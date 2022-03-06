package com.nosorae.labs.inbody

import android.content.ContentValues
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.drawToBitmap
import com.nosorae.labs.databinding.ActivityBeforeAfterBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream


class BeforeAfterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBeforeAfterBinding

    // @RequiresApi(Build.VERSION_CODES.Q)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBeforeAfterBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun onResume() {
        super.onResume()
        binding.btSave.setOnClickListener {
            val bitmap = binding.clBeforeContainer.drawToBitmap()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                mySave()
            } else {
                saveTheImageLegacyStyle(bitmap)
            }
        }
    }

    private fun mySave() {
        var os: OutputStream?
        val bitmap = binding.clBeforeContainer.drawToBitmap()

        // MediaStore.Images.Media.insertImage(contentResolver, bitmap, "InBody", "")


        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME,
                System.currentTimeMillis().toString() + "BeforeNAfter")
            put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DCIM)
                put(MediaStore.MediaColumns.IS_PENDING, 1)
            }

        }

        val resolver = contentResolver
        val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        try {
            uri?.let {
                os = resolver.openOutputStream(it)
                os?.let { stream ->
                    Log.e("asdf", uri.toString())
                    if (!bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)) {
                        throw IOException("Failed to save bitmap.")
                    } else {

                    }
                } ?: throw IOException("Failed to get output stream")
            } ?: throw IOException("Failed to create new MediaStore record")
        } catch (e: IOException) {
            if (uri != null) {
                resolver.delete(uri, null, null)
            }
            throw IOException(e)
        } finally {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                Log.e("asdf", "save success")
                contentValues.put(MediaStore.MediaColumns.IS_PENDING, 0)
                uri?.let {
                    resolver.update(uri, contentValues, null, null)
                }
            }
        }

    }


    //Make sure to call this function on a worker thread, else it will block main thread
    @RequiresApi(Build.VERSION_CODES.Q)
    fun saveImageInQ(bitmap: Bitmap): Uri? {
        val filename = "IMG_${System.currentTimeMillis()}.jpg"
        var fos: OutputStream? = null
        var imageUri: Uri? = null
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            put(MediaStore.Video.Media.IS_PENDING, 1)
        }

        //use application context to get contentResolver
        val contentResolver = application.contentResolver

        contentResolver.also { resolver ->
            imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            fos = imageUri?.let { resolver.openOutputStream(it) }
        }

        fos?.use { bitmap.compress(Bitmap.CompressFormat.JPEG, 70, it) }

        contentValues.clear()
        contentValues.put(MediaStore.Video.Media.IS_PENDING, 0)
        imageUri?.let { contentResolver.update(it, contentValues, null, null) }

        return imageUri
    }

    //Make sure to call this function on a worker thread, else it will block main thread
    fun saveTheImageLegacyStyle(bitmap:Bitmap){
        var fos: OutputStream? = null
        val imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val image = File(imagesDir, "filename")
        fos = FileOutputStream(image)
        fos.use {bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)}
    }

}
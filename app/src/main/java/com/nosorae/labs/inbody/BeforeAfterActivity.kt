package com.nosorae.labs.inbody

import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.drawToBitmap
import com.nosorae.labs.databinding.ActivityBeforeAfterBinding
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.*


class BeforeAfterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBeforeAfterBinding

   // @RequiresApi(Build.VERSION_CODES.Q)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBeforeAfterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btSave.setOnClickListener {
            Log.e("my uri", "onClick")

            var os: OutputStream? = null
            val bitmap = binding.clBeforeContainer.drawToBitmap()

            MediaStore.Images.Media.insertImage(contentResolver, bitmap, "InBody", "")

//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                val resolver = contentResolver
//                val contentValues = ContentValues().apply {
//                    put(MediaStore.MediaColumns.DISPLAY_NAME, System.currentTimeMillis().toString()+"BeforeNAfter")
//                    put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
//                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DCIM)
//                }
//                val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
//                uri?.let {  os = resolver.openOutputStream(it) }
//                Log.e("my uri", uri.toString())
//
//            } else {
//                val imgDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
//
//                Log.e("my uri", imgDir.toString())
//
//                val folder = File(imgDir.toString())
//                if (!folder.exists()) {
//                    folder.mkdir()
//                }
//
//                val image = File(imgDir, System.currentTimeMillis().toString()+"BeforeNAfter")
//                os = FileOutputStream(image)
//            }
//
//
//            os?.let { it ->
//                Log.e("my uri", "os is not null")
//                bitmap.compress(CompressFormat.PNG, 100, os)
//                it.flush()
//                it.close()
//            }
        }
    }

}
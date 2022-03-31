package com.nosorae.labs.assignments.inbody

import android.Manifest
import android.content.ContentValues
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.drawToBitmap
import com.nosorae.labs.databinding.ActivityBeforeAfterBinding
import java.io.IOException
import java.io.OutputStream


class BeforeAfterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBeforeAfterBinding

    var afterOk = false
    var beforeOk = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBeforeAfterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        disableSaveButton()
        initBeforeAfterFragments()
    }

    // TODO 버튼 색깔
    private fun disableSaveButton() {
        binding.btSave.setOnClickListener {
            Toast.makeText(this, "두 이미지 모두 선택해주세요.", Toast.LENGTH_LONG).show()
        }
    }

    private fun initBeforeAfterFragments() {
        supportFragmentManager.apply {
            beginTransaction().replace(binding.flBeforeContainer.id, BeforeNAfterFragment(), "전")
                .commit()
            beginTransaction().replace(binding.flAfterContainer.id, BeforeNAfterFragment(), "후")
                .commit()


            setFragmentResultListener(PARAM_BEFORE_RESULT,
                this@BeforeAfterActivity) { requestKey, bundle ->
                beforeOk = true
                if (afterOk) enableSaveButton()
            }
            setFragmentResultListener(PARAM_AFTER_RESULT,
                this@BeforeAfterActivity) { requestKey, bundle ->
                afterOk = true
                if (beforeOk) enableSaveButton()
            }
        }

    }


    private val requestWriteStoragePermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
            if (result) {
                saveImageToGallery()
            } else {
                Toast.makeText(this, "권한을 허용해야 갤러리에 접근 가능합니다.", Toast.LENGTH_LONG).show()
            }
        }
    private fun enableSaveButton() {
        binding.btSave.setOnClickListener {
            requestWriteStoragePermission.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
    }


    // 별도의 스레드에서 처리
    private fun saveImageToGallery() {
        var os: OutputStream?
        val bitmap = binding.clCaptureFrame.drawToBitmap()

        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, "BeforeNAfter_0")
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
                    if (!bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)) {
                        throw IOException("Failed to save bitmap.")
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
                contentValues.put(MediaStore.MediaColumns.IS_PENDING, 0)
                uri?.let {
                    resolver.update(uri, contentValues, null, null)
                }
            }
        }

    }


    companion object {
        const val PARAM_BEFORE_RESULT = "PARAM_BEFORE_RESULT"
        const val PARAM_AFTER_RESULT = "PARAM_AFTER_RESULT"
    }
}
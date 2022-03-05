package com.nosorae.labs.inbody

import android.content.ContentValues
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.core.graphics.createBitmap
import androidx.core.view.drawToBitmap
import com.nosorae.labs.R
import com.nosorae.labs.databinding.ActivityBeforeAfterBinding

class BeforeAfterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBeforeAfterBinding

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_before_after)
        binding = ActivityBeforeAfterBinding.inflate(layoutInflater)


        binding.btSave.setOnClickListener {
            val bitmap = binding.clBeforeContainer.drawToBitmap()
            val resolver = contentResolver
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, "CuteKitten001")
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                put(MediaStore.MediaColumns.RELATIVE_PATH, "DCIM/PerracoLabs")
            }

            val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        }



    }
}
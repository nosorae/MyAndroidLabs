package com.nosorae.labs.clean_architecture.hilt.background.service

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nosorae.labs.R

class ServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        Intent(
            this,
            TimerService::class.java
        ).also { intent ->
            intent.putExtra("args", "노소래의 서비스 테스트!!")
            startService(intent)
        }
    }
}
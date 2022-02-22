package com.nosorae.labs.background.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

class TimerService: Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

}
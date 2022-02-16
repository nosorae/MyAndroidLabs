package com.nosorae.labs

import android.app.Application
import android.content.IntentFilter
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.nosorae.labs.background.LabsBroadcastReceiver
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LabsApplication: Application() {
    val br = LabsBroadcastReceiver()
    val filter = IntentFilter("com.nosorae.labs.MY_NOTIFICATION")


    override fun onCreate() {
        super.onCreate()
        Log.d("labslogtag", "onCreate LabsApplication")
        LocalBroadcastManager.getInstance(this).registerReceiver(br, filter)
    }

    override fun onTerminate() {
        super.onTerminate()
        Log.d("labslogtag", "onTerminate LabsApplication")
        unregisterReceiver(br)
    }

}
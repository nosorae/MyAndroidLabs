package com.nosorae.labs.background.service

import android.app.Service
import android.content.Intent
import android.os.*
import android.widget.Toast

class TimerService: Service() {

//    private var serviceLooper: Looper? = null
//    private var serviceHandler: ServiceHandler? = null

//    // Handler that receives messages from the thread
//    private inner class ServiceHandler(looper: Looper) : Handler(looper) {
//
//        override fun handleMessage(msg: Message) {
//            // Normally we would do some work here, like download a file.
//            // For our sample, we just sleep for 5 seconds.
//            try {
//                Thread.sleep(5000)
//            } catch (e: InterruptedException) {
//                // Restore interrupt status.
//                Thread.currentThread().interrupt()
//            }
//
//            // Stop the service using the startId, so that we don't stop
//            // the service in the middle of handling another job
//            stopSelf(msg.arg1)
//        }
//    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
//        HandlerThread("HandlerThreadName", Process.THREAD_PRIORITY_BACKGROUND).apply {
//            // Get the HandlerThread's Looper and use it for our Handler
//            serviceLooper = looper
//            serviceHandler = ServiceHandler(looper)
//        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "service starting ${intent?.getStringExtra("args")}", Toast.LENGTH_SHORT).show()

//        // For each start request, send a message to start a job and deliver the
//        // start ID so we know which request we're stopping when we finish the job
//        serviceHandler?.obtainMessage()?.also { msg ->
//            msg.arg1 = startId
//            serviceHandler?.sendMessage(msg)
//        }

        stopSelf()
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show()
    }

}
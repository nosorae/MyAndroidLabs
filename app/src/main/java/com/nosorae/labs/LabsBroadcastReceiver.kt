package com.nosorae.labs

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class LabsBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("labslogtag", "LabsBroadcastReceiver")
        StringBuilder().apply {
            intent?.let {
                append("Action: ${it.action}\n")
                append("URI: ${it.toUri(Intent.URI_INTENT_SCHEME)}\n")
                toString().also { log ->
                    Log.d("labslogtag", log)
                    Toast.makeText(context, log, Toast.LENGTH_LONG).show()
                }
            }

        }
    }
}
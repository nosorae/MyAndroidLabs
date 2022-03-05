package com.nosorae.labs.clean_architecture.hilt.background.service

import android.app.IntentService
import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.nosorae.labs.clean_architecture.hilt.common.Resource
import com.nosorae.labs.clean_architecture.hilt.domain.use_case.get_coin.GetCoinsUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import kotlin.concurrent.thread

@AndroidEntryPoint
class TimerService: Service() {

    @Inject
    lateinit var getCoinsUseCase: GetCoinsUseCase

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Thread {
            while (true) {
                getCoinsUseCase().onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            showToast(result.data!![0].toString())
                        }
                        is Resource.Loading -> {

                        }
                        is Resource.Error -> {
                            showToast(result.message.toString())
                        }
                    }
                }.launchIn(scope = GlobalScope)

                Thread.sleep(5000)
                Log.e("asdf", "asdf")
            }
        }.run()

        return START_STICKY
    }

    override fun onDestroy() {
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show()
    }

    private fun showToast(text: String) {
        val handler = Handler(Looper.getMainLooper())
        handler.post {
            Toast.makeText(applicationContext, text, Toast.LENGTH_LONG).show()
        }
    }


}
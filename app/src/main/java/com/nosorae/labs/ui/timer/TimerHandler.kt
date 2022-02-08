package com.nosorae.labs.ui.timer

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.core.content.getSystemService
import androidx.localbroadcastmanager.content.LocalBroadcastManager

/**/
class TimerHandler(
    private val context: Context,
    val startTime: Long,
    val endTime: Long,
    val stepTime: Long = 1000,
    val endFunc: (() -> Unit)? = null
) : Handler(Looper.getMainLooper()) {

    // UI에서 현재 측정 시간을 가져 가기위해
    var currentTime: Long = 0

    // BroadcastReceiver register 위한 변수
    val br = TimerBroadcastReceiver()
    val filter = IntentFilter(TIMER_BROADCAST_ACTION)

    init {
        currentTime = startTime
    }

    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        when (msg.what) {
            TIMER_PAUSE ->{

            }
            TIMER_RESET -> {
                unregisterLocalBroadcast()

                currentTime = startTime
                this.sendEmptyMessage(TIMER_PAUSE)
            }
            TIMER_START -> {
                registerLocalBroadcast()

                this.sendEmptyMessage(TIMER_CONTINUE)
            }
            TIMER_CONTINUE -> {
                currentTime += stepTime
                if (endTime > currentTime)
                    this.sendEmptyMessageDelayed(TIMER_CONTINUE, stepTime)
                else
                    this.sendEmptyMessage(TIMER_STOP)
            }
            TIMER_STOP -> {
                sendBroadcastMessage()
                unregisterLocalBroadcast()

                endFunc?.let { it() }
            }
        }
    }

    private fun registerLocalBroadcast() {
        LocalBroadcastManager.getInstance(context).registerReceiver(br, filter)
    }
    private fun unregisterLocalBroadcast() {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(br)
    }
    private fun sendBroadcastMessage() {
        val intent = Intent().also {
            it.setAction(TIMER_BROADCAST_ACTION)
        }
       LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
    }


    companion object {
        const val TIMER_PAUSE = 1000    // 일시 정지 상태
        const val TIMER_RESET = 1001    // 타이머를 다시 시작
        const val TIMER_START = 1002    // 타이머 시작
        const val TIMER_CONTINUE = 1003 // 타이머가 작동 중인 경우
        const val TIMER_STOP = 1004     // 타이머가 종료 된 경우
        const val TIMER_BROADCAST_ACTION = "com.partnus.timer.TIMER_NOTIFICATION"
    }
}
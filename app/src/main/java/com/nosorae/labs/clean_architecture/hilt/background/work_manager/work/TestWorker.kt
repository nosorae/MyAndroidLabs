package com.nosorae.labs.clean_architecture.hilt.background.work_manager.work

import android.app.NotificationManager
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.nosorae.labs.clean_architecture.hilt.common.Constants.KEY_TEST_WORKER_DATA
import com.nosorae.labs.clean_architecture.hilt.common.Constants.LOG_TAG_TEST_WORKER
import kotlinx.coroutines.delay


class TestWorker(context: Context, params: WorkerParameters): CoroutineWorker(context, params) {

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as
                NotificationManager

    private fun createForegroundInfo() {

    }


    override suspend fun doWork(): Result {
        delay(5000L)
//        setForeground(
//            ForegroundInfo(
//                1,
//                Notification.Builder(applicationContext)
//                    .setContentTitle("Timer")
//                    .setContentText("Timer 작동완료")
//                    .setSmallIcon(R.drawable.)
//                    .build()
//            )
//        )
        //val appContext = applicationContext
        val number = inputData.getString(KEY_TEST_WORKER_DATA)?.toInt()
        Log.e(LOG_TAG_TEST_WORKER, "TestWorker 호출됨 : $number")
        showToast(number)
        val outputData = workDataOf(KEY_TEST_WORKER_DATA to "${number!! + 1}")
        return Result.success(outputData)
    }

    private fun showToast(number: Int?) {
        val handler = Handler(Looper.getMainLooper())
        handler.post {
            Toast.makeText(applicationContext, "TestWorker 호출됨 : $number", Toast.LENGTH_LONG).show()
        }
    }

}
//
//try {

//    return ListenableWorker.Result.success()
//} catch (e: Exception) {
//    return ListenableWorker.Result.failure()
//}
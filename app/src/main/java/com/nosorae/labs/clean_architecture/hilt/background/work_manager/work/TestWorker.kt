package com.nosorae.labs.clean_architecture.hilt.background.work_manager.work

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.nosorae.labs.clean_architecture.hilt.common.Constants.KEY_TEST_WORKER_DATA


class TestWorker(context: Context, params: WorkerParameters): Worker(context, params) {
    override fun doWork(): Result {

        //val appContext = applicationContext
        val number = inputData.getString(KEY_TEST_WORKER_DATA)?.toInt()
        Log.e("TestWorker", "TestWorker 호출됨 : $number")
        //Toast.makeText(appContext, "TestWorker 호출됨 : $number", Toast.LENGTH_LONG).show()
        val outputData = workDataOf(KEY_TEST_WORKER_DATA to "${number!! + 1}")
        return Result.success(outputData)
    }
}
//
//try {

//    return ListenableWorker.Result.success()
//} catch (e: Exception) {
//    return ListenableWorker.Result.failure()
//}
package com.example.jetpacktest

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

/// 后台任务
class SimpleWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    // 不是主线程
    override fun doWork(): Result {
        Log.d("SimpleWorker", "do work in SimpleWorker")
        return Result.success()
    }
}
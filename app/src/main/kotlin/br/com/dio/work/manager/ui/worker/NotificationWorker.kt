package br.com.dio.work.manager.ui.worker

import android.content.Context
import android.util.Log
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import br.com.dio.work.manager.datasource.VideosDataSource
import br.com.dio.work.manager.ui.extensions.showBigPictureNotification
import java.util.concurrent.TimeUnit

class NotificationWorker(
    private val context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {
    override fun doWork(): Result {
        Log.i("TESTE", TAG)

        val video = VideosDataSource.getRandomVideo()
        context.showBigPictureNotification(video)

        return Result.success()
    }

    companion object {
        private const val TAG = "NotificationWorker"
        private const val WORK_NAME = "worker_name"
        fun start(context: Context) {
            WorkManager.getInstance(context)
                .enqueueUniqueWork(
                    WORK_NAME,
                    androidx.work.ExistingWorkPolicy.KEEP,
                    createRequest()
                )
        }

        private fun createRequest() =
            androidx.work.OneTimeWorkRequest.Builder(NotificationWorker::class.java)
                .setInitialDelay(10, java.util.concurrent.TimeUnit.SECONDS)
                .build()

        fun startByPeriod(context: Context) {
            Log.i("TESTE", TAG)
            WorkManager.getInstance(context)
                .enqueueUniquePeriodicWork(
                    WORK_NAME,
                    ExistingPeriodicWorkPolicy.KEEP,
                    createPeriodicRequest()
                )
        }

        private fun createPeriodicRequest() = PeriodicWorkRequestBuilder<NotificationWorker>(
            5,
            TimeUnit.SECONDS
        ).build()
    }
}


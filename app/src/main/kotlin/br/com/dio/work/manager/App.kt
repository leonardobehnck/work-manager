package br.com.dio.work.manager

import android.app.Application
import br.com.dio.work.manager.datasource.VideosDataSource
import br.com.dio.work.manager.ui.worker.NotificationWorker

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        VideosDataSource.setFromFile(assets)
    }
}
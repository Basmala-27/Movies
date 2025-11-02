package com.moviecoo.colorthemeandtypography.services

import android.content.Context
import android.content.Intent
import android.os.Build
import com.moviecoo.colorthemeandtypography.services.download.DownloadService
import com.moviecoo.colorthemeandtypography.services.maintenance.CacheCleanService
import com.moviecoo.colorthemeandtypography.services.notification.NotificationService
import com.moviecoo.colorthemeandtypography.services.player.MoviePlayerService
import com.moviecoo.colorthemeandtypography.services.sync.SyncService

object ServiceStarter {

    fun startBackgroundServices(context: Context) {
        startForegroundService(context, CacheCleanService::class.java)
        startForegroundService(context, SyncService::class.java)
    }

    fun startUIAndNotificationServices(context: Context) {
        startForegroundService(context, NotificationService::class.java)
        startForegroundService(context, MoviePlayerService::class.java)
    }

    private fun startForegroundService(context: Context, serviceClass: Class<*>) {
        val intent = Intent(context, serviceClass)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent)
        } else {
            context.startService(intent)
        }
    }
}

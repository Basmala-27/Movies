package com.moviecoo.colorthemeandtypography.services.download

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.moviecoo.colorthemeandtypography.R
import kotlinx.coroutines.*

class DownloadService : Service() {

    private val channelId = "download_channel"
    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.IO + serviceJob)

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(2, createNotification("Downloading...", 0))

        serviceScope.launch {
            for (i in 1..100) {
                delay(100)
                val notification = createNotification("Downloading...", i)
                val manager = getSystemService(NotificationManager::class.java)
                manager?.notify(2, notification)
            }
            stopSelf()
        }

        return START_STICKY
    }

    private fun createNotification(title: String, progress: Int): Notification {
        return NotificationCompat.Builder(this, channelId)
            .setContentTitle(title)
            .setContentText(if (progress < 100) "Progress: $progress%" else "Download complete")
            .setSmallIcon(R.drawable.homem_aranha)
            .setProgress(100, progress, false)
            .build()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                channelId,
                "Download Service Channel",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(serviceChannel)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceJob.cancel()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}

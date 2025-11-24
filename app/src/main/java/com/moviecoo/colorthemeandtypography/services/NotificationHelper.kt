package com.moviecoo.colorthemeandtypography.helpers

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.moviecoo.colorthemeandtypography.R

class NotificationHelper(private val context: Context) {

    private val channelId = "default_channel"

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    fun sendNotification(notificationId: Int, title: String, message: String) {
        createNotificationChannel()
        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.homem_aranha)
            .build()
        NotificationManagerCompat.from(context).notify(notificationId, notification)
    }

    private fun createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Default Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = context.getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(channel)
        }
    }
}

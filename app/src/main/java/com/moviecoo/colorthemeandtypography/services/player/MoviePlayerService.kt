package com.moviecoo.colorthemeandtypography.services.player

import android.app.*
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.moviecoo.colorthemeandtypography.R
import android.util.Log

class MoviePlayerService : Service() {

    private val channelId = "movie_player_channel"

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification: Notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Movie Player")
            .setContentText("Playing your movie...")
            .setSmallIcon(R.drawable.homem_aranha)
            .build()

        startForeground(1, notification)
        playMovie()

        return START_STICKY
    }

    private fun playMovie() {
        Log.d("MoviePlayerService", "Movie is playing in background...")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MoviePlayerService", "Movie stopped.")
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                channelId,
                "Movie Player Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(serviceChannel)
        }
    }
}

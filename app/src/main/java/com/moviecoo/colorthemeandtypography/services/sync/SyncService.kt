package com.moviecoo.colorthemeandtypography.services.sync

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*

class SyncService : Service() {
    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.IO + serviceJob)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        serviceScope.launch {
            syncUserData()
            stopSelf()
        }
        return START_STICKY
    }
    private suspend fun syncUserData() {
        delay(2000)
        Log.d("SyncService", "User data synced successfully.")
    }
    override fun onDestroy() {
        super.onDestroy()
        serviceJob.cancel()
        Log.d("SyncService", "SyncService stopped.")
    }
    override fun onBind(intent: Intent?): IBinder? = null
}

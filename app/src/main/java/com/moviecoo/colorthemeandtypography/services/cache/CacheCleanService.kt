package com.moviecoo.colorthemeandtypography.services.maintenance

import android.app.Service
import android.content.Intent
import android.os.IBinder
import kotlinx.coroutines.*
import java.io.File
import android.util.Log

class CacheCleanService : Service() {
    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.IO + serviceJob)

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        serviceScope.launch {
            cleanCache()
            stopSelf()
        }
        return START_STICKY
    }

    private suspend fun cleanCache() {
        val cacheDirectory: File? = cacheDir
        cacheDirectory?.listFiles()?.forEach { file ->
            if (file.isFile && file.name.endsWith(".tmp")) {
                if (file.delete()) {
                    Log.d("CacheCleanService", "Deleted: ${file.name}")
                }
            }
        }
        Log.d("CacheCleanService", "Cache cleaned successfully.")
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceJob.cancel()
        Log.d("CacheCleanService", "CacheCleanService stopped.")
    }

    override fun onBind(intent: Intent?): IBinder? = null
}

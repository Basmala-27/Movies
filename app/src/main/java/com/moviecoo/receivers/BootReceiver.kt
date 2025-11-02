package com.moviecoo.colorthemeandtypography.broadcastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.moviecoo.colorthemeandtypography.services.ServiceStarter

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            ServiceStarter.startBackgroundServices(context)
        }
    }
}

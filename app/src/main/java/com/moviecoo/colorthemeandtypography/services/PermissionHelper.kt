package com.moviecoo.colorthemeandtypography.helpers

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

class PermissionHelper(private val activity: ComponentActivity, private val onAllGranted: () -> Unit) {

    private val requiredPermissions = arrayOf(
        Manifest.permission.POST_NOTIFICATIONS,
        Manifest.permission.BLUETOOTH_CONNECT,
        Manifest.permission.BLUETOOTH_SCAN,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    lateinit var requestPermissionLauncher: ActivityResultLauncher<Array<String>>

    fun initLauncher() {
        requestPermissionLauncher =
            activity.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { resultMap ->
                val allGranted = requiredPermissions.all { permission ->
                    resultMap[permission] ?: false
                }
                if (allGranted) {
                    onAllGranted()
                } else {
                    Toast.makeText(activity, "All permissions are required!", Toast.LENGTH_LONG).show()
                }
            }
    }

    fun checkAndRequestPermissions() {
        val notGranted = requiredPermissions.filter {
            ContextCompat.checkSelfPermission(activity, it) != PackageManager.PERMISSION_GRANTED
        }
        if (notGranted.isEmpty()) {
            onAllGranted()
        } else {
            requestPermissionLauncher.launch(notGranted.toTypedArray())
        }
    }
}


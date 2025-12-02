package com.moviecoo.colorthemeandtypography.ui.Screens.errorScreen


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object NetworkStatus {

    fun getNetworkStatus(context: Context): NetworkState {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return NetworkState.NoConnection
        val capabilities = connectivityManager.getNetworkCapabilities(network)
            ?: return NetworkState.NoConnection

        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                val downSpeed = capabilities.linkDownstreamBandwidthKbps
                if (downSpeed < 1000) {  // أقل من 1Mbps
                    NetworkState.PoorConnection
                } else {
                    NetworkState.Connected
                }
            }

            else -> NetworkState.NoConnection
        }
    }
}



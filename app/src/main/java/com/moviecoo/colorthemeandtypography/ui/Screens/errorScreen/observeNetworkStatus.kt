package com.moviecoo.colorthemeandtypography.ui.Screens.errorScreen

import android.content.Context
import android.net.ConnectivityManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

@Composable
fun observeNetworkStatus(refreshKey: Int): NetworkState {
    val context = LocalContext.current
    var networkState by remember { mutableStateOf<NetworkState>(NetworkState.NoConnection) }

    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    DisposableEffect(context, refreshKey) {
        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: android.net.Network) {
                val capabilities = connectivityManager.getNetworkCapabilities(network)
                networkState = if (capabilities != null) {
                    val speedKbps = capabilities.linkDownstreamBandwidthKbps
                    if (speedKbps < 1000) NetworkState.PoorConnection else NetworkState.Connected
                } else {
                    NetworkState.NoConnection
                }
            }

            override fun onLost(network: android.net.Network) {
                networkState = NetworkState.NoConnection
            }
        }

        connectivityManager.registerDefaultNetworkCallback(callback)

        onDispose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }

    return networkState
}



package com.moviecoo.colorthemeandtypography.ui.Screens.errorScreen

import android.content.Context
import android.net.ConnectivityManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

/**
 * ## observeNetworkStatus
 * A composable function that observes the device's real-time network connectivity status.
 *
 * It utilizes the Android [ConnectivityManager.NetworkCallback] within a [DisposableEffect]
 * to register and unregister the listener, ensuring proper resource management tied to
 * the composable's lifecycle.
 *
 * @param refreshKey An optional key (defaulting to 0) that can be changed to force the
 * re-registration of the network callback, if needed (e.g., for a manual retry).
 * @return The current [NetworkState] as a mutable Compose State.
 */
@Composable
fun observeNetworkStatus(refreshKey: Int = 0): NetworkState {
    val context = LocalContext.current

    // State variable to hold and update the network status, triggering recomposition when changed.
    var networkState by remember { mutableStateOf<NetworkState>(NetworkState.NoConnection) }

    // Retrieve the system ConnectivityManager service.
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    // --- Lifecycle-Aware Network Callback Registration ---
    DisposableEffect(context, refreshKey) {
        // 1. Define the NetworkCallback to handle network events.
        val callback = object : ConnectivityManager.NetworkCallback() {
            /**
             * Called when a network connection becomes available.
             */
            override fun onAvailable(network: android.net.Network) {
                val capabilities = connectivityManager.getNetworkCapabilities(network)
                networkState = if (capabilities != null) {
                    // Check bandwidth to assess connection quality.
                    val speedKbps = capabilities.linkDownstreamBandwidthKbps
                    // Assuming < 1 Mbps (1000 Kbps) is considered a poor connection.
                    if (speedKbps < 1000) NetworkState.PoorConnection else NetworkState.Connected
                } else {
                    NetworkState.NoConnection
                }
            }

            /**
             * Called when a network connection is lost.
             */
            override fun onLost(network: android.net.Network) {
                // When any network is lost, default to no connection until a new one is available.
                networkState = NetworkState.NoConnection
            }
        }

        // 2. Register the callback with the system.
        // registerDefaultNetworkCallback monitors the system's current default network.
        connectivityManager.registerDefaultNetworkCallback(callback)

        // 3. Define the cleanup block (onDispose).
        onDispose {
            // Unregister the callback when the composable leaves the composition (or keys change)
            // to prevent memory leaks and unnecessary system overhead.
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }

    return networkState
}
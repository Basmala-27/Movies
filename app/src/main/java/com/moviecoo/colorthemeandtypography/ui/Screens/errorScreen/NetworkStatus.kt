package com.moviecoo.colorthemeandtypography.ui.Screens.errorScreen

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

/**
 * ## NetworkStatus
 * A utility object responsible for checking the current network connection status
 * and assessing its quality based on downstream bandwidth.
 *
 * This object is used to drive the state of the [NetworkState] sealed class
 * (which should be defined elsewhere in this package).
 */
object NetworkStatus {

    /**
     * Retrieves the current network status by checking connectivity and bandwidth.
     *
     * @param context The Android context required to access the ConnectivityManager service.
     * @return A [NetworkState] object indicating the connection quality.
     */
    fun getNetworkStatus(context: Context): NetworkState {
        // 1. Get Connectivity Manager Service
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // 2. Check for Active Network
        // Returns NoConnection immediately if no active network is found.
        val network = connectivityManager.activeNetwork ?: return NetworkState.NoConnection

        // 3. Get Network Capabilities
        // Returns NoConnection if capabilities cannot be determined.
        val capabilities = connectivityManager.getNetworkCapabilities(network)
            ?: return NetworkState.NoConnection

        // 4. Assess Connection Type and Quality
        return when {
            // Check if the active network is Wi-Fi or Cellular (the primary internet connections).
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {

                // Retrieve the estimated maximum downstream bandwidth in kilobits per second (Kbps).
                val downSpeed = capabilities.linkDownstreamBandwidthKbps

                // Determine Poor Connection (Example threshold: less than 1 Mbps)
                // 1000 Kbps = 1 Mbps. This threshold can be adjusted based on app requirements.
                if (downSpeed < 1000) {
                    NetworkState.PoorConnection
                } else {
                    NetworkState.Connected
                }
            }

            // Handle other types of transport (e.g., VPN, Ethernet) or unknown connection.
            else -> NetworkState.NoConnection
        }
    }
}
// NOTE: For a complete solution, ensure the NetworkState sealed class is available
// and that this method is called frequently (e.g., via a BroadcastReceiver or
// Flow/LiveData observing the ConnectivityManager's NetworkCallback) to provide
// real-time updates to the UI.
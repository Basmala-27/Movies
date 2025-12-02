package com.moviecoo.colorthemeandtypography.ui.Screens.errorScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.firestore.remote.ConnectivityMonitor

//@Composable
//fun NetworkHandlerScreen(
//    content: @Composable () -> Unit
//) {
//    val context = LocalContext.current
//    var refreshKey by remember { mutableStateOf(0) }
//    // مفتاح لإعادة التقييم
//    val networkState = remember(refreshKey) {
//        NetworkStatus.getNetworkStatus(context)
//    }
//
//    when (networkState) {
//
//        is NetworkState.NoConnection -> {
//            NoInternetScreen(
//                onRetry = {
//                    refreshKey++
//                }
//            )
//        }
//
//        is NetworkState.PoorConnection -> {
//            PoorConnectionScreen(
//                onRetry = {
//                    refreshKey++
//                }
//            )
//        }
//
//        is NetworkState.Connected -> {
//            // content() سيتم إعادة تقييمه كل مرة refreshKey يتغير
//            key(refreshKey) {
//                content()
//            }
//        }
//    }
//}
sealed class NetworkState {
    object Connected : NetworkState()
    object PoorConnection : NetworkState()
    object NoConnection : NetworkState()
}
@Composable
fun NetworkHandlerScreen(
    content: @Composable () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        // الشاشة الأساسية موجودة دايمًا
        content()

        // الشبكة
        val networkState = observeNetworkStatus(0)

        // Overlay للشبكة
        if (networkState is NetworkState.NoConnection) {
            NoInternetScreen(
                onRetry = {
                    // ممكن تحطي Toast فقط، بدون إعادة تحميل التطبيق
                }
            )
        } else if (networkState is NetworkState.PoorConnection) {
            PoorConnectionScreen(
                onRetry = {
                    // نفس الشيء
                }
            )
        }
    }
}


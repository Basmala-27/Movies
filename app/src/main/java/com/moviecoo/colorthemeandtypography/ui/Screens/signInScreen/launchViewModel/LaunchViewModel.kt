package com.moviecoo.colorthemeandtypography.ui.Screens.signInScreen.launchViewModel


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviecoo.colorthemeandtypography.ui.Screens.signInScreen.dataStoreManager.DataStoreManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LaunchViewModel : ViewModel() {

    private val _launchCount = MutableStateFlow(0)
    val launchCount: StateFlow<Int> = _launchCount
    private val _hasLaunched = MutableStateFlow(false)
    val hasLaunched: StateFlow<Boolean> = _hasLaunched

    fun loadHasLaunched(context: Context) {
        viewModelScope.launch {
            val launched = DataStoreManager.getHasLaunchedOnboarding(context)
            _hasLaunched.value = launched
        }
    }

    fun markLaunched(context: Context) {
        viewModelScope.launch {
            DataStoreManager.setHasLaunchedOnboarding(context, true)
            _hasLaunched.value = true
        }
    }

    fun loadLaunchCount(context: Context) {
        viewModelScope.launch {
            DataStoreManager.getLaunchCount(context).collect { count ->
                _launchCount.value = count
            }
        }
    }

    fun increase(context: Context) {
        viewModelScope.launch {
            DataStoreManager.incrementLaunchCount(context)
        }
    }
}

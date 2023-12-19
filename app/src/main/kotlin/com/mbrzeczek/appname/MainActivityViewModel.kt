package com.mbrzeczek.appname

import android.content.Intent
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbrzeczek.appname.navigation.AppRedirect
import com.mbrzeczek.appname.navigation.deeplinks.DeepLinkServiceable
import com.mbrzeczek.appname.util.AppTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val deepLinkService: DeepLinkServiceable,
) : ViewModel() {

    /**
     * Initial [MainActivity] ui state is set to [MainActivityUiState.Loading] and mapped to
     * [MainActivityUiState.Success] once the [AppTheme] data is retrieved
     */
    val uiState = mutableStateOf(MainActivityUiState.Success)

    private val _redirect = MutableSharedFlow<AppRedirect>()
    val redirect = _redirect.asSharedFlow()

    private var redirected: Boolean = false

    /**
     * Redirect the user to another part of the app if necessary. This can be deep links, in-app
     * messages, user surveys, app rating dialogs, etc.
     *
     * Redirect can only happen once, and ideally should only be performed after the user launches
     * the app.
     *
     * @param force `true` to redirect even if the user had already been redirected.
     */
    fun redirectIfNeeded(force: Boolean = false) {
        if (force.not() && redirected) return

        viewModelScope.launch {
            // delay is a workaround to navigate immediately after NavController graph is set
            delay(1)

            if (redirectForDeepLink()) {
                redirected = true
                return@launch
            }
        }
    }

    fun initialize(intent: Intent?) {
        if (intent == null) return
        viewModelScope.launch {
            parseIntent(intent)
        }
    }

    private suspend fun parseIntent(intent: Intent) {
        val action = deepLinkService.parse(intent)
        deepLinkService.launchAction = action
    }

    /**
     * Redirects for deep link if one is available.
     *
     * @return `true` if we redirected
     */
    private fun redirectForDeepLink(): Boolean {
        val launchAction = deepLinkService.launchAction
        val cache = deepLinkService.cache

        return when {
            launchAction != null -> {
                viewModelScope.launch {
                    _redirect.emit(AppRedirect.DeepLink(launchAction))
                }
                deepLinkService.launchAction = null

                true
            }

            cache != null -> {
                viewModelScope.launch {
                    _redirect.emit(AppRedirect.DeepLink(cache))
                }
                deepLinkService.cache = null

                true
            }

            else -> {
                false
            }
        }
    }
}

sealed class MainActivityUiState {
    object Loading : MainActivityUiState()
    object Success : MainActivityUiState()
}

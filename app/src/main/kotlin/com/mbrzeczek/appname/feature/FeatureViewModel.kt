package com.mbrzeczek.appname.feature

import androidx.lifecycle.ViewModel
import com.mbrzeczek.appname.feature.model.UiAction
import com.mbrzeczek.appname.feature.model.UiState
import com.mbrzeczek.appname.navigation.NavigationTarget
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow

/**
 * A ViewModel that encourages a Declarative and Reactive way of updating pages, handling user
 * actions, and navigating to other pages.
 *
 * **This should only be used for pages built using Jetpack Compose. If you are consuming a
 * [FeatureView], please use [FeatureView] and [FeatureViewViewModel] instead.**
 *
 * &nbsp;
 *
 * A View's UI state is exposed via the [state] property, which provides an immutable
 * representation of data that should be presented to the user. Only the ViewModel should update
 * this state.
 *
 * User actions in the View should notify the ViewModel using [onAction], which should serve as
 * the sole entry point for feeding the ViewModel with user actions.
 *
 * To navigate, use [navigateTo]. The [NavigationTarget] is exposed via the [navigation] property.
 *
 * @see UiState
 * @see UiAction
 * @see NavigationTarget
 *
 */
abstract class FeatureViewModel<S : UiState, A : UiAction, N : NavigationTarget> : ViewModel() {
    /**
     * Provides the entire View's data. All operations on the data need to be done through here
     * to ensure we are Reactive.
     */
    abstract val state: StateFlow<S>

    /**
     * Processes a [UiAction] sent from the View.
     */
    abstract fun onAction(action: A)

    private val _navigation = Channel<N>(Channel.BUFFERED)
    val navigation = _navigation.receiveAsFlow()

    /**
     * Sets the [NavigationTarget] which indicates where the user should be navigated to.
     */
    suspend fun navigateTo(target: N) {
        _navigation.send(target)
    }
}

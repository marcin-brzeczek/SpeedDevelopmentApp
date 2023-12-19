package com.mbrzeczek.appname.feature.model

/**
 * A action driven by the View that should be handled by the ViewModel. For example,
 * this includes user-initiated actions such as button taps, list item selections, and swipe events.
 *
 * All user-initiated actions need to notify the ViewModel so that all business logic for handling
 * actions is contained inside the ViewModel. In response, the ViewModel determines whether
 * [UiState] needs to be updated, or if it needs to send events back to the View for high-level
 * handling.
 *
 */
interface UiAction

/**
 * An empty action for screens where the View does not produce any actions for the ViewModel.
 */
object EmptyAction : UiAction

private sealed interface SampleAction : UiAction {
    object BackClicked : SampleAction
}

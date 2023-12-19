package com.mbrzeczek.appname.feature.model

/**
 * Describes what should be displayed on a screen.
 *
 * For describing one-time, higher-level concepts such as navigation and presentation, use
 * [UiEvent] instead if you're building with the Android View System (pre-Compose). If you're
 * building with Compose, use [NavigationTarget] instead.
 *
 * UiState is driven by the ViewModel to provides state to the View (Activity/Fragment/Composable).
 * This should include all user-facing data that the View needs to render. The state should serve
 * as the "source of truth" for data that is rendered in the View.
 *
 * Only the ViewModel should be the one mutating UiState. The only thing the View should do is
 * render according to the state.
 *
 * If you need to update data in your View, you should do this through the ViewModel, and let your
 * View passively react to those changes in state. For triggering the ViewModel to update state,
 * see [UiAction].
 *
 * If you're building with the Android View System (pre-Compose), leverage the hooks provided
 * for easy adoption by [FeatureView] and [FeatureViewViewModel]. If you're building with Compose,
 * use [FeatureViewModel] instead.
 *
 */
interface UiState

/**
 * An empty Ui state for scenarios where no states are required.
 */
object EmptyState : UiState

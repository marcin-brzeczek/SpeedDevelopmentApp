package com.mbrzeczek.appname.ui.screen.home

import com.mbrzeczek.appname.feature.model.UiState

sealed interface HomeUiState : UiState {
    data object Loading : HomeUiState

    data class Error(
        val error: String? = null
    ) : HomeUiState

    data class Success(val pageTitle: String) : HomeUiState
}

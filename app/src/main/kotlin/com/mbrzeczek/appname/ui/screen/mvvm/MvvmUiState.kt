package com.mbrzeczek.appname.ui.screen.mvvm

import com.mbrzeczek.appname.feature.model.UiState

sealed interface MvvmUiState : UiState {
    object Loading : MvvmUiState
    object Error : MvvmUiState
    object Success : MvvmUiState
}

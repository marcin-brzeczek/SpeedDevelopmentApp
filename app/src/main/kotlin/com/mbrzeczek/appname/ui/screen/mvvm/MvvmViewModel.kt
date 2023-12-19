package com.mbrzeczek.appname.ui.screen.mvvm

import com.mbrzeczek.appname.feature.FeatureViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MvvmViewModel : FeatureViewModel<MvvmUiState, MvvmAction, MvvmNavigationTarget>() {

    val uiState = MutableStateFlow(MvvmUiState.Success)
    override val state: StateFlow<MvvmUiState>
        get() = TODO("Not yet implemented")

    override fun onAction(action: MvvmAction) {
        TODO("Not yet implemented")
    }
}

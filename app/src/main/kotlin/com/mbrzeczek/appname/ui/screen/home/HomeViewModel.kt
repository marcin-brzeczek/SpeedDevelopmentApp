package com.mbrzeczek.appname.ui.screen.home

import androidx.lifecycle.viewModelScope
import com.mbrzeczek.appname.feature.FeatureViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor() : FeatureViewModel<HomeUiState, HomeAction, HomeNavigationTarget>() {

    override val state = flowOf(HomeUiState.Success("Home Page")).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HomeUiState.Loading
    )

    override fun onAction(action: HomeAction) {
        when (action) {
            HomeAction.Next -> viewModelScope.launch {
                navigateTo(HomeNavigationTarget.Next)
            }
        }
    }
}

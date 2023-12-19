package com.mbrzeczek.appname.ui.screen.orbitMVI

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ObrbitMVIViewModel : ContainerHost<OrbitMVIUiState, OrbitMVISideEffects>, ViewModel() {

    override val container = container<OrbitMVIUiState, OrbitMVISideEffects>(
        initialState = OrbitMVIUiState.Initial
    ) {
        loadData()
    }

    private fun loadData() = intent {
        delay(1000L)
        reduce {
            state.copy(
                isLoading = false
            )
        }
    }
}

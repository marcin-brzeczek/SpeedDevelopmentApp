package com.mbrzeczek.appname.ui.screen.orbitMVI

data class OrbitMVIUiState(
    val isLoading: Boolean
) {

    companion object {
        val Initial = OrbitMVIUiState(isLoading = true)
    }
}

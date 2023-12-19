package com.mbrzeczek.appname.ui.screen.orbitMVI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mbrzeczek.appname.ui.util.components.loading.LoadingScreen
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun OrbitMVIPage( // todo marcin method naming should be independent of generated destination route
    navigator: OrbitMVINavigator,
    viewModel: ObrbitMVIViewModel = hiltViewModel()
) {
    val uiState by viewModel.container.stateFlow.collectAsStateWithLifecycle()

    OrbitMVIPage(
        uiState = uiState,
    )

    LaunchedEffect(Unit) {
        viewModel.container.sideEffectFlow.collect { sideEffect ->
            when (sideEffect) {
                MoveTo3Page -> {
                    navigator.navigate(OrbitMVINavigationTarget.Next)
                }
            }
        }
    }
}

@Composable
private fun OrbitMVIPage(
    uiState: OrbitMVIUiState,
    modifier: Modifier = Modifier
) {
    if (uiState.isLoading) {
        LoadingScreen()
    } else {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "TEST ORBIT MVI PAGE")
        }
    }
}

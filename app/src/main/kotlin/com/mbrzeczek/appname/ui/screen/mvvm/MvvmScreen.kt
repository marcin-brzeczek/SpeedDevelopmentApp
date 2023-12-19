package com.mbrzeczek.appname.ui.screen.mvvm

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

data class MvvmPageArgs(
    val test: String,
    val testOptional: Boolean? = null
)

@Destination(
    navArgsDelegate = MvvmPageArgs::class
)
@Composable
fun MvvmPage(
    navigator: MvvmNavigator,
    viewModel: MvvmViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MvvmPage(
        uiState = uiState,
    )

    LaunchedEffect(Unit) {
        viewModel.navigation.collect {
            navigator.navigate(it)
        }
    }
}

@Composable
private fun MvvmPage(
    uiState: MvvmUiState,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        MvvmUiState.Error -> {
        }
        MvvmUiState.Loading -> LoadingScreen()

        MvvmUiState.Success -> {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "TEST PAGE")
            }
        }
    }
}

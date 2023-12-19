package com.mbrzeczek.appname.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mbrzeczek.appname.ui.util.components.loading.LoadingScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = true)
@Destination
@Composable
internal fun HomePage(
    navigator: HomeNavigator,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    HomePage(
        uiState = uiState,
        onAction = viewModel::onAction,
    )

    LaunchedEffect(Unit) {
        viewModel.navigation.collect {
            navigator.navigate(it)
        }
    }
}

@Composable
private fun HomePage(
    uiState: HomeUiState,
    onAction: (HomeAction) -> Unit,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        HomeUiState.Loading -> LoadingScreen()

        is HomeUiState.Success -> {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(uiState.pageTitle)
                Spacer(Modifier.height(50.dp))
                Button(onClick = {
                    onAction(HomeAction.Next)
                }) {
                    Text(text = "NEXT")
                }
            }
        }
        is HomeUiState.Error -> Unit
    }
}

@Preview
@Composable
private fun HomeScreenLoadingPreview() {
    HomePage(
        onAction = {},
        uiState = HomeUiState.Loading,
    )
}

@Preview
@Composable
private fun HomeScreenContentPreview() {
    HomePage(
        onAction = {},
        uiState = HomeUiState.Success("Home"),
    )
}

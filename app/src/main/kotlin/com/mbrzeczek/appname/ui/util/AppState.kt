package com.mbrzeczek.appname.ui.util

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mbrzeczek.appname.navigation.NavGraphs
import com.mbrzeczek.appname.navigation.appCurrentDestinationAsState
import com.mbrzeczek.appname.navigation.appDestination
import com.mbrzeczek.appname.navigation.destinations.Destination
import com.mbrzeczek.appname.navigation.startAppDestination
import com.mbrzeczek.appname.util.SnackbarController
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): AppState {
    return remember(navController, snackbarHostState, coroutineScope) {
        AppState(navController, snackbarHostState, coroutineScope)
    }
}

// Controls app state. Stable -> if any of the values is changed, the Composables are recomposed
@Stable
class AppState(
    val navController: NavHostController,
    val snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope,
    val snackbarController: SnackbarController = SnackbarController(
        snackbarHostState,
        coroutineScope
    )
) {
    /**
     * App's current [Destination] if set, otherwise starting destination.
     *
     * Starting destination: search for `@RootNavGraph(start = true)`
     */
    val currentDestination: Destination
        @Composable get() = navController.appCurrentDestinationAsState().value
            ?: NavGraphs.root.startAppDestination

    /**
     * App's previous destination if set, otherwise null
     */
    val prevDestination: Destination?
        @Composable get() = navController.previousBackStackEntry?.appDestination()

    fun goBack() {
        navController.navigateUp()
    }
}

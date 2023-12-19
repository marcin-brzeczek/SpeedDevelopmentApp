package com.mbrzeczek.appname.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.scope.DestinationScopeWithNoDependencies
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

@Composable
internal fun AppNavHost(
    navController: NavHostController,
    navGraph: NavGraphSpec,
    startRoute: Route,
    onNavigate: (AppNavigationTarget) -> Unit,
    modifier: Modifier = Modifier,
) {
    @Composable
    fun DestinationScopeWithNoDependencies<*>.createNavigator(): AppNavigator {
        return AppNavigator(
            navController = navController,
        )
    }

    DestinationsNavHost(
        modifier = modifier,
        navGraph = navGraph,
        startRoute = startRoute,
        navController = navController,
        dependenciesContainerBuilder = {
            dependency(createNavigator())
        },
    )
}

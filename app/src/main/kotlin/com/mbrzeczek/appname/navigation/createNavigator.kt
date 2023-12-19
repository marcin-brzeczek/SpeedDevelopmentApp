package com.mbrzeczek.appname.navigation

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.scope.DestinationScopeWithNoDependencies

@Composable
fun DestinationScopeWithNoDependencies<*>.createNavigator(): AppNavigator {
    return AppNavigator(
        navController = navController,
    )
}

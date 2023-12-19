package com.mbrzeczek.appname.navigation

import com.mbrzeczek.appname.navigation.destinations.HomePageDestination
import com.mbrzeczek.appname.navigation.destinations.MvvmPageDestination
import com.mbrzeczek.appname.navigation.destinations.OrbitMVIPageDestination
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

/**
 * Nav graph containing all destinations in the app.
 */
internal object AppNavGraph : NavGraphSpec {

    val onboardingStartRoute: Route = HomePageDestination

    override val route: String = "root"

    override val startRoute: Route = onboardingStartRoute

    override val destinationsByRoute = listOf<DestinationSpec<*>>(
        HomePageDestination,
        MvvmPageDestination,
        OrbitMVIPageDestination,
    ).associateBy {
        it.route
    }
}

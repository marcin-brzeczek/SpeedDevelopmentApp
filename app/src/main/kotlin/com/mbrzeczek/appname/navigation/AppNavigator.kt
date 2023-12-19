package com.mbrzeczek.appname.navigation

import androidx.navigation.NavController
import com.mbrzeczek.appname.navigation.destinations.MvvmPageDestination
import com.mbrzeczek.appname.ui.screen.home.HomeNavigationTarget
import com.mbrzeczek.appname.ui.screen.home.HomeNavigator
import com.mbrzeczek.appname.ui.screen.mvvm.MvvmNavigationTarget
import com.mbrzeczek.appname.ui.screen.mvvm.MvvmNavigator
import com.mbrzeczek.appname.ui.screen.orbitMVI.OrbitMVINavigationTarget
import com.mbrzeczek.appname.ui.screen.orbitMVI.OrbitMVINavigator
import com.ramcosta.composedestinations.navigation.navigate

/**
 * Hosts navigation logic that coordinates across all feature modules.
 *
 * &nbsp;
 *
 * To use, first define a Navigator in your feature module. Your Navigator should be a SAM interface.
 * ```kotlin
 * // make public so `:app` can access it
 * interface OnboardingNavigator {
 *     fun navigate(target: OnboardingNavigationTarget)
 * }
 *
 * sealed interface OnboardingNavigationTarget : NavigationTarget
 * ```
 *
 * Then, implement your Navigator in AppNavigator.
 * ```kotlin
 * class AppNavigator(
 *     // [...]
 * ) : OnboardingNavigator {
 *     override fun navigate(target: OnboardingNavigationTarget) {
 *         // [...]
 *     }
 * }
 * ```
 *
 * @property navController The nav controller for performing navigation.
 * @property onAppNavigate Special navigation events delegated to the hosting activity.
 */
class AppNavigator(
    private val navController: NavController,
) : HomeNavigator,
    MvvmNavigator,
    OrbitMVINavigator { // add navigator classes here

    override fun navigate(target: HomeNavigationTarget) {
        when (target) {
            is HomeNavigationTarget.Browser -> {
            }

            HomeNavigationTarget.Next -> {
                navController.navigate(MvvmPageDestination(test = ""))
            }
        }
    }

    override fun navigate(target: MvvmNavigationTarget) {
        TODO("Not yet implemented")
    }

    override fun navigate(target: OrbitMVINavigationTarget) {
        TODO("Not yet implemented")
    }

    // endregion
}

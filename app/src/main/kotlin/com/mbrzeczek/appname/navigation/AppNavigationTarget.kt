package com.mbrzeczek.appname.navigation

import androidx.navigation.NavController

/**
 * Navigation targets to be handled by the hosting activity.
 */
internal sealed interface AppNavigationTarget {
    /**
     * Launches the browser with the provided url.
     *
     * @property url The url to launch in the browser.
     */
    data class Browser(val url: String) : AppNavigationTarget

    /**
     * Launches the phone dialer app with the provided phone number.
     *
     * @property rawPhoneNumber Phone number in the form `XXXXXXXXXX` without any delimiters.
     */
    data class Phone(val rawPhoneNumber: String) : AppNavigationTarget

    /**
     * Redirects the user to another part of the app.
     *
     * @property redirect Indicates to where the user should be redirected.
     * @property navController The NavController for performing the redirect, which should have
     * access to the entire app's nav graph.
     */
    class Redirect(
        val redirect: AppRedirect,
        val navController: NavController,
    ) : AppNavigationTarget
}

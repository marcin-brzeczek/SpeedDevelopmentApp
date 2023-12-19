package com.mbrzeczek.appname.ui.screen.orbitMVI

import com.mbrzeczek.appname.navigation.NavigationTarget

sealed interface OrbitMVINavigationTarget : NavigationTarget {
    object Next : OrbitMVINavigationTarget
    object Back : OrbitMVINavigationTarget
}

interface OrbitMVINavigator {
    fun navigate(target: OrbitMVINavigationTarget)
}

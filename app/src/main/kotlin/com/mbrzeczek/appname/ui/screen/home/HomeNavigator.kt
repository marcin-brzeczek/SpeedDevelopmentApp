package com.mbrzeczek.appname.ui.screen.home

import com.mbrzeczek.appname.navigation.NavigationTarget

sealed interface HomeNavigationTarget : NavigationTarget {
    object Next : HomeNavigationTarget
    data class Browser(val url: String) : HomeNavigationTarget
}

interface HomeNavigator {
    fun navigate(target: HomeNavigationTarget)
}

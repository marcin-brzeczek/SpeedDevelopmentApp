package com.mbrzeczek.appname.ui.screen.mvvm

import com.mbrzeczek.appname.navigation.NavigationTarget

sealed interface MvvmNavigationTarget : NavigationTarget {
    object Next : MvvmNavigationTarget
    object Back : MvvmNavigationTarget
}

interface MvvmNavigator {
    fun navigate(target: MvvmNavigationTarget)
}

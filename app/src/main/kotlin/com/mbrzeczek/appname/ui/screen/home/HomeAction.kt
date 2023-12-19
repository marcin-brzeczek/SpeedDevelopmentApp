package com.mbrzeczek.appname.ui.screen.home

import com.mbrzeczek.appname.feature.model.UiAction

internal sealed interface HomeAction : UiAction {
    object Next : HomeAction
}

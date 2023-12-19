package com.mbrzeczek.appname.navigation

import com.mbrzeczek.appname.navigation.deeplinks.DeepLinkAction

/**
 * Different ways the app can automatically redirect after the app is launched.
 */
sealed interface AppRedirect {
    data class DeepLink(
        val action: DeepLinkAction,
    ) : AppRedirect
}

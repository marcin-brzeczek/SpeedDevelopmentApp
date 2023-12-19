package com.mbrzeczek.appname.navigation.deeplinks

import androidx.navigation.NavController

/**
 * A handler for processing deep links.
 *
 * @param T The bundled args type for the deep link.
 */
interface SliideDeepLinkHandler<T : SliideDeepLinkHandler.Args> {
    interface Args

    fun handle(
        action: DeepLinkAction?,
        args: T? = null
    ): DeepLinkAction?
}

interface SliideDeepLinkNavigationHandler :
    SliideDeepLinkHandler<SliideDeepLinkNavigationHandler.Args> {
    class Args(
        val navController: NavController,
    ) : SliideDeepLinkHandler.Args
}

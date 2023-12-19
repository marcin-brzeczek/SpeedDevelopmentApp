package com.mbrzeczek.appname.navigation.deeplinks.model

import com.mbrzeczek.appname.navigation.deeplinks.DeepLinkAction

sealed class SiideDeepLinkAction(
    /**
     * This action's unique identifier. Currently only used for logging.
     */
    override val key: String
) : DeepLinkAction, KeyIdentifiable {

    override val name: String = this::class.simpleName.orEmpty()

    /**
     * For deep links whose routes are autogenerated by Compose Destinations. These can be passed
     * directly into `navController.navigate(route)`.
     */

    data class Generated(
        override val path: String
    ) : SiideDeepLinkAction(key = "generated"), DeepLinkAction.ComposeRoutable
}

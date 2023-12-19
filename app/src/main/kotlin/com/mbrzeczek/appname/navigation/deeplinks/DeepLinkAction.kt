package com.mbrzeczek.appname.navigation.deeplinks

/**
 * Abstraction for describing a deep link.
 */
interface DeepLinkAction {
    /**
     * Indicates that this [DeepLinkAction] should cache the original deeplink.
     *
     * This should be used for [DeepLinkAction]s which serve to temporarily "intercept" another
     * [DeepLinkAction]. The [Interceptable] must be completed before the original destination can
     * be presented.
     */
    interface Interceptable

    /**
     * Indicates that this [DeepLinkAction] can be routed via Compose navigation.
     */

    interface ComposeRoutable {
        val path: String
    }
}

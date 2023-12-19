package com.mbrzeczek.appname.navigation.deeplinks.handler

import androidx.navigation.NavController
import com.mbrzeczek.appname.navigation.deeplinks.SliideDeepLinkNavigationHandler
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.Direction
import timber.log.Timber
import javax.inject.Inject

class DefaultSliideDeepLinkNavigationHandler @Inject constructor() : SliideDeepLinkNavigationHandler {

    private class Destination(
        val destination: Direction,
    )

    override fun handle(
        action: com.mbrzeczek.appname.navigation.deeplinks.DeepLinkAction?,
        args: SliideDeepLinkNavigationHandler.Args?
    ): com.mbrzeczek.appname.navigation.deeplinks.DeepLinkAction? {
        if (args == null) throw IllegalArgumentException("Can't handle $action. Args must not be null!")

        if (action !is com.mbrzeczek.appname.navigation.deeplinks.model.SiideDeepLinkAction) return null

        if (action is com.mbrzeczek.appname.navigation.deeplinks.DeepLinkAction.ComposeRoutable) {
            val didNavigate = pathNavigate(
                navController = args.navController,
                path = action.path,
                key = action.key,
            )
            return if (didNavigate) action else null
        }

        val destinations = buildList<Destination> {
            when (action) {
                else -> {
                }
            }
        }.onEach {
            args.navController.navigate(
                direction = it.destination
            )
        }

        return if (destinations.isNotEmpty()) action else null
    }

    private fun pathNavigate(
        navController: NavController,
        path: String,
        key: String,
    ): Boolean {
        // Note - This method does not currently support launching in a "parent"
        // (synthetic stack), or launching with preferred presentation.
        // Keeping note for future reference.
        val didNavigate = try {
            navController.navigate(route = path)
            true
        } catch (e: IllegalArgumentException) {
            // path is invalid
            false
        }

        log(
            success = didNavigate,
            key = key,
            path = path
        )

        return didNavigate
    }

    private fun log(success: Boolean, key: String, path: String? = null) {
        if (success) {
            Timber.d("navigation success: $key : $path")
        } else {
            Timber.d("navigation failure: $key : $path")
        }
    }
}

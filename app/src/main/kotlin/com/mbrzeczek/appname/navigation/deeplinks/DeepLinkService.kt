package com.mbrzeczek.appname.navigation.deeplinks
import android.content.Intent
import androidx.navigation.NavController
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Responsible for parsing and handling incoming deep links.
 */
interface DeepLinkServiceable {
    var launchAction: DeepLinkAction?
    var cache: DeepLinkAction?

    /**
     * Converts incoming deep links from their raw formats into a neutralized (application-defined)
     * model.
     *
     * @param input The deep link in its raw form.
     * @return A [DeepLinkAction] that describes the deep link .
     */
    suspend fun parse(input: Any): DeepLinkAction?

    /**
     * Processes an incoming deep link.
     *
     * @return `true` if the [action] was successfully handled.
     */
    fun handle(
        action: DeepLinkAction,
        navController: NavController,
    ): Boolean
}

/**
 * Special implementation that supports multiple [DeepLinkParser] and [SliideDeepLinkHandler]
 * implementations.
 */
@Singleton
class DeepLinkService @Inject constructor(
    private val parsers: Array<DeepLinkParser<*>>,
    private val handlers: Array<SliideDeepLinkHandler<*>>,
) : DeepLinkServiceable {
    override var launchAction: DeepLinkAction? = null

    /**
     * Session-scoped cache. This does not persist across app restarts.
     */
    override var cache: DeepLinkAction? = null

    @Suppress("UNCHECKED_CAST")
    override suspend fun parse(input: Any): DeepLinkAction? {
        return when (input) {
            is Intent -> {
                parse(
                    input,
                    parsers = parsers.filter { it.ref is Intent } as List<DeepLinkParser<Intent>>
                )
            }

            is JSONObject -> {
                parse(
                    input,
                    parsers = parsers.filter { it.ref is JSONObject } as List<DeepLinkParser<JSONObject>>
                )
            }

            else -> {
                throw IllegalArgumentException("Input provided is not supported!")
            }
        }
    }

    private suspend fun <T> parse(input: T, parsers: List<DeepLinkParser<T>>): DeepLinkAction? {
        parsers.forEach {
            val result = it.parse(input)
            if (result != null) return result
        }

        return null
    }

    override fun handle(
        action: DeepLinkAction,
        navController: NavController
    ): Boolean {
        val finalAction = handlers.fold(action as DeepLinkAction?) { resolvedAction, next ->
            when (next) {
                is SliideDeepLinkNavigationHandler -> {
                    next.handle(
                        action = resolvedAction,
                        args = SliideDeepLinkNavigationHandler.Args(
                            navController = navController,
                        )
                    )
                }

                else -> {
                    next.handle(resolvedAction)
                }
            }
        }

        cache = when {
            action != finalAction && finalAction is DeepLinkAction.Interceptable -> action
            else -> null
        }

        return finalAction != null
    }
}

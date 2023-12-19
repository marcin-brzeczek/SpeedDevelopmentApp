package com.mbrzeczek.appname.navigation.deeplinks

/**
 * Parses an input of some type [T] into a [DeepLinkAction].
 *
 * @param T The input type.
 */
interface DeepLinkParser<T> {
    /**
     * Hack to getting type of `T` in lists due to Kotlin type erasure from `List<DeepLinkParser<*>>`
     */
    val ref: T

    suspend fun parse(input: T): DeepLinkAction?
}

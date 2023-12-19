package com.mbrzeczek.appname.navigation.deeplinks.parser

import android.content.Intent
import android.content.UriMatcher
import android.net.Uri
import com.mbrzeczek.appname.navigation.deeplinks.DeepLinkParser
import com.mbrzeczek.appname.navigation.deeplinks.model.SiideDeepLinkAction
import javax.inject.Inject

/**
 * Parses `sliide://` deeplinks
 */
class SliideDeepLinkParser @Inject constructor() : DeepLinkParser<Intent> {
    companion object {
        private const val SCHEME = "sliide"

        private const val GENERATED = "generated"
    }

    // paths and queries not currently supported
    private enum class Link(val key: String) {
        BROWSE(key = "browse");

        companion object {
            fun from(key: String): Link? =
                Link.values().firstOrNull { it.key.equals(key, ignoreCase = true) }
        }
    }

    override val ref: Intent = Intent()

    override suspend fun parse(input: Intent): SiideDeepLinkAction? {
        if (input.isSliideDeepLink().not()) return null

        val data = input.data!!

        return when (data.getType()) {
            Type.GENERATED -> {
                val path = input.data?.toString()?.substringAfter("$SCHEME://$GENERATED/")
                    ?: return null
                SiideDeepLinkAction.Generated(path = path)
            }
        }
    }

    private fun Intent.isSliideDeepLink(): Boolean {
        return data != null && scheme == SCHEME
    }

    private enum class Type(val code: Int) {

        GENERATED(0);

        companion object {
            fun fromCode(code: Int): Type =
                values().find { it.code == code } ?: GENERATED
        }
    }

    private fun Uri.getType(): Type {
        if (this.host == GENERATED) return Type.GENERATED

        val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        return Type.fromCode(uriMatcher.match(this))
    }
}

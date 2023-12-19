package com.mbrzeczek.appname.di

import android.content.Intent
import com.mbrzeczek.appname.navigation.deeplinks.DeepLinkParser
import com.mbrzeczek.appname.navigation.deeplinks.DeepLinkService
import com.mbrzeczek.appname.navigation.deeplinks.DeepLinkServiceable
import com.mbrzeczek.appname.navigation.deeplinks.SliideDeepLinkHandler
import com.mbrzeczek.appname.navigation.deeplinks.handler.DefaultSliideDeepLinkNavigationHandler
import com.mbrzeczek.appname.navigation.deeplinks.parser.DeepLinkParserQualifier
import com.mbrzeczek.appname.navigation.deeplinks.parser.SliideDeepLinkParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DeepLinkModule {

    // region handlers

    @[Provides Singleton]
    fun handlers(
        rewriteNavigation: DefaultSliideDeepLinkNavigationHandler,
    ): Array<SliideDeepLinkHandler<*>> = arrayOf(
        rewriteNavigation,
    )

    @[Provides Singleton DeepLinkParserQualifier.Sliide]
    fun sliideParser(impl: SliideDeepLinkParser): DeepLinkParser<Intent> = impl

    @[Provides Singleton]
    fun parsers(
        @DeepLinkParserQualifier.Sliide sliideParser: DeepLinkParser<Intent>,

    ): Array<DeepLinkParser<*>> = arrayOf(
        sliideParser,
    )

    @[Provides Singleton]
    fun deepLinkService(impl: DeepLinkService): DeepLinkServiceable = impl
}

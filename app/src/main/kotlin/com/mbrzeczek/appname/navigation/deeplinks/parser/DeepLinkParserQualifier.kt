package com.mbrzeczek.appname.navigation.deeplinks.parser

import javax.inject.Qualifier

object DeepLinkParserQualifier {
    @[Qualifier Retention(AnnotationRetention.BINARY)]
    annotation class RemoteMessage

    @[Qualifier Retention(AnnotationRetention.BINARY)]
    annotation class Web

    @[Qualifier Retention(AnnotationRetention.BINARY)]
    annotation class Sliide
}

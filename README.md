<div align="center">
  <a href="https://developer.android.com/jetpack/compose">
    <img src="https://tabris.com/wp-content/uploads/2021/06/jetpack-compose-icon_RGB.png" alt="Jetpack Compose logo" title="Jetpack Compose logo" width="200" height="200" />
  </a>
</div>

# Android Jetpack Compose template
- Composable destinations from Ramcosta to reduce boilerplate code related to writing navigation routes.
- Deepling mechanism for launching each composable page regardless of navigation
  - for using deeplinks run the command line adb shell 'am start -d "sliide://generated/orbit_m_v_i_page"'

## What does it use?
- [Kotlin](https://kotlinlang.org/) as main language
- [Jetpack Compose](https://developer.android.com/jetpack/compose) as modern toolkit for native UI
- [Material 3 components for Jetpack Compose](https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#top-level-functions) to build UI faster
- [Room](https://developer.android.com/training/data-storage/room) as local persistent DB
- [Retrofit+OkHttp](https://square.github.io/retrofit/): for api calls
- [Proto Datastore](https://developer.android.com/topic/libraries/architecture/datastore?gclid=CjwKCAjwkYGVBhArEiwA4sZLuMMCRUnWZzzy-AwDePYTUTn3gO6-rrT8jGo7D-H2vztegIJ-zEsb8hoCtI8QAvD_BwE&gclsrc=aw.ds) as typesafe data storage solution with protocol buffers support
- [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for Dependency Injection
- [Accompanist](https://google.github.io/accompanist/) a group of libraries that aim to supplement Jetpack Compose with features that are commonly required by developers but not yet available
  - [System UI Controller](https://google.github.io/accompanist/systemuicontroller/) to change status and navigation bar colors
- [Detekt](https://detekt.dev/) for static code analysis and formatting
- [Timber](https://github.com/JakeWharton/timber) for logging
- [Eva icons](https://github.com/DevSrSouza/compose-icons/blob/master/eva-icons/DOCUMENTATION.md) for the icons to use throughout the whole app
- [Coil](https://coil-kt.github.io/coil/compose/) for image loading backed by Kotlin coroutines
- [Splashscreen API](https://developer.android.com/develop/ui/views/launch/splash-screen) to display a splashscreen at app launch
- [Per-app language preferences](https://developer.android.com/guide/topics/resources/app-languages) to use a language inside the app that is different from the system language
- [Compose Destinations](https://composedestinations.rafaelcosta.xyz/) for easier app navigation

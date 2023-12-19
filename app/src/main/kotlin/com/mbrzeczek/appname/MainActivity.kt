package com.mbrzeczek.appname

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import com.mbrzeczek.appname.navigation.AppRedirect
import com.mbrzeczek.appname.navigation.NavGraphs
import com.mbrzeczek.appname.navigation.createNavigator
import com.mbrzeczek.appname.navigation.deeplinks.DeepLinkServiceable
import com.mbrzeczek.appname.ui.theme.AppMaterialTheme
import com.mbrzeczek.appname.ui.theme.custom.LocalPadding
import com.mbrzeczek.appname.ui.theme.custom.padding
import com.mbrzeczek.appname.ui.util.rememberAppState
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var deepLinkService: DeepLinkServiceable

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.initialize(intent)

        // Turn off the decor fitting system windows, which allows us to handle insets,
        // including IME animations
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            CompositionLocalProvider(LocalPadding provides padding) {
                AppMaterialTheme() {
                    val appState = rememberAppState()

                    LaunchedEffect(Unit) {
                        viewModel.redirect.collect { appRedirect ->
                            redirect(appRedirect, appState.navController)
                        }
                    }

                    Scaffold(
                        snackbarHost = { SnackbarHost(appState.snackbarHostState) },
                        contentWindowInsets = WindowInsets(0, 0, 0, 0)
                    ) { padding ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(padding)
                                .consumeWindowInsets(padding)
                                .windowInsetsPadding(
                                    WindowInsets.safeDrawing.only(
                                        WindowInsetsSides.Horizontal
                                    )
                                )
                        ) {
                            DestinationsNavHost(
                                navGraph = NavGraphs.root,
                                navController = appState.navController,
                                dependenciesContainerBuilder = {
                                    dependency(createNavigator())
                                },
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        viewModel.initialize(intent)
        viewModel.redirectIfNeeded(force = true)
    }

    private fun redirect(redirect: AppRedirect, navController: NavController) {
        when (redirect) {
            is AppRedirect.DeepLink -> {
                deepLinkService.handle(
                    action = redirect.action,
                    navController = navController,
                )
            }
        }
    }
}

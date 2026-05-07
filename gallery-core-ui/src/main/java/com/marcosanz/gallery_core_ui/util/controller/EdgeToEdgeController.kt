package com.marcosanz.gallery_core_ui.util.controller

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.LocalActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.window.DialogWindowProvider
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

object EdgeToEdgeController {

    enum class Mode {
        LIGHT, DARK
    }

    var currentStatusBarMode: Mode = Mode.LIGHT
    var currentNavigationBarMode: Mode = Mode.LIGHT

    private val statusBarLight = SystemBarStyle.Companion.light(Color.TRANSPARENT, Color.BLACK)
    private val statusBarDark = SystemBarStyle.Companion.dark(Color.TRANSPARENT)

    private val navigationBarLight = SystemBarStyle.Companion.light(
        Color.TRANSPARENT,
        Color.TRANSPARENT
    )
    private val navigationBarDark = SystemBarStyle.Companion.dark(
        Color.TRANSPARENT
    )

    private fun getStatusBarStyle(mode: Mode) =
        when (mode) {
            Mode.LIGHT -> statusBarLight
            Mode.DARK -> statusBarDark
        }

    private fun getNavigationBarStyle(mode: Mode) =
        when (mode) {
            Mode.LIGHT -> navigationBarLight
            Mode.DARK -> navigationBarDark
        }

    fun enableEdgeToEdge(activity: ComponentActivity, mode: Mode = Mode.LIGHT) {
        enableEdgeToEdge(activity = activity, statusBarMode = mode, navigationBarMode = mode)
    }

    fun enableEdgeToEdge(
        activity: ComponentActivity,
        statusBarMode: Mode = Mode.LIGHT,
        navigationBarMode: Mode = Mode.LIGHT
    ) {
        activity.enableEdgeToEdge(
            statusBarStyle = getStatusBarStyle(statusBarMode),
            navigationBarStyle = getNavigationBarStyle(navigationBarMode)
        )
        currentStatusBarMode = statusBarMode
        currentNavigationBarMode = navigationBarMode
    }

    @Composable
    fun SystemBarsVisibility(
        visible: Boolean,
        resetOnDispose: Boolean = true
    ) {
        val view = LocalView.current
        val window = remember(view) {
            val dialogWindow = (view.parent as? DialogWindowProvider)?.window
            dialogWindow ?: view.context.findActivity()?.window
        } ?: return

        val controller = remember(window, view) {
            WindowCompat.getInsetsController(window, view)
        }

        DisposableEffect(visible) {
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

            if (visible) {
                controller.show(WindowInsetsCompat.Type.systemBars())
            } else {
                controller.hide(WindowInsetsCompat.Type.systemBars())
            }

            onDispose {
                if (resetOnDispose) {
                    controller.show(WindowInsetsCompat.Type.systemBars())
                }
            }
        }
    }

    private fun Context.findActivity(): Activity? {
        var context = this
        while (context is ContextWrapper) {
            if (context is Activity) return context
            context = context.baseContext
        }
        return null
    }

    /**
     * Adjusts the statusBar and navigationBar colors of the current Activity.
     *
     * @param statusBarMode StatusBar color mode.
     * @param navigationBarMode NavigationBar color mode.
     * @param keep If you should be kept the previous mode or not.
     *
     */
    @Composable
    fun SetEdgeToEdgeStyle(
        statusBarMode: Mode = Mode.LIGHT,
        navigationBarMode: Mode = Mode.LIGHT,
        keep: Boolean = false
    ) {
        val ogStatusMode = currentStatusBarMode
        val ogNavigationMode = currentNavigationBarMode
        val activity = (LocalActivity.current as? AppCompatActivity) ?: return

        DisposableEffect(activity, statusBarMode, navigationBarMode, keep) {
            enableEdgeToEdge(
                activity = activity,
                statusBarMode = statusBarMode,
                navigationBarMode = navigationBarMode
            )
            onDispose {
                if (!keep)
                    enableEdgeToEdge(
                        activity = activity,
                        statusBarMode = ogStatusMode,
                        navigationBarMode = ogNavigationMode
                    )
            }
        }
    }

}
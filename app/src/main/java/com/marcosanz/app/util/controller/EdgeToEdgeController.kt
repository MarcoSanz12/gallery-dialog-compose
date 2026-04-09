package com.marcosanz.app.util.controller

import android.graphics.Color
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.LocalActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect

object EdgeToEdgeController {

    enum class Mode {
        LIGHT, DARK
    }

    var currentStatusBarMode: Mode = Mode.LIGHT
    var currentNavigationBarMode: Mode = Mode.LIGHT

    private val statusBarLight = SystemBarStyle.light(Color.TRANSPARENT, Color.BLACK)
    private val statusBarDark = SystemBarStyle.dark(Color.TRANSPARENT)

    private val navigationBarLight = SystemBarStyle.light(
        Color.TRANSPARENT,
        Color.TRANSPARENT
    )
    private val navigationBarDark = SystemBarStyle.dark(
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

    /**
     * Ajusta los colores de statusBar y navigationBar de la Activity actual.
     *
     * @param mode Modo de colores a aplicar.
     * @param keep true Sí se debe mantener el modo actual de colores, false si debe volver al previo
     */
    @Composable
    fun SetEdgeToEdgeStyle(mode: Mode = Mode.LIGHT, keep: Boolean = false) {
        SetEdgeToEdgeStyle(
            statusBarMode = mode,
            navigationBarMode = mode,
            keep = keep
        )
    }

    /**
     * Ajusta los colores de statusBar y navigationBar de la Activity actual.
     *
     * @param statusBarMode Modo de colores a aplicar
     * @param navigationBarMode Modo de colores a aplicar
     * @param keep true Sí se debe mantener el modo actual de colores, false si debe volver al previo
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
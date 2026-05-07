package com.marcosanz.gallery_core_ui.util.controller

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import android.content.res.Configuration

@SuppressLint("SourceLockedOrientationActivity")
object OrientationController {

    fun Context.findActivity(): Activity? {
        var context = this
        while (context is ContextWrapper) {
            if (context is Activity) return context
            context = context.baseContext
        }
        return null
    }

    fun isLandscape(context: Context): Boolean {
        return context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    }

    fun setOrientation(context: Context, orientation: Int) {
        val activity = context.findActivity() ?: return
        activity.requestedOrientation = orientation
    }

    fun setOrientationPortrait(context: Context) {
        val activity = context.findActivity() ?: return
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT
    }

    fun setOrientationLandscape(context: Context) {
        val activity = context.findActivity() ?: return
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE
    }

    fun getRequestedOrientation(context: Context): Int {
        return context.findActivity()?.requestedOrientation ?: ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    }

    fun toggleOrientation(context: Context) {
        if (isLandscape(context = context))
            setOrientationPortrait(context = context)
        else
            setOrientationLandscape(context = context)
    }
}
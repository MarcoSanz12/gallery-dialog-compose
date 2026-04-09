package com.marcosanz.app.core.ui.component.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.marcosanz.app.core.ui.theme.defaults.AppPadding

@Composable
fun SpacerSmall(size: Dp = AppPadding.small) {
    MySpacer(size)
}

@Composable
fun SpacerMedium(size: Dp = AppPadding.medium) {
    MySpacer(size)
}

@Composable
fun SpacerLarge(size: Dp = AppPadding.large) {
    MySpacer(size)
}

@Composable
fun SpacerExtraLarge(size: Dp = AppPadding.extraLarge) {
    MySpacer(size)
}

@Composable
internal fun MySpacer(size: Dp) {
    Spacer(modifier = Modifier.size(size))
}
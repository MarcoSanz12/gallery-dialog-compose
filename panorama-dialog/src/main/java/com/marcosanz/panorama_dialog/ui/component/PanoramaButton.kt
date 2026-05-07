package com.marcosanz.panorama_dialog.ui.component


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.marcosanz.gallery_core_ui.theme.PreviewScaffold
import com.marcosanz.gallery_core_ui.ui.BasicRoundButton
import com.marcosanz.panorama_dialog.R

@Preview
@Composable
private fun PanoramaButtonsPreview() {
    PreviewScaffold {
    }
}

@Composable
internal fun SensorialRotationButton(
    modifier: Modifier = Modifier,
    isVisible: Boolean = true,
    enabled : Boolean = true,
    onClick: () -> Unit
) {
    BasicRoundButton(
        modifier = modifier,
        isVisible = isVisible,
        enabled = enabled,
        iconScale = 0.95f,
        icon = painterResource(R.drawable.ic_gallery_dialog_sensorial_rotation),
        contentDescription = stringResource(R.string.gallery_dialog_cd_sensorial_rotation),
        onClick = onClick
    )
}
package com.marcosanz.gallery_dialog.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.marcosanz.gallery_core_ui.theme.PreviewScaffold
import com.marcosanz.gallery_core_ui.ui.BasicRoundButton
import com.marcosanz.gallery_dialog.R

@Preview
@Composable
private fun GalleryButtonsPreview() {
    PreviewScaffold() {
        // More
        Text(text = "More button")
        GalleryMoreOptionsButton() { }
    }
}



@Composable
internal fun GalleryMoreOptionsButton(
    modifier: Modifier = Modifier,
    isVisible: Boolean = true,
    onClick: () -> Unit
) {
    BasicRoundButton(
        modifier = modifier,
        isVisible = isVisible,
        icon = painterResource(R.drawable.ic_gallery_dialog_more),
        contentDescription = stringResource(R.string.gallery_dialog_cd_more_button),
        onClick = onClick
    )
}
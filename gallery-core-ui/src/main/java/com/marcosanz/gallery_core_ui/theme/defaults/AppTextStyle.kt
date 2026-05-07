package com.marcosanz.gallery_core_ui.theme.defaults

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.marcosanz.gallery_core_ui.theme.PreviewScaffold

@Preview
@Composable
private fun TextStylePreview() {
    PreviewScaffold() {
        PreviewText(
            text = "Body",
            style = AppTextStyle.Body.body
        )
        PreviewText(
            text = "Button\nNormal",
            style = AppTextStyle.Button.button
        )
    }
}

@Composable
private fun PreviewText(
    text: String,
    style: TextStyle,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = text,
        style = style
    )
}

object AppTextStyle {

    object Body {
        val body = TextStyle(
            fontSize = AppTextSize.body,
            color = Color.Black,
            fontWeight = FontWeight.Normal,
            lineHeight = AppTextSize.body * 1.25,
        )
    }

    object Button {
        val button = TextStyle(
            fontSize = AppTextSize.button,
            color = Color.Black,
            fontWeight = FontWeight.Normal,
            lineHeight = 16.sp,
        )
    }
}


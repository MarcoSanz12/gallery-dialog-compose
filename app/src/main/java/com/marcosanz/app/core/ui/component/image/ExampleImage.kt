package com.marcosanz.app.core.ui.component.image

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.marcosanz.app.R
import com.marcosanz.app.core.ui.component.common.SpacerLarge
import com.marcosanz.app.core.ui.component.common.SpacerMedium
import com.marcosanz.core_ui.theme.PreviewScaffold
import com.marcosanz.core_ui.theme.defaults.AppPadding
import com.marcosanz.gallery_dialog.GalleryItem

@Preview
@Composable
private fun ExampleImagePreview() {
    PreviewScaffold {
        ExampleImageRow(
            items = listOf(
                GalleryItem(
                    model = R.drawable.drawable_cuellar1,
                    description = "Front view of the Cuéllar Castle, Valladolid, Spain"
                ),
                GalleryItem(
                    model = R.drawable.drawable_cuellar2,
                    description = "Walls of the castle"
                ),
                GalleryItem(
                    model = R.drawable.drawable_cuellar3,
                    description = "Beautiful lake near the village of Cuéllar, its an important reservoir for all kind of species, including fishes and birds. Beware the mosquitoes, bile creatures"
                ),
                GalleryItem(
                    model = R.drawable.drawable_cuellar4,
                    description = buildString {
                        repeat(100) {
                            append(it)
                        }
                    }
                )
            ),
            title = "ExampleImageRow",
            modifier = Modifier.height(200.dp),
            onGalleryItemClick = {}
        )
    }
}


@Suppress("MutableParameter")
@Composable
fun ExampleImage(
    item: GalleryItem,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource? = null,
    onClick: () -> Unit
) {
    val interactionSource = remember(interactionSource) {
        interactionSource ?: MutableInteractionSource()
    }
    val isPressed = interactionSource.collectIsPressedAsState()
    val animatedScale by animateFloatAsState(
        targetValue = if (isPressed.value) 0.9f else 1f
    )

    AsyncImage(
        model = item.model,
        contentDescription = item.description,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .graphicsLayer {
                scaleX = animatedScale
                scaleY = animatedScale
            }
            .aspectRatio(16f / 9f, matchHeightConstraintsFirst = true)
            .clickable(
                onClick = onClick,
                interactionSource = interactionSource
            )
    )
}

@Composable
fun ExampleImageRow(
    items: List<GalleryItem>,
    title: String,
    modifier: Modifier = Modifier,
    imagesContentPadding: PaddingValues = PaddingValues(horizontal = AppPadding.large),
    onGalleryItemClick: (GalleryItem) -> Unit
) {

    Column(modifier = modifier
        .fillMaxWidth()
        .height(200.dp)) {
        // Title
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(imagesContentPadding).fillMaxWidth()
        )
        SpacerMedium()
        // Images
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
                .drawWithContent {
                    drawContent()
                    val fadeWidth = 20.dp.toPx()
                    drawRect(
                        brush = Brush.horizontalGradient(
                            0f to Color.Transparent,
                            fadeWidth / size.width to Color.Black,
                            (size.width - fadeWidth) / size.width to Color.Black,
                            1f to Color.Transparent
                        ),
                        blendMode = BlendMode.DstIn
                    )
                },
            contentPadding = imagesContentPadding,
        ) {
            items(
                items = items.toList(),
                key = { it.model }
            ) {
                ExampleImage(
                    item = it,
                    onClick = { onGalleryItemClick(it) }
                )
                SpacerLarge()
            }
        }
    }
}
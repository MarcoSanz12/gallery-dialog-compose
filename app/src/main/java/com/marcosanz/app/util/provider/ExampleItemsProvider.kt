package com.marcosanz.app.util.provider

import com.marcosanz.app.R
import com.marcosanz.gallery_dialog.GalleryItem

object ExampleItemsProvider {
    // GalleryItems with Drawables
    val drawableItems = listOf(
        GalleryItem(
            model = R.drawable.drawable_cuellar1,
            description = "Drawable - Front view of the Cuéllar Castle, Valladolid, Spain"
        ),
        GalleryItem(
            model = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSPc7FmzOQ6GRuFgJBOBO2IZqu7vcPdF3zg4w&s",
            description = "URL - Guillermo Diaz Ibañez"
        ),
        GalleryItem(
            model = R.drawable.drawable_cuellar2,
            description = "Drawable - Walls of the castle"
        ),
        GalleryItem(
            model = "https://www.nonexistantpage777.ru/nonimage",
            description = "URL - Non existant image, should load error"
        ),
        GalleryItem(
            model = R.drawable.drawable_cuellar3,
            description = "Drawable - Beautiful lake near the village of Cuéllar, its an important reservoir for all kind of species, including fishes and birds. Beware the mosquitoes, bile creatures"
        ),
        GalleryItem(
            model = R.drawable.drawable_cuellar4,
            description = buildString {
                repeat(100) {
                    append(it)
                }
            }
        ),
        GalleryItem(
            model = R.drawable.drawable_cuellar5,
            description = null
        ),
        GalleryItem(
            model = R.drawable.drawable_cuellar6,
            description = "Drawable - Line1\nLine2\nLine3\nLine4\nLine5\nLine6\nLine7\nLine8\nLine9\nLine10"
        )
    )

    val allLists = listOf(
        drawableItems
    )

}
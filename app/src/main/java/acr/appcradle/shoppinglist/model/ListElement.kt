package acr.appcradle.shoppinglist.model

import androidx.compose.ui.graphics.Color

data class ListElement(
    val id: Long,
    val icon: Int,
    val iconBackground: Color,
    val listName: String,
    val boughtCount: Int,
    val totalCount: Int,
)


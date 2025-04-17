package acr.appcradle.shoppinglist.model

import acr.appcradle.shoppinglist.ShoppingList
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class ListElement(
    val icon: ImageVector,
    val iconBackground: Color,
    val listName: String,
    val boughtCount: Int,
    val totalCount: Int,
)


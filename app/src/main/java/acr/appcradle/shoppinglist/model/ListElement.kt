package acr.appcradle.shoppinglist.model

import acr.appcradle.shoppinglist.ShoppingList
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class ListElement(
    val id: Int?,
    val icon: ImageVector,
    val iconBackground: Color,
    val listName: String,
    val boughtCount: Int,
    val totalCount: Int,
)

fun ShoppingList.toListElement(): ListElement {
    return ListElement(
        id = null,
        icon = Icons.Default.ShoppingCart,
        iconBackground = Color(0xFF64B5F6),
        listName = "",
        boughtCount = 0,
        totalCount = 0
    )
}

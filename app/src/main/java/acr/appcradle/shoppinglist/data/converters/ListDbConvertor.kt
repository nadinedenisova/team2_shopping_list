package acr.appcradle.shoppinglist.data.converters

import acr.appcradle.shoppinglist.ShoppingList
import acr.appcradle.shoppinglist.model.ListElement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.Color
import javax.inject.Inject

class ListDbConvertor @Inject constructor() {

    fun map (listElement: ListElement) : ShoppingList {
        return ShoppingList(
            id = null!!,
            iconId = Icons.Default.ShoppingCart.toString(),
            iconColorHex = Color(0xFF64B5F6).toString(),
            listName = listElement.listName,
            boughtCount = listElement.boughtCount.toLong(),
            totalCount = listElement.totalCount.toLong()
        )
    }

    fun map (shoppingList: ShoppingList) : ListElement {
        return ListElement(
            icon = Icons.Default.ShoppingCart,
            iconBackground = Color(0xFF64B5F6),
            listName = shoppingList.listName,
            boughtCount = shoppingList.boughtCount.toInt(),
            totalCount = shoppingList.totalCount.toInt()
        )
    }


}
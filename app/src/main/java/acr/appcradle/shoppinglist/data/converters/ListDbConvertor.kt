package acr.appcradle.shoppinglist.data.converters

import acr.appcradle.shoppinglist.ShoppingList
import acr.appcradle.shoppinglist.model.ListElement
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import javax.inject.Inject

class ListDbConvertor @Inject constructor() {

    fun map(listElement: ListElement): ShoppingList {
        return ShoppingList(
            id = null!!,
            icon = listElement.icon.toLong(),
            iconBackground = listElement.iconBackground.toLong(),
            listName = listElement.listName,
            boughtCount = listElement.boughtCount.toLong(),
            totalCount = listElement.totalCount.toLong()
        )
    }

    fun map(shoppingList: ShoppingList): ListElement {
        return ListElement(
            id = shoppingList.id,
            icon = shoppingList.icon.toInt(),
            iconBackground = shoppingList.iconBackground,
            listName = shoppingList.listName,
            boughtCount = shoppingList.boughtCount.toInt(),
            totalCount = shoppingList.totalCount.toInt()
        )
    }


}
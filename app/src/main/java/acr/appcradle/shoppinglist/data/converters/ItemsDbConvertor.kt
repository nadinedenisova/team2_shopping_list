package acr.appcradle.shoppinglist.data.converters

import acr.appcradle.shoppinglist.ShoppingItems
import acr.appcradle.shoppinglist.model.ShoppingElement
import javax.inject.Inject

class ItemsDbConvertor @Inject constructor() {

    fun map (shoppingElement: ShoppingElement) : ShoppingItems {
        return ShoppingItems(
           id = 0L,
           name = shoppingElement.name,
           amount = shoppingElement.amount,
           unit = shoppingElement.unit,
           checked = if (shoppingElement.checked) 1 else 0
        )
    }

    fun map (shoppingItems: ShoppingItems) : ShoppingElement {
        return ShoppingElement(
            name = shoppingItems.name,
            listId = shoppingItems.listId,
            amount = shoppingItems.amount,
            unit = shoppingItems.unit,
            checked = shoppingItems.checked.toInt() == 1
        )
    }
}
package acr.appcradle.shoppinglist.data.converters

import acr.appcradle.shoppinglist.ShoppingItems
import acr.appcradle.shoppinglist.model.ShoppingElement
import acr.appcradle.shoppinglist.utils.DbAdapters
import javax.inject.Inject

class ItemsDbConvertor @Inject constructor() {

    fun map(shoppingElement: ShoppingElement): ShoppingItems {
        return ShoppingItems(
            id = 0L,
            listId = shoppingElement.listId,
            name = shoppingElement.name,
            amount = shoppingElement.amount,
            unit = shoppingElement.unit,
            checked = DbAdapters.booleanToLong.encode(shoppingElement.checked)
        )
    }

    fun map(shoppingItems: ShoppingItems): ShoppingElement {
        return ShoppingElement(
            id = shoppingItems.id,
            name = shoppingItems.name,
            listId = shoppingItems.listId,
            amount = shoppingItems.amount,
            unit = shoppingItems.unit,
            checked = DbAdapters.booleanToLong.decode(shoppingItems.checked)
        )
    }
}
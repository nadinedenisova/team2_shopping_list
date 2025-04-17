package acr.appcradle.shoppinglist.data.converters

import acr.appcradle.shoppinglist.ShoppingItems
import acr.appcradle.shoppinglist.model.ShoppingElement
import javax.inject.Inject

class ItemsDbConvertor @Inject constructor() {

    fun map (shoppingElement: ShoppingElement) : ShoppingItems {
        return ShoppingItems(
           id = null!!,
           name = shoppingElement.name,
           amount = shoppingElement.amount,
           unit = shoppingElement.unit,
           checked = 1
        )
    }

    fun map (shoppingItems: ShoppingItems) : ShoppingElement {
        return ShoppingElement(
            name = shoppingItems.name,
            amount = shoppingItems.amount,
            unit = shoppingItems.unit,
            checked = true
        )
    }
}
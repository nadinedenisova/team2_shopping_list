package acr.appcradle.shoppinglist.model

import kotlinx.coroutines.flow.Flow

interface ItemsInteractor {
    fun getAllItems(listId: Long) : Flow<List<ShoppingElement>>
    suspend fun addItem(item: ShoppingElement)
    suspend fun deleteItem(id: Long)
}
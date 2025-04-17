package acr.appcradle.shoppinglist.model

import kotlinx.coroutines.flow.Flow

interface ItemsRepository {
    fun getAllItems() : Flow<List<ShoppingElement>>
    suspend fun addItem(item: ShoppingElement)
    suspend fun deleteItem(id: Long)
}

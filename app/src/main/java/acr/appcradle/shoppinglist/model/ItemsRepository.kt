package acr.appcradle.shoppinglist.model

import kotlinx.coroutines.flow.Flow

internal interface ItemsRepository {
    fun getAllItems(listId: Long): Flow<List<ShoppingElement>>
    fun getSortedItems(listId: Long): Flow<List<ShoppingElement>>
    suspend fun addItem(item: ShoppingElement)
    suspend fun updatedItem(item: ShoppingElement)
    suspend fun updatedItemCheck(item: ShoppingElement)
    suspend fun deleteItem(id: Long)
    suspend fun deleteAllChecked(listId: Long)
    suspend fun makeAllUnChecked(listId: Long)
}

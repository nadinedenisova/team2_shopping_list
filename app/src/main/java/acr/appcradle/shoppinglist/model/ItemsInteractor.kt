package acr.appcradle.shoppinglist.model

import kotlinx.coroutines.flow.Flow

interface ItemsInteractor {
    fun getAllItems(listId: Long) : Flow<List<ShoppingElement>>
    fun getSortedItems(listId: Long): Flow<List<ShoppingElement>>
    suspend fun addItem(item: ShoppingElement): Result<Unit>
    suspend fun deleteItem(id: Long): Result<Unit>
}
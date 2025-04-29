package acr.appcradle.shoppinglist.model

import kotlinx.coroutines.flow.Flow

interface ItemsInteractor {
    fun getAllItems() : Flow<List<ShoppingElement>>
    suspend fun addItem(item: ShoppingElement): Result<Unit>
    suspend fun deleteItem(id: Long): Result<Unit>
}
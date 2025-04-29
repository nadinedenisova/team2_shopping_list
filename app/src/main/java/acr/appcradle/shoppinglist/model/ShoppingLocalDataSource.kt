package acr.appcradle.shoppinglist.model

import kotlinx.coroutines.flow.Flow

interface ShoppingLocalDataSource {
    fun getAllLists(): Flow<List<ListElement>>
    suspend fun insertList(item: ListElement)
    suspend fun deleteList(id: Long)

    fun getAllItems(): Flow<List<ShoppingElement>>
    suspend fun insertItem(item: ShoppingElement)
    suspend fun deleteItem(id: Long)
}
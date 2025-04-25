package acr.appcradle.shoppinglist.model

import kotlinx.coroutines.flow.Flow

interface ShoppingLocalDataSource {
    fun getAllLists(): Flow<List<ListElement>>
    fun getSortedLists(): Flow<List<ListElement>>
    suspend fun getListById(id: Long): ListElement
    suspend fun insertList(item: ListElement): Long
    suspend fun deleteList(id: Long)
    suspend fun updateList(item: ListElement)

    fun getAllItems(listId: Long): Flow<List<ShoppingElement>>
    fun getSortedItems(listId: Long): Flow<List<ShoppingElement>>
    suspend fun insertItem(item: ShoppingElement)
    suspend fun deleteItem(id: Long)
    suspend fun updateItemInfo(item: ShoppingElement)
    suspend fun updateItemCheck(item: ShoppingElement)
    suspend fun deleteAllChecked(listId: Long)
    suspend fun makeAllUnChecked(listId: Long)
}
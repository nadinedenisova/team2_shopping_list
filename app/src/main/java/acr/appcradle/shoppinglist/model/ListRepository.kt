package acr.appcradle.shoppinglist.model

import kotlinx.coroutines.flow.Flow

internal interface ListRepository {

    fun getAllLists(): Flow<List<ListElement>>
    fun getSortedLists(): Flow<List<ListElement>>
    suspend fun getListById(id: Long): ListElement
    suspend fun addItem(item: ListElement): Long
    suspend fun deleteItem(id: Long)
    suspend fun updateList(item: ListElement)
}

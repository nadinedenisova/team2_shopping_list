package acr.appcradle.shoppinglist.model

import kotlinx.coroutines.flow.Flow

interface ListRepository {

     fun getAllLists() : Flow<List<ListElement>>
     suspend fun addItem(item: ListElement)
     suspend fun deleteItem(id: Long)
}

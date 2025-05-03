package acr.appcradle.shoppinglist.data.listAll

import acr.appcradle.shoppinglist.model.ListElement
import acr.appcradle.shoppinglist.model.ListRepository
import acr.appcradle.shoppinglist.model.ShoppingLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ListRepositoryImpl @Inject constructor(
    private val localDataSource: ShoppingLocalDataSource
) : ListRepository {

    override fun getAllLists(): Flow<List<ListElement>> =
        localDataSource.getAllLists()

    override fun getSortedLists(): Flow<List<ListElement>> =
        localDataSource.getSortedLists()

    override suspend fun getListById(id: Long): ListElement {
        return localDataSource.getListById(id)
    }

    override suspend fun addItem(item: ListElement): Long {
        return localDataSource.insertList(item)
    }

    override suspend fun deleteItem(id: Long) {
        localDataSource.deleteList(id)
    }

    override suspend fun updateList(item: ListElement) {
        localDataSource.updateList(item)
    }
}

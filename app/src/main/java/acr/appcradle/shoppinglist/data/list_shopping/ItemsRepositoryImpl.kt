package acr.appcradle.shoppinglist.data.list_shopping

import acr.appcradle.shoppinglist.model.ItemsRepository
import acr.appcradle.shoppinglist.model.ShoppingElement
import acr.appcradle.shoppinglist.model.ShoppingLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemsRepositoryImpl @Inject constructor(
    private val localDataSource: ShoppingLocalDataSource
) : ItemsRepository {

    override fun getAllItems(listId: Long): Flow<List<ShoppingElement>> =
        localDataSource.getAllItems(listId)

    override fun getSortedItems(listId: Long): Flow<List<ShoppingElement>> =
        localDataSource.getSortedItems(listId)

    override suspend fun addItem(item: ShoppingElement) {
        localDataSource.insertItem(item)
    }

    override suspend fun updatedItem(item: ShoppingElement) {
        localDataSource.updateItem(item)
    }

    override suspend fun deleteItem(id: Long) {
        localDataSource.deleteItem(id)
    }

}
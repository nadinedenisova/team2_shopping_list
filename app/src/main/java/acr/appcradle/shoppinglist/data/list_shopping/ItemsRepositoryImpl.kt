package acr.appcradle.shoppinglist.data.list_shopping

import acr.appcradle.shoppinglist.ShoppingItemsQueries
import acr.appcradle.shoppinglist.data.converters.ItemsDbConvertor
import acr.appcradle.shoppinglist.data.converters.ListDbConvertor
import acr.appcradle.shoppinglist.model.ItemsRepository
import acr.appcradle.shoppinglist.model.ShoppingElement
import acr.appcradle.shoppinglist.model.ShoppingLocalDataSource
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    override suspend fun deleteItem(id: Long) {
        localDataSource.deleteItem(id)
    }
}
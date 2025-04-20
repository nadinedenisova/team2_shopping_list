package acr.appcradle.shoppinglist.data.list_shopping

import acr.appcradle.shoppinglist.ShoppingItemsQueries
import acr.appcradle.shoppinglist.data.converters.ItemsDbConvertor
import acr.appcradle.shoppinglist.data.converters.ListDbConvertor
import acr.appcradle.shoppinglist.model.ItemsRepository
import acr.appcradle.shoppinglist.model.ShoppingElement
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemsRepositoryImpl @Inject constructor(
    private val queries: ShoppingItemsQueries,
    private val converter: ItemsDbConvertor
) : ItemsRepository {

    override fun getAllItems(listId: Long): Flow<List<ShoppingElement>> =
        queries.selectItemsByListId(listId)
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { dbList ->
                dbList.map { converter.map(it) }
            }

    override suspend fun addItem(item: ShoppingElement) {
        queries.insertItems(
            listId = item.listId,
            name = item.name,
            amount = item.amount,
            unit = item.unit,
            checked = 1
        )
    }

    override suspend fun deleteItem(id: Long) {
        queries.deleteItems(id)
    }
}
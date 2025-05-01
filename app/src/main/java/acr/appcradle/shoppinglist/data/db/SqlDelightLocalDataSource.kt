package acr.appcradle.shoppinglist.data.db

import acr.appcradle.shoppinglist.ShoppingItemsQueries
import acr.appcradle.shoppinglist.ShoppingListQueries
import acr.appcradle.shoppinglist.data.converters.ItemsDbConvertor
import acr.appcradle.shoppinglist.data.converters.ListDbConvertor
import acr.appcradle.shoppinglist.model.ListElement
import acr.appcradle.shoppinglist.model.ShoppingElement
import acr.appcradle.shoppinglist.model.ShoppingLocalDataSource
import android.util.Log
import androidx.compose.ui.graphics.toArgb
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SqlDelightLocalDataSource @Inject constructor(
    private val listQueries: ShoppingListQueries,
    private val itemsQueries: ShoppingItemsQueries,
    private val converterList: ListDbConvertor,
    private val converterItem: ItemsDbConvertor
) : ShoppingLocalDataSource {

    override fun getAllLists(): Flow<List<ListElement>> =
        listQueries.selectAll()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { dbList -> dbList.map { converterList.map(it) } }
            .retry(3)
            .catch { e ->
                Log.e("ListRepository", "Ошибка при чтении списка из БД: ${e.message}", e)
                "Ошибка при загрузке списков"
            }

    override fun getSortedLists(): Flow<List<ListElement>> =
        listQueries.selectAllSortedByName()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { dbList -> dbList.map { converterList.map(it) } }
            .retry(3)
            .catch { e ->
                Log.e("ListRepository", "Ошибка при чтении списка из БД: ${e.message}", e)
                "Ошибка при загрузке списков"
            }


    override suspend fun insertList(item: ListElement): Long = withContext(Dispatchers.IO) {
        try {
            listQueries.insertElement(
                icon = item.icon.toLong(),
                iconBackground = item.iconBackground.toArgb().toLong(),
                listName = item.listName,
                boughtCount = item.boughtCount.toLong(),
                totalCount = item.totalCount.toLong()
            )
            listQueries.lastInsertedId().executeAsOne()
        } catch (e: Exception) {
            Log.e("ListRepository", "Error inserting list: ${e.message}", e)
            -1L
        }
    }

    override suspend fun deleteList(id: Long) {
        withContext(Dispatchers.IO) {
            try {
                listQueries.deleteElement(id)
            } catch (e: Exception) {
                Log.e("ListRepository", "Ошибка ${e.message}", e)
            }
        }
    }

    override suspend fun updateList(item: ListElement) {
        withContext(Dispatchers.IO) {
            try {
                listQueries.updateListById(
                    id = item.id,
                    icon = item.icon.toLong(),
                    iconBackground = item.iconBackground.toArgb().toLong(),
                    listName = item.listName
                )
            } catch (e: Exception) {
                Log.e("ListRepository", "Ошибка ${e.message}", e)
            }
        }

    }

    override suspend fun getListById(id: Long): ListElement = withContext(Dispatchers.IO) {
        try {
            val dbModel = listQueries.getListByListId(id).executeAsOne()
            converterList.map(dbModel)
        } catch (e: Exception) {
            Log.e("ListRepository", "Error fetching list id=$id: ${e.message}", e)
            throw e
        }
    }

    override fun getAllItems(listId: Long): Flow<List<ShoppingElement>> =
        itemsQueries.selectItemsById(listId)
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { dbList -> dbList.map { converterItem.map(it) } }
            .retry(3)
            .catch { e ->
                Log.e("ItemRepository", "Ошибка при чтении списка из БД: ${e.message}", e)
                "Ошибка"
            }

    override fun getSortedItems(listId: Long): Flow<List<ShoppingElement>> =
        itemsQueries.selectAllSortedByName()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { dbList -> dbList.map { converterItem.map(it) } }
            .retry(3)
            .catch { e ->
                Log.e("ItemRepository", "Ошибка при чтении списка из БД: ${e.message}", e)
                "Ошибка"
            }


    override suspend fun insertItem(item: ShoppingElement) {
        withContext(Dispatchers.IO) {
            try {
                val bdItem = converterItem.map(item)
                itemsQueries.insertItems(
                    listId = bdItem.listId,
                    name = bdItem.name,
                    amount = bdItem.amount,
                    unit = bdItem.unit,
                    checked = bdItem.checked
                )
            } catch (e: Exception) {
                Log.e("ListRepository", "Ошибка ${e.message}", e)
            }
        }
    }

    override suspend fun updateItemInfo(item: ShoppingElement) {
        withContext(Dispatchers.IO) {
            try {
                itemsQueries.updateItemsInfo(
                    name = item.name,
                    amount = item.amount,
                    unit = item.unit,
                    id = item.id
                )
            } catch (e: Exception) {
                Log.e("ListRepository", "Ошибка ${e.message}", e)
            }
        }
    }

    override suspend fun updateItemCheck(item: ShoppingElement) {
        withContext(Dispatchers.IO) {
            try {
                val bdItem = converterItem.map(item.copy(checked = !item.checked))
                itemsQueries.updateItemsCheck(checked = bdItem.checked, id = item.id)
            } catch (e: Exception) {
                Log.e("ListRepository", "Ошибка ${e.message}", e)
            }
        }

    }

    override suspend fun deleteItem(id: Long) {
        withContext(Dispatchers.IO) {
            try {
                itemsQueries.deleteItems(id)
            } catch (e: Exception) {
                Log.e("ListRepository", "Ошибка ${e.message}", e)
            }
        }

    }

    override suspend fun deleteAllChecked(listId: Long) {
        withContext(Dispatchers.IO) {
            try {
                itemsQueries.deleteAllChecked(listId)
            } catch (e: Exception) {
                Log.e("ListRepository", "Ошибка ${e.message}", e)
            }
        }

    }

    override suspend fun makeAllUnChecked(listId: Long) {
        withContext(Dispatchers.IO) {
            try {
                itemsQueries.makeAllUnchecked(listId)
            } catch (e: Exception) {
                Log.e("ListRepository", "Ошибка ${e.message}", e)
            }
        }
    }
}
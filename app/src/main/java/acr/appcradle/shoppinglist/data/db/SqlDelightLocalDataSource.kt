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
                emit(emptyList())
            }

    override suspend fun insertList(item: ListElement) {
        withContext(Dispatchers.IO) {
            try {
                listQueries.insertElement(
                    icon = item.icon.toLong(),
                    iconBackground = item.iconBackground.toArgb().toLong(),
                    listName = item.listName,
                    boughtCount = item.boughtCount.toLong(),
                    totalCount = item.totalCount.toLong()
                )
            } catch (e: Exception) {
                Log.e("ListRepository", "Ошибка при добавлении списка: ${e.message}", e)
            }
        }
    override fun getSortedLists(): Flow<List<ListElement>> =
        listQueries.selectAllSortedByName()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { dbList -> dbList.map { converterList.map(it) } }
            .retry(3)
            .catch { e ->
                Log.e("ListRepository", "Ошибка при чтении списка из БД: ${e.message}", e)
                emit(emptyList())
            }


    override suspend fun insertList(item: ListElement): Long {
        listQueries.insertElement(
            icon = item.icon.toLong(),
            iconBackground = item.iconBackground.toLong(),
            listName = item.listName,
            boughtCount = item.boughtCount.toLong(),
            totalCount = item.totalCount.toLong()
        )

        return listQueries.lastInsertedId().executeAsOne()
    }

    override suspend fun deleteList(id: Long) {
        withContext(Dispatchers.IO) {
            try {
                listQueries.deleteElement(id)
            } catch (e: Exception) {
                Log.e("ListRepository", "Ошибка при удалении списка: ${e.message}", e)
            }
        }
    }

    override suspend fun updateList(item: ListElement) {
        listQueries.updateListById(
            id = item.id,
            icon = item.icon.toLong(),
            iconBackground = item.iconBackground.toLong(),
            listName = item.listName
        )
    }

    override suspend fun getListById(id: Long): ListElement {
        val list = listQueries.getListByListId(id).executeAsOne()
        return converterList.map(list)
    }

    override fun getAllItems(listId: Long): Flow<List<ShoppingElement>> =
        itemsQueries.selectItemsById(listId)
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { dbList -> dbList.map { converterItem.map(it) } }
            .retry(3)
            .catch { e ->
                Log.e("ItemRepository", "Ошибка при чтении списка из БД: ${e.message}", e)
                emit(emptyList())
            }

    override fun getSortedItems(listId: Long): Flow<List<ShoppingElement>> =
        itemsQueries.selectAllSortedByName()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { dbList -> dbList.map { converterItem.map(it) } }
            .retry(3)
            .catch { e ->
                Log.e("ItemRepository", "Ошибка при чтении списка из БД: ${e.message}", e)
                emit(emptyList())
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
                Log.e("ListRepository", "Ошибка при добавлении элемента: ${e.message}", e)
                // throw e
            }
        }
    }

    override suspend fun updateItemCheck(item: ShoppingElement) {
        val bdItem = converterItem.map(item.copy(checked = !item.checked))
        itemsQueries.updateItemsCheck(checked = bdItem.checked, id = item.id)
    }

    override suspend fun deleteItem(id: Long) {
        withContext(Dispatchers.IO) {
            try {
                itemsQueries.deleteItems(id)
            } catch (e: Exception) {
                Log.e("ListRepository", "Ошибка при удалении элемента: ${e.message}", e)
                // throw e
            }
        }
        Log.i("database", "удаление в прослойке")

    }

    override suspend fun deleteAllChecked(listId: Long) {
        itemsQueries.deleteAllChecked(listId)
    }

    override suspend fun makeAllUnChecked(listId: Long) {
        itemsQueries.makeAllUnchecked(listId)
    }
}
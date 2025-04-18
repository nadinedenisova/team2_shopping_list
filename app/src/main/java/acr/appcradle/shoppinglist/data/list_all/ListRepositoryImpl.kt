package acr.appcradle.shoppinglist.data.list_all

import acr.appcradle.shoppinglist.ShoppingListQueries
import acr.appcradle.shoppinglist.data.converters.ListDbConvertor
import acr.appcradle.shoppinglist.model.ListElement
import acr.appcradle.shoppinglist.model.ListRepository
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton  //аннотация класса в единственном экземпляре
class ListRepositoryImpl @Inject constructor(
    private val queries: ShoppingListQueries,
    private val converter: ListDbConvertor
) : ListRepository {

    override fun getAllItems(): Flow<List<ListElement>> =
        queries.selectAll()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { dbList ->
                dbList.map { converter.map(it) }
            }

    override suspend fun addItem(item: ListElement) {
        queries.insertElement(
            icon = item.icon.toLong(),
            iconBackground = item.iconBackground.toArgb().toLong(),
            listName = item.listName,
            boughtCount = item.boughtCount.toLong(),
            totalCount = item.totalCount.toLong()
        )
    }

    override suspend fun deleteItem(id: Long) {
        queries.deleteElement(id)
    }


}
package acr.appcradle.shoppinglist.data

import acr.appcradle.shoppinglist.ShoppingDatabase
import acr.appcradle.shoppinglist.model.ListElement
import acr.appcradle.shoppinglist.model.ListRepository
import acr.appcradle.shoppinglist.model.toListElement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.Color
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton  //аннотация класса в единственном экземпляре
class ListRepositoryImpl @Inject constructor(private val db: ShoppingDatabase) : ListRepository {

    private val queries = db.shoppingListQueries

    override fun getAllItems(): Flow<List<ListElement>> =
        queries.selectAll()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { list ->
                list.map { it.toListElement() }
            }

    override suspend fun addItem(item: ListElement) {
        queries.insertElement(
            iconId = Icons.Default.ShoppingCart.toString(),
            iconColorHex = Color(0xFF64B5F6).toString(),
            listName = item.listName,
            boughtCount = item.boughtCount.toLong(),
            totalCount = item.totalCount.toLong()
        )
    }

    override suspend fun deleteItem(id: Long) {
        queries.deleteElement(id)
    }


}
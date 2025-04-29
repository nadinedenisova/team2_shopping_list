package acr.appcradle.shoppinglist.data.list_all

import acr.appcradle.shoppinglist.model.ListElement
import acr.appcradle.shoppinglist.model.ListRepository
import acr.appcradle.shoppinglist.model.ShoppingLocalDataSource
import androidx.compose.ui.graphics.toArgb
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ListRepositoryImpl @Inject constructor(
    private val localDataSource: ShoppingLocalDataSource
) : ListRepository {

    override fun getAllLists(): Flow<List<ListElement>> =
        localDataSource.getAllLists()

    override suspend fun addItem(item: ListElement) {
       localDataSource.insertList(item)
    }

    override suspend fun deleteItem(id: Long) {
        localDataSource.deleteList(id)
    }


}
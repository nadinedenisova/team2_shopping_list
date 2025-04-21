package acr.appcradle.shoppinglist.domain.list_shopping

import acr.appcradle.shoppinglist.model.ItemsInteractor
import acr.appcradle.shoppinglist.model.ItemsRepository
import acr.appcradle.shoppinglist.model.ShoppingElement
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemsInteractorImpl @Inject constructor(
    private val repository: ItemsRepository
) : ItemsInteractor {

    override fun getAllItems(listId: Long): Flow<List<ShoppingElement>> {
        return repository.getAllItems(listId)
    }

    override suspend fun addItem(item: ShoppingElement) {
        repository.addItem(item)
    }

    override suspend fun deleteItem(id: Long) {
        repository.deleteItem(id)
    }
}
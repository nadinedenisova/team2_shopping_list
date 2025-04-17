package acr.appcradle.shoppinglist.domain.list_all

import acr.appcradle.shoppinglist.model.ListElement
import acr.appcradle.shoppinglist.model.ListInteractor
import acr.appcradle.shoppinglist.model.ListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ListInteractorImpl @Inject constructor(private val repository: ListRepository) : ListInteractor {

    override fun getAllItems(): Flow<List<ListElement>> {
        return repository.getAllItems()
    }

    override suspend fun addItem(item: ListElement) {
        repository.addItem(item)
    }

    override suspend fun deleteItem(id: Long) {
        repository.deleteItem(id)
    }

}
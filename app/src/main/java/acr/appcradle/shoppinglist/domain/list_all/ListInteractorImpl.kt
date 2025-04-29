package acr.appcradle.shoppinglist.domain.list_all

import acr.appcradle.shoppinglist.model.ListElement
import acr.appcradle.shoppinglist.model.ListInteractor
import acr.appcradle.shoppinglist.model.ListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ListInteractorImpl @Inject constructor(
    private val repository: ListRepository
) : ListInteractor {

    override fun getAllItems(): Flow<List<ListElement>> {
        return repository.getAllLists()
    }

    override suspend fun addItem(item: ListElement) : Result<Unit> {
        return try {
            repository.addItem(item)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteItem(id: Long) : Result<Unit> {
        return try {
            repository.deleteItem(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}
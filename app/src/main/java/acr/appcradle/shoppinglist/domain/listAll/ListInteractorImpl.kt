package acr.appcradle.shoppinglist.domain.listAll

import acr.appcradle.shoppinglist.model.ListElement
import acr.appcradle.shoppinglist.model.ListInteractor
import acr.appcradle.shoppinglist.model.ListRepository
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ListInteractorImpl @Inject constructor(
    private val repository: ListRepository
) : ListInteractor {

    override fun getAllItems(): Flow<List<ListElement>> {
        return repository.getAllLists()
    }

    override suspend fun addItem(item: ListElement): Result<Unit> {
        return try {
            repository.addItem(item)
            Result.success(Unit)
        } catch (e: IOException) {
            Result.failure(e)
        }
    }

    override suspend fun deleteItem(id: Long): Result<Unit> {
        return try {
            repository.deleteItem(id)
            Result.success(Unit)
        } catch (e: IOException) {
            Result.failure(e)
        }
    }
}

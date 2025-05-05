package acr.appcradle.shoppinglist.ui

import acr.appcradle.shoppinglist.model.AppIntents
import acr.appcradle.shoppinglist.model.IconsIntent
import acr.appcradle.shoppinglist.model.ItemsRepository
import acr.appcradle.shoppinglist.model.ListElement
import acr.appcradle.shoppinglist.model.ListRepository
import acr.appcradle.shoppinglist.model.ListsScreenState
import acr.appcradle.shoppinglist.model.NewListData
import acr.appcradle.shoppinglist.model.ShoppingElement
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val repository: ListRepository,
    private val itemsInteractor: ItemsRepository
//    private val itemsInteractor: ItemsInteractor
) : ViewModel() {

    private val _listsAllState = MutableStateFlow(ListsScreenState())
    val listsAllState: StateFlow<ListsScreenState> = _listsAllState.asStateFlow()

    private val _iconState = MutableStateFlow(NewListData())
    val iconState: StateFlow<NewListData> = _iconState.asStateFlow()

    private val _itemsList = MutableStateFlow(emptyList<ShoppingElement>())
    val itemsList: StateFlow<List<ShoppingElement>> = _itemsList.asStateFlow()

    private val _isTitleDuplicate = MutableStateFlow(false)
    val isTitleDuplicate: StateFlow<Boolean> = _isTitleDuplicate.asStateFlow()

    fun iconsIntent(intent: IconsIntent) {
        when (intent) {
            is IconsIntent.Update -> {
                intent.icon?.let { icon ->
                    _iconState.update { it.copy(icon = icon) }
                    Log.e("database", "${repository.getAllLists()}")
                    repository.getAllLists()
                }
                intent.color?.let { color ->
                    _iconState.update { it.copy(iconColor = color) }
                }
                intent.title?.let { title ->
                    _iconState.update { it.copy(title = title) }
                }
            }
        }
    }

    fun actionIntent(intent: AppIntents) {
        when (intent) {
            is AppIntents.DeleteItem -> {
                viewModelScope.launch {
                    repository.deleteItem(intent.id)
                    itemsInteractor.deleteItem(intent.id)
                    loadLists()
                    Log.i("database", "Пришел запрос на удаление ид = ${intent.id}")
                }
            }

            is AppIntents.LoadList -> {
                loadLists(sorted = false)
                Log.i("database", "Загружаются списки")
            }

            is AppIntents.LoadSortedLists -> {
                loadLists(sorted = true)
            }

            is AppIntents.LoadItems -> {
                loadItems(intent.listId, sorted = false)
                Log.i("database", "Загружаются элементы списка")
            }

            is AppIntents.LoadSortedItems -> {
                loadItems(intent.listId, sorted = true)
            }

            is AppIntents.AddItem -> {
                viewModelScope.launch {
                    itemsInteractor.addItem(item = intent.item)
                }
                Log.i("database", "Новый элемент добавлен: ${intent.item}")
            }

            is AppIntents.DuplicateList -> {
                duplicateList(intent.listId)
            }

            is AppIntents.UpdateItem -> {
                viewModelScope.launch {
                    itemsInteractor.updatedItem(item = intent.item)
                }
            }

            is AppIntents.UpdateItemCheck -> {
                viewModelScope.launch {
                    itemsInteractor.updatedItemCheck(intent.item)
                }
            }

            is AppIntents.DeleteAllChecked -> {
                viewModelScope.launch {
                    itemsInteractor.deleteAllChecked(intent.listId)
                }
            }

            is AppIntents.MakeAllUnChecked -> {
                viewModelScope.launch {
                    itemsInteractor.makeAllUnChecked(intent.listId)
                }
            }

            else -> {}
        }
    }

    private fun loadLists(sorted: Boolean = false) {
        viewModelScope.launch {
            _listsAllState.update { it.copy(isLoading = true) }
            val flow = if (sorted) repository.getSortedLists() else repository.getAllLists()
            val items = flow.first()
            _listsAllState.update {
                ListsScreenState(
                    isLoading = false,
                    list = items,
                    isEmpty = items.isEmpty()
                )
            }
        }
    }

    private fun loadItems(listId: Long, sorted: Boolean = false) {
        viewModelScope.launch {
            val flow = if (sorted)
                itemsInteractor.getSortedItems(listId)
            else
                itemsInteractor.getAllItems(listId)

            flow.collect { items ->
                _itemsList.update { items }
            }
        }
    }

    fun checkTitleUniqueness(title: String) {
        viewModelScope.launch {
            val all = repository.getAllLists().first()
            _isTitleDuplicate.value = all.any {
                it.listName.equals(title.trim(), ignoreCase = true)
            }
        }
    }

    fun createNewList(title: String, onComplete: () -> Unit) {
        viewModelScope.launch {
            val data = iconState.value
            var newItem = ListElement(
                id = 0L,
                icon = data.icon.toInt(),
                iconBackground = data.iconColor.value.toLong(),
                listName = title,
                boughtCount = 0,
                totalCount = 0
            )
            repository.addItem(newItem)
            onComplete()
            actionIntent(AppIntents.LoadList)
        }
    }

    fun updateList(item: ListElement, onComplete: () -> Unit) {
        viewModelScope.launch {
            repository.updateList(item)
            onComplete()
            actionIntent(AppIntents.LoadList)
        }
    }

    private fun duplicateList(listId: Long) {
        viewModelScope.launch {
            val originalList = repository.getListById(listId)
            val items = itemsInteractor.getAllItems(listId).first()
            val newList = originalList.copy(
                id = 0L,
                listName = "${originalList.listName} копия"
            )
            val newListId = repository.addItem(newList)
            items.forEach { item ->
                val newItem = item.copy(listId = newListId)
                itemsInteractor.addItem(newItem)
            }
            loadLists()
        }
    }
}

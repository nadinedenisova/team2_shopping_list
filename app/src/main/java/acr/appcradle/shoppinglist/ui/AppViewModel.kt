package acr.appcradle.shoppinglist.ui

import acr.appcradle.shoppinglist.model.AppIntents
import acr.appcradle.shoppinglist.model.IconsIntent
import acr.appcradle.shoppinglist.model.ItemsRepository
import acr.appcradle.shoppinglist.model.ListElement
import acr.appcradle.shoppinglist.model.ListRepository
import acr.appcradle.shoppinglist.model.ListsScreenState
import acr.appcradle.shoppinglist.model.NewListData
import acr.appcradle.shoppinglist.model.ShoppingElement
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

    fun iconsIntent(intent: IconsIntent) {
        when (intent) {
            is IconsIntent.ChangeIcon -> {
                _iconState.update { it.copy(icon = intent.icon) }
                Log.e("database", "${repository.getAllLists()}")
                repository.getAllLists()
            }

            is IconsIntent.ChangeColor -> {
                _iconState.update { it.copy(iconColor = intent.color) }
            }

            is IconsIntent.ChangeTitle -> {

            }
        }
    }

    fun actionIntent(intent: AppIntents) {
        when (intent) {

            is AppIntents.DeleteItem -> {
                viewModelScope.launch {
                    repository.deleteItem(intent.id)
                    loadLists()
                }
            }

            is AppIntents.LoadList -> {
                loadLists()
                Log.i("database", "Загружаются списки")

            }

            is AppIntents.LoadItems -> {
                loadItems()
                Log.i("database", "Загружаются элементы списка")
            }

            is AppIntents.AddItem -> {
                viewModelScope.launch {
                    itemsInteractor.addItem(item = intent.item)
                }
                Log.i("database", "Новый элемент добавлен: ${intent.item}")
            }
        }
    }

    private fun loadLists() {
        viewModelScope.launch {
            _listsAllState.update { it.copy(isLoading = true) }
            val items = repository.getAllLists().first()
            _listsAllState.update {
                ListsScreenState(
                    isLoading = false,
                    list = items,
                    isEmpty = items.isEmpty()
                )
            }
        }
    }

    private fun loadItems() {
        viewModelScope.launch {
            itemsInteractor.getAllItems().collect { items ->
                _itemsList.update { items }
            }
        }
    }

    fun createNewList(title: String, onComplete: () -> Unit) {
        viewModelScope.launch {
            val data = iconState.value
            val newItem = ListElement(
                id = 0L,
                icon = data.icon!!.toInt(),
                iconBackground = data.iconColor!!,
                listName = title,
                boughtCount = 0,
                totalCount = 0
            )
            repository.addItem(newItem)
            onComplete()
        }
    }

}
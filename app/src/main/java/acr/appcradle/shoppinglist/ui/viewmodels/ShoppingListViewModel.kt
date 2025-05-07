package acr.appcradle.shoppinglist.ui.viewmodels

import acr.appcradle.shoppinglist.model.ItemsRepository
import acr.appcradle.shoppinglist.model.ListRepository
import acr.appcradle.shoppinglist.model.ShoppingElement
import acr.appcradle.shoppinglist.model.ShoppingListIntent
import acr.appcradle.shoppinglist.model.ShoppingListState
import android.content.Context
import android.content.Intent
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
class ShoppingListViewModel @Inject internal constructor(
    private val itemsRepository: ItemsRepository,
    private val listRepository: ListRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ShoppingListState())
    val state: StateFlow<ShoppingListState> = _state.asStateFlow()

    fun handleIntent(intent: ShoppingListIntent) {
        when (intent) {
            is ShoppingListIntent.LoadItems -> loadItems(intent.listId, intent.sorted)
            is ShoppingListIntent.AddItem -> addItem(intent.item)
            is ShoppingListIntent.UpdateItem -> updateItem(intent.item)
            is ShoppingListIntent.DeleteItem -> deleteItem(intent.id, intent.listId)
            is ShoppingListIntent.UpdateItemCheck -> updateItemCheck(intent.item)
            is ShoppingListIntent.DeleteAllChecked -> deleteAllChecked(intent.listId)
            is ShoppingListIntent.MakeAllUnChecked -> makeAllUnChecked(intent.listId)
            is ShoppingListIntent.ShareList -> shareList(intent.name, intent.list, intent.context)
        }
    }

    private fun loadItems(listId: Long, sorted: Boolean = false) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                val flow = if (sorted) {
                    itemsRepository.getSortedItems(listId)
                } else {
                    itemsRepository.getAllItems(listId)
                }
                flow.collect { items ->
                    _state.update { it.copy(items = items, isLoading = false) }
                }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }

    private fun addItem(item: ShoppingElement) {
        viewModelScope.launch {
            try {
                itemsRepository.addItem(item)
                updateListCounters(item.listId)
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message) }
            }
        }
    }

    private fun updateItem(item: ShoppingElement) {
        viewModelScope.launch {
            try {
                itemsRepository.updatedItem(item)
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message) }
            }
        }
    }

    private fun deleteItem(id: Long, listId: Long) {
        viewModelScope.launch {
            try {
                itemsRepository.deleteItem(id)
                updateListCounters(listId)
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message) }
            }
        }
    }

    private fun updateItemCheck(item: ShoppingElement) {
        viewModelScope.launch {
            try {
                itemsRepository.updatedItemCheck(item)
                updateListCounters(item.listId)
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message) }
            }
        }
    }

    private fun deleteAllChecked(listId: Long) {
        viewModelScope.launch {
            try {
                itemsRepository.deleteAllChecked(listId)
                updateListCounters(listId)
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message) }
            }
        }
    }

    private fun makeAllUnChecked(listId: Long) {
        viewModelScope.launch {
            try {
                itemsRepository.makeAllUnChecked(listId)
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message) }
            }
        }
    }

    private fun shareList(name: String, list: List<ShoppingElement>, context: Context) {
        val text = buildString {
            appendLine("Название списка: $name")
            appendLine()
            appendLine("Список товаров:")
            list.forEach {
                appendLine("- ${it.name}")
            }
        }

        val sendIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }

        val shareIntent = Intent.createChooser(sendIntent, "Поделиться списком")
        context.startActivity(shareIntent)
    }

    private fun updateListCounters(listId: Long) {
        viewModelScope.launch {
            try {
                val items = itemsRepository.getAllItems(listId).first()
                val totalCount = items.size
                val boughtCount = items.count { it.checked }
                val list = listRepository.getListById(listId)

                if (list != null) {
                    val updatedList = list.copy(
                        totalCount = totalCount,
                        boughtCount = boughtCount
                    )
                    listRepository.updateList(updatedList)
                }
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message) }
            }
        }
    }
} 
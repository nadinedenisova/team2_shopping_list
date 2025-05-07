package acr.appcradle.shoppinglist.ui.viewmodels

import acr.appcradle.shoppinglist.model.ListElement
import acr.appcradle.shoppinglist.model.ListRepository
import acr.appcradle.shoppinglist.model.ListsIntent
import acr.appcradle.shoppinglist.model.ListsScreenState
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
class ListsViewModel @Inject constructor(
    val repository: ListRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ListsScreenState())
    val state: StateFlow<ListsScreenState> = _state.asStateFlow()

    fun handleIntent(intent: ListsIntent) {
        when (intent) {
            is ListsIntent.LoadLists -> loadLists(intent.sorted)
            is ListsIntent.DeleteList -> deleteList(intent.id)
            is ListsIntent.DuplicateList -> duplicateList(intent.id)
            is ListsIntent.UpdateList -> updateList(intent.list)
        }
    }

    private fun loadLists(sorted: Boolean = false) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                val flow = if (sorted) repository.getSortedLists() else repository.getAllLists()
                val items = flow.first()
                _state.update {
                    ListsScreenState(
                        isLoading = false,
                        list = items,
                        isEmpty = items.isEmpty()
                    )
                }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }

    private fun deleteList(id: Long) {
        viewModelScope.launch {
            _state.update { it.copy(error = null) }
            try {
                repository.deleteItem(id)
                loadLists()
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message) }
            }
        }
    }

    private fun duplicateList(id: Long) {
        viewModelScope.launch {
            _state.update { it.copy(error = null) }
            try {
                val originalList = repository.getListById(id)
                if (originalList != null) {
                    val newList = originalList.copy(
                        id = 0L,
                        listName = "${originalList.listName} копия"
                    )
                    repository.addItem(newList)
                    loadLists()
                }
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message) }
            }
        }
    }

    private fun updateList(list: ListElement) {
        viewModelScope.launch {
            _state.update { it.copy(error = null) }
            try {
                repository.updateList(list)
                loadLists()
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message) }
            }
        }
    }
} 
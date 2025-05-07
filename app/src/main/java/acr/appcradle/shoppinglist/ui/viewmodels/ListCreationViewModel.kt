package acr.appcradle.shoppinglist.ui.viewmodels

import acr.appcradle.shoppinglist.model.ListCreationIntent
import acr.appcradle.shoppinglist.model.ListCreationState
import acr.appcradle.shoppinglist.model.ListElement
import acr.appcradle.shoppinglist.model.ListRepository
import androidx.compose.ui.graphics.Color
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
class ListCreationViewModel @Inject internal constructor(
    private val repository: ListRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ListCreationState())
    val state: StateFlow<ListCreationState> = _state.asStateFlow()

    fun handleIntent(intent: ListCreationIntent) {
        when (intent) {
            is ListCreationIntent.ChangeIcon -> changeIcon(intent.icon)
            is ListCreationIntent.ChangeColor -> changeColor(intent.color)
            is ListCreationIntent.ChangeTitle -> changeTitle(intent.title)
            is ListCreationIntent.CreateList -> createList(intent.title)
            is ListCreationIntent.CheckTitleUniqueness -> checkTitleUniqueness(intent.title)
        }
    }

    private fun changeIcon(icon: Int) {
        _state.update { it.copy(icon = icon) }
    }

    private fun changeColor(color: Color) {
        _state.update { it.copy(iconColor = color) }
    }

    private fun changeTitle(title: String) {
        _state.update { it.copy(title = title) }
    }

    private fun createList(title: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                val data = state.value
                val newItem = ListElement(
                    id = 0L,
                    icon = data.icon!!.toInt(),
                    iconBackground = data.iconColor!!.value.toLong(),
                    listName = title,
                    boughtCount = 0,
                    totalCount = 0
                )
                repository.addItem(newItem)
                _state.update { it.copy(isLoading = false) }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }

    private fun checkTitleUniqueness(title: String) {
        viewModelScope.launch {
            try {
                val all = repository.getAllLists().first()
                _state.update {
                    it.copy(
                        isTitleDuplicate = all.any {
                            it.listName.equals(title.trim(), ignoreCase = true)
                        }
                    )
                }
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message) }
            }
        }
    }
} 
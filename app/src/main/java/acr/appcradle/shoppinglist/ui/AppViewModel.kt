package acr.appcradle.shoppinglist.ui

import acr.appcradle.shoppinglist.model.AppIntents
import acr.appcradle.shoppinglist.model.IconsIntent
import acr.appcradle.shoppinglist.model.ListElement
import acr.appcradle.shoppinglist.model.ListRepository
import acr.appcradle.shoppinglist.model.ListsScreenState
import acr.appcradle.shoppinglist.model.NewListData
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val repository: ListRepository
) : ViewModel() {

    private val _listsAllState = MutableStateFlow(ListsScreenState())
    val listsAllState: StateFlow<ListsScreenState> = _listsAllState.asStateFlow()

    private val _iconState = MutableStateFlow(NewListData())
    val iconState: StateFlow<NewListData> = _iconState.asStateFlow()

    fun iconsIntent(intent: IconsIntent) {
        when (intent) {
            is IconsIntent.ChangeIcon -> {
                _iconState.value = _iconState.value.copy(icon = intent.icon)
                Log.e("database", "${repository.getAllItems()}")
                repository.getAllItems()
            }

            is IconsIntent.ChangeColor -> {
                _iconState.value = _iconState.value.copy(iconColor = intent.color)
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
            }
        }
    }

    private fun loadLists() {
        viewModelScope.launch {
            _listsAllState.value = _listsAllState.value.copy(isLoading = true)
            val items = repository.getAllItems().first()
            _listsAllState.value = ListsScreenState(
                isLoading = false,
                list = items,
                isEmpty = items.isEmpty()
            )
        }
    }

}
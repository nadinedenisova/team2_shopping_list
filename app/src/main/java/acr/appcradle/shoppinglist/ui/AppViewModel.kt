package acr.appcradle.shoppinglist.ui

import acr.appcradle.shoppinglist.data.Repository
import acr.appcradle.shoppinglist.model.AppIntents
import acr.appcradle.shoppinglist.model.IconsIntent
import acr.appcradle.shoppinglist.model.ListsScreenState
import acr.appcradle.shoppinglist.model.NewListData
import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _state = MutableStateFlow(ListsScreenState())
    val state: StateFlow<ListsScreenState> = _state.asStateFlow()

    private val _iconState = MutableStateFlow(NewListData())
    val iconState: StateFlow<NewListData> = _iconState.asStateFlow()

    fun iconsIntent(intent: IconsIntent) {
        when (intent) {
            is IconsIntent.ChangeIcon -> {
                _iconState.value = _iconState.value.copy(icon = intent.icon)
                Log.e("database", "${repository.getValue()}")
                repository.setValue()
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
            AppIntents.AddItem -> TODO()
            AppIntents.CheckItem -> TODO()
            AppIntents.DeleteItem -> TODO()
            AppIntents.LoadList -> TODO()
        }
    }
}
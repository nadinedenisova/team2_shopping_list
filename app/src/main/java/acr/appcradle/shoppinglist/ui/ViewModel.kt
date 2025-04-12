package acr.appcradle.shoppinglist.ui

import acr.appcradle.shoppinglist.model.AppIntents
import acr.appcradle.shoppinglist.model.ListsScreenState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppViewModel : ViewModel() {

    private val _state = MutableStateFlow(ListsScreenState())
    val state: StateFlow<ListsScreenState> = _state.asStateFlow()

    fun actionIntent(intent: AppIntents) {
        when (intent) {
            AppIntents.AddItem -> TODO()
            AppIntents.CheckItem -> TODO()
            AppIntents.DeleteItem -> TODO()
            AppIntents.LoadList -> TODO()
        }
    }
}
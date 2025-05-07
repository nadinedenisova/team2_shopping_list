package acr.appcradle.shoppinglist.model

import acr.appcradle.shoppinglist.data.model.AuthResponse
import androidx.compose.ui.graphics.Color

data class ListsScreenState(
    val isLoading: Boolean = false,
    val list: List<ListElement> = emptyList(),
    val isEmpty: Boolean = false,
    val error: String? = null
)

data class ListCreationState(
    val icon: Int? = null,
    val iconColor: Color? = null,
    val title: String = "",
    val isTitleDuplicate: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)

data class ShoppingListState(
    val items: List<ShoppingElement> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed class AuthUiState {
    object Initial : AuthUiState()
    object Loading : AuthUiState()
    data class Success(val response: AuthResponse) : AuthUiState()
    data class Error(val message: String) : AuthUiState()
}

sealed class RegisterUiState {
    object Initial : RegisterUiState()
    object Loading : RegisterUiState()
    data class Success(val response: AuthResponse) : RegisterUiState()
    data class Error(val message: String) : RegisterUiState()
}
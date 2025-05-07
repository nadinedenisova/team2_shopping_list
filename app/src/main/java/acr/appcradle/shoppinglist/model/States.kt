package acr.appcradle.shoppinglist.model

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

data class AuthState(
    val isLoading: Boolean = false,
    val isAuthenticated: Boolean = false,
    val error: String? = null
)

data class RegisterState(
    val isLoading: Boolean = false,
    val isRegistered: Boolean = false,
    val error: String? = null
) 
package acr.appcradle.shoppinglist.model

import android.content.Context
import androidx.compose.ui.graphics.Color

sealed class ListsIntent {
    data class LoadLists(val sorted: Boolean = false) : ListsIntent()
    data class DeleteList(val id: Long) : ListsIntent()
    data class DuplicateList(val id: Long) : ListsIntent()
    data class UpdateList(val list: ListElement) : ListsIntent()
}

sealed class ListCreationIntent {
    data class SetExistingList(val list: ListElement) : ListCreationIntent()
    data class ChangeIcon(val icon: Int) : ListCreationIntent()
    data class ChangeColor(val color: Color) : ListCreationIntent()
    data class ChangeTitle(val title: String) : ListCreationIntent()
    data class CreateList(val title: String) : ListCreationIntent()
    data class UpdateList(val list: ListElement) : ListCreationIntent()
    data class CheckTitleUniqueness(val title: String) : ListCreationIntent()
}

sealed class ShoppingListIntent {
    data class LoadItems(val listId: Long, val sorted: Boolean = false) : ShoppingListIntent()
    data class AddItem(val item: ShoppingElement) : ShoppingListIntent()
    data class UpdateItem(val item: ShoppingElement) : ShoppingListIntent()
    data class DeleteItem(val id: Long, val listId: Long) : ShoppingListIntent()
    data class UpdateItemCheck(val item: ShoppingElement) : ShoppingListIntent()
    data class DeleteAllChecked(val listId: Long) : ShoppingListIntent()
    data class MakeAllUnChecked(val listId: Long) : ShoppingListIntent()
    data class ShareList(val name: String, val list: List<ShoppingElement>, val context: Context) : ShoppingListIntent()
}

sealed class AuthIntent {
    data class Login(val email: String, val password: String) : AuthIntent()
    data object Logout : AuthIntent()
}

sealed class RegisterIntent {
    data class Register(val email: String, val password: String) : RegisterIntent()
} 
package acr.appcradle.shoppinglist.model

import android.content.Context

sealed interface AppIntents {
    data class DeleteItem(val id: Long) : AppIntents
    data object LoadList : AppIntents
    data object LoadSortedLists : AppIntents
    data class LoadItems(val listId: Long) : AppIntents
    data class LoadSortedItems(val listId: Long) : AppIntents
    data class AddItem(val item: ShoppingElement) : AppIntents
    data class UpdateItem(val item: ShoppingElement) : AppIntents
    data class UpdateItemCheck(val item: ShoppingElement) : AppIntents
    data class DeleteAllChecked(val listId: Long) : AppIntents
    data class MakeAllUnChecked(val listId: Long) : AppIntents
    data class DuplicateList(val listId: Long) : AppIntents
    data class ShareList(val name: String, val list: List<ShoppingElement>, val context: Context) : AppIntents
}
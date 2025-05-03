package acr.appcradle.shoppinglist.model

import android.content.Context

sealed interface AppIntents {
    class DeleteItem(val id: Long) : AppIntents
    object LoadList : AppIntents
    object LoadSortedLists : AppIntents
    class LoadItems(val listId: Long) : AppIntents
    class LoadSortedItems(val listId: Long) : AppIntents
    class AddItem(val item: ShoppingElement) : AppIntents
    class UpdateItem(val item: ShoppingElement) : AppIntents
    class UpdateItemCheck(val item: ShoppingElement) : AppIntents
    class DeleteAllChecked(val listId: Long) : AppIntents
    class MakeAllUnChecked(val listId: Long) : AppIntents
    class DuplicateList(val listId: Long) : AppIntents
    class ShareList(val name: String, val list: List<ShoppingElement>, val context: Context) : AppIntents
}
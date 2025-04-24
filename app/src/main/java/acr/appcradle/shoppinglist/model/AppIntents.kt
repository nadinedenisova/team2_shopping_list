package acr.appcradle.shoppinglist.model

sealed interface AppIntents {
    data class DeleteItem(val id: Long) : AppIntents
    data object LoadList : AppIntents
    data object LoadSortedLists : AppIntents
    data class LoadItems(val listId: Long) : AppIntents
    data class LoadSortedItems(val listId: Long) : AppIntents
    data class AddItem(val item: ShoppingElement) : AppIntents
    data class UpdateItem(val item: ShoppingElement) : AppIntents
    data class DuplicateList(val listId: Long) : AppIntents
}
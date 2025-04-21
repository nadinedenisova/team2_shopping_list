package acr.appcradle.shoppinglist.model

sealed interface AppIntents {
    data class DeleteItem(val id: Long) : AppIntents
    data object LoadList : AppIntents
    data class LoadItems(val listId: Long) : AppIntents
    data class AddItem(val item: ShoppingElement) : AppIntents
}
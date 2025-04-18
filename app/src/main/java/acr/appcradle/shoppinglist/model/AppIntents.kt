package acr.appcradle.shoppinglist.model

sealed interface AppIntents {
    data class DeleteItem(val id: Long) : AppIntents
    data object LoadList : AppIntents
    data object LoadItems : AppIntents
    data class AddItem(val item: ShoppingElement) : AppIntents
}
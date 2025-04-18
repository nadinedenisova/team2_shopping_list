package acr.appcradle.shoppinglist.model

sealed interface AppIntents {
    data class DeleteItem(val id: Long) : AppIntents
    data object LoadList : AppIntents
}
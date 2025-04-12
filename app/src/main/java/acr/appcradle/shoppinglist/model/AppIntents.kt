package acr.appcradle.shoppinglist.model

sealed interface AppIntents {
    data object AddItem : AppIntents
    data object CheckItem : AppIntents
    data object DeleteItem : AppIntents
    data object LoadList : AppIntents
}

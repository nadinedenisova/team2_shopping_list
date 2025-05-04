package acr.appcradle.shoppinglist.model

internal data class ListsScreenState(
    val isLoading: Boolean = false,
    val list: List<ListElement> = emptyList<ListElement>(),
    val isEmpty: Boolean = false
)

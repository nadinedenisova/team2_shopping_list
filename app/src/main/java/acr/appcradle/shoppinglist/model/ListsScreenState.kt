package acr.appcradle.shoppinglist.model

data class ListsScreenState(
    val isLoading: Boolean = false,
    val list: List<ListElement> = emptyList<ListElement>()
)

package acr.appcradle.shoppinglist.model

import kotlinx.serialization.Serializable

@Serializable
internal data class ListElement(
    val id: Long,
    val icon: Int,
    val iconBackground: Long,
    val listName: String,
    val boughtCount: Int,
    val totalCount: Int,
)

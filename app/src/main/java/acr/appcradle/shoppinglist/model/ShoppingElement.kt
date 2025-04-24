package acr.appcradle.shoppinglist.model

data class ShoppingElement(
    val listId: Long,
    val name: String,
    val amount: String,
    val unit: String,
    val checked: Boolean,
)

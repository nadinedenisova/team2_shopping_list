package acr.appcradle.shoppinglist.model

data class ShoppingElement(
    val name: String,
    val amount: String,
    val unit: String,
    val checked: Boolean,
)

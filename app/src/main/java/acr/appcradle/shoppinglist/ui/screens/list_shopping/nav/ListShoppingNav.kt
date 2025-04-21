package acr.appcradle.shoppinglist.ui.screens.list_shopping.nav

import acr.appcradle.shoppinglist.ui.screens.list_shopping.ListShoppingScreen
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object ListShoppingRoute

fun NavController.navigateToShoppingList(listId: Long) {
    navigate("listShopping/$listId")
}

fun NavGraphBuilder.shoppingScreen(
    onBackClick: () -> Unit,
) {
    composable("listShopping/{listId}") { backStackEntry ->
        val listId =
            backStackEntry.arguments?.getString("listId")?.toLongOrNull() ?: return@composable
        ListShoppingScreen(
            listId = listId,
            onBackClick = onBackClick,
        )
    }
}
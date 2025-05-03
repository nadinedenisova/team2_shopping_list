package acr.appcradle.shoppinglist.ui.screens.listShopping.nav

import acr.appcradle.shoppinglist.ui.screens.listShopping.ListShoppingScreen
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import kotlinx.serialization.Serializable
import java.net.URLEncoder

@Serializable
data object ListShoppingRoute

internal fun NavController.navigateToShoppingList(listId: Long, listName: String) {
//    navigate("listShopping/$listId")
    val encodedName = URLEncoder.encode(listName, "UTF-8")
    navigate("listShopping/$listId/$encodedName")
}

internal fun NavGraphBuilder.shoppingScreen(
    onBackClick: () -> Unit,
) {
    composable(
        route = "listShopping/{listId}/{listName}",
        arguments = listOf(
            navArgument("listId") { type = NavType.StringType },
            navArgument("listName") { type = NavType.StringType }
        )
    ) { backStackEntry ->
//        val listId = backStackEntry.arguments?.getString("listId")?.toLongOrNull() ?: return@composable
        val listId =
            backStackEntry.arguments?.getString("listId")?.toLongOrNull() ?: return@composable
        val listName = backStackEntry.arguments?.getString("listName") ?: return@composable
        ListShoppingScreen(
            listId = listId,
            onBackClick = onBackClick,
            listName = listName,
        )
    }
}

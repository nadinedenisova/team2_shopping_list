package acr.appcradle.shoppinglist.ui.screens.list_shopping.nav

import acr.appcradle.shoppinglist.model.ListElement
import acr.appcradle.shoppinglist.ui.screens.list_shopping.ListShoppingScreen
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import kotlinx.serialization.Serializable
import java.net.URLEncoder

@Serializable
data object ListShoppingRoute

fun NavController.navigateToShoppingList(list: ListElement) {
    val encodedList = URLEncoder
        .encode(kotlinx.serialization
            .json
            .Json
            .encodeToString(ListElement.serializer(), list), "UTF-8")
    navigate("listShopping/$encodedList")
}

fun NavGraphBuilder.shoppingScreen(
    onBackClick: () -> Unit,
) {
    composable(
        route = "listShopping/{list}",
        arguments = listOf(
            navArgument("list") { type = NavType.StringType }
        )
    ) { backStackEntry ->
        val listArg = backStackEntry.arguments?.getString("list") ?: return@composable
        val decodedList = java.net.URLDecoder.decode(listArg, "UTF-8")
        val list = kotlinx.serialization.json.Json.decodeFromString(ListElement.serializer(), decodedList)

        ListShoppingScreen(
            listId = list.id,
            onBackClick = onBackClick,
            listName = list.listName,
        )
    }
}
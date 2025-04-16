package acr.appcradle.shoppinglist.ui.screens.list_shopping.nav

import acr.appcradle.shoppinglist.ui.screens.list_shopping.ListShoppingScreen
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object ListShoppingRoute

fun NavController.navigateToShoppingList() =
    navigate(route = ListShoppingRoute)


fun NavGraphBuilder.shoppingScreen(
    onBackClick: () -> Unit,
) {
    composable<ListShoppingRoute> {
        ListShoppingScreen(
            onBackClick = onBackClick,
        )
    }
}
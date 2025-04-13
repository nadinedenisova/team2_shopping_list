package acr.appcradle.shoppinglist.ui.screens.list_creation.nav

import acr.appcradle.shoppinglist.ui.screens.list_creation.ListCreationScreen
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable


@Serializable
data object ListCreationRoute

fun NavController.navigateToListCreation(navOptions: NavOptions) =
    navigate(route = ListCreationRoute, navOptions)


fun NavGraphBuilder.creationScreen(
    onClick: () -> Unit
) {
    composable<ListCreationRoute> { ListCreationScreen(onBackClick = onClick) }
}
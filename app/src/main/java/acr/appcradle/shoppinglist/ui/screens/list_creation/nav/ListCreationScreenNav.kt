package acr.appcradle.shoppinglist.ui.screens.list_creation.nav

import acr.appcradle.shoppinglist.ui.AppViewModel
import acr.appcradle.shoppinglist.ui.screens.list_creation.ListCreationScreen
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import kotlinx.serialization.Serializable


@Serializable
data object ListCreationRoute

fun NavController.navigateToListCreation() =
    navigate(route = ListCreationRoute)


fun NavGraphBuilder.creationScreen(
    onClick: () -> Unit
) {
    composable<ListCreationRoute> { ListCreationScreen(onBackClick = onClick, viewModel = AppViewModel()) }
}
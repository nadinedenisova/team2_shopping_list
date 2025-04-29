package acr.appcradle.shoppinglist.ui.screens.list_creation.nav

import acr.appcradle.shoppinglist.ui.screens.list_creation.ListCreationScreen
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable


@Serializable
data object ListCreationRoute

fun NavController.navigateToListCreation() =
    navigate(route = ListCreationRoute)


fun NavGraphBuilder.creationScreen(
    onBackClick: () -> Unit,
    onNextClick:()-> Unit
) {
    composable<ListCreationRoute> {
        ListCreationScreen(
            onNextClick = onNextClick,
            onBackClick = onBackClick,
        )
    }
}
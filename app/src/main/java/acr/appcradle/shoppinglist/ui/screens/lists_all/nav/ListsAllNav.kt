package acr.appcradle.shoppinglist.ui.screens.lists_all.nav

import acr.appcradle.shoppinglist.ui.screens.lists_all.ListsAll
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object ListsAllRoute

fun NavController.navigateListsAll() = navigate(route = ListsAllRoute)

fun NavGraphBuilder.listsAll(
    createNewListClick: () -> Unit,
    onListClick: () -> Unit
) {
    composable<ListsAllRoute> {
        ListsAll(
            createNewListClick = createNewListClick,
            onListClick = onListClick
        )
    }
}
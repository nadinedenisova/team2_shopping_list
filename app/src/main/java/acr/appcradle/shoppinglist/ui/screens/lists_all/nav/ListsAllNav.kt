package acr.appcradle.shoppinglist.ui.screens.lists_all.nav

import acr.appcradle.shoppinglist.ui.screens.lists_all.ListsAll
import acr.appcradle.shoppinglist.utils.ThemeOption
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object ListsAllRoute

fun NavController.navigateListsAll() = navigate(route = ListsAllRoute)

fun NavGraphBuilder.listsAll(
    createNewListClick: () -> Unit,
    editListClick: (Long) -> Unit,
    onListClick: (Long) -> Unit,
    onThemeChange: (ThemeOption) -> Unit
) {
    composable<ListsAllRoute> {
        ListsAll(
            createNewListClick = createNewListClick,
            editListClick = editListClick,
            onListClick = onListClick,
            onThemeChange = onThemeChange,
        )
    }
}
package acr.appcradle.shoppinglist.ui.screens.listsAll.nav

import acr.appcradle.shoppinglist.model.ListElement
import acr.appcradle.shoppinglist.ui.screens.greeting.nav.GreetingRoute
import acr.appcradle.shoppinglist.ui.screens.listsAll.ListsAll
import acr.appcradle.shoppinglist.utils.ThemeOption
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object ListsAllRoute

internal fun NavController.navigateListsAll() = navigate(ListsAllRoute) {
    popUpTo(GreetingRoute) {
        inclusive = false
    }
}

internal fun NavGraphBuilder.listsAll(
    createNewListClick: () -> Unit,
    onEdit: (ListElement) -> Unit,
    onListClick: (Long, String) -> Unit,
    onThemeChange: (ThemeOption) -> Unit
) {
    composable<ListsAllRoute> {
        ListsAll(
            createNewListClick = createNewListClick,
            onListClick = onListClick,
            onThemeChange = onThemeChange,
            onEdit = onEdit
        )
    }
}

package acr.appcradle.shoppinglist

import acr.appcradle.shoppinglist.model.ListElement
import acr.appcradle.shoppinglist.ui.screens.greeting.nav.GreetingRoute
import acr.appcradle.shoppinglist.ui.screens.greeting.nav.greeting
import acr.appcradle.shoppinglist.ui.screens.listCreation.nav.creationScreen
import acr.appcradle.shoppinglist.ui.screens.listCreation.nav.navigateToListCreation
import acr.appcradle.shoppinglist.ui.screens.listShopping.nav.navigateToShoppingList
import acr.appcradle.shoppinglist.ui.screens.listShopping.nav.shoppingScreen
import acr.appcradle.shoppinglist.ui.screens.listsAll.nav.listsAll
import acr.appcradle.shoppinglist.ui.screens.listsAll.nav.navigateListsAll
import acr.appcradle.shoppinglist.utils.ThemeOption
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
internal fun AppNavHost(
    scaffoldPaddings: PaddingValues,
    modifier: Modifier = Modifier,
    onThemeChange: (ThemeOption) -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = GreetingRoute,
        modifier = modifier.padding(scaffoldPaddings),
    ) {
        greeting(
            onNextClick = { navController.navigateListsAll() }
        )
        listsAll(
            createNewListClick = { navController.navigateToListCreation() },
            onListClick = { listId, listName ->
                navController.navigateToShoppingList(
                    listId,
                    listName
                )
            },
            onThemeChange = onThemeChange,
            onEdit = { list: ListElement ->
                navController.navigateToListCreation(list)
            }
        )
        creationScreen(
            onBackClick = { navController.popBackStack() },
            onNextClick = { navController.navigateListsAll() },
            onThemeChange = onThemeChange
        )
        shoppingScreen(
            onBackClick = { navController.popBackStack() },
        )
    }
}

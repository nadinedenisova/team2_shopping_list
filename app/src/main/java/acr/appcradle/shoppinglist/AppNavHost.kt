package acr.appcradle.shoppinglist

import acr.appcradle.shoppinglist.ui.screens.greeting.nav.GreetingRoute
import acr.appcradle.shoppinglist.ui.screens.greeting.nav.greeting
import acr.appcradle.shoppinglist.ui.screens.list_creation.nav.creationScreen
import acr.appcradle.shoppinglist.ui.screens.list_creation.nav.navigateToListCreation
import acr.appcradle.shoppinglist.ui.screens.list_shopping.nav.navigateToShoppingList
import acr.appcradle.shoppinglist.ui.screens.list_shopping.nav.shoppingScreen
import acr.appcradle.shoppinglist.ui.screens.lists_all.nav.listsAll
import acr.appcradle.shoppinglist.ui.screens.lists_all.nav.navigateListsAll
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavHost(
    scaffoldPaddings: PaddingValues,
    modifier: Modifier = Modifier
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
            onListClick = { navController.navigateToShoppingList() }
        )
        creationScreen(
            onBackClick = { navController.popBackStack() },
            onNextClick = { navController.navigateListsAll() })
        shoppingScreen(
            onBackClick = { navController.popBackStack() },
        )
    }
}

enum class RoutesList {
    GreetingRoute,
    ListsAllRoute,
    ListShoppingRoute,
    ListCreationRoute
}
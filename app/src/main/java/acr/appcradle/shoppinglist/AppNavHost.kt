package acr.appcradle.shoppinglist

import acr.appcradle.shoppinglist.ui.AppViewModel
import acr.appcradle.shoppinglist.ui.screens.greeting.nav.GreetingRoute
import acr.appcradle.shoppinglist.ui.screens.greeting.nav.greeting
import acr.appcradle.shoppinglist.ui.screens.list_creation.nav.creationScreen
import acr.appcradle.shoppinglist.ui.screens.list_creation.nav.navigateToListCreation
import acr.appcradle.shoppinglist.ui.screens.lists_all.nav.listsAll
import acr.appcradle.shoppinglist.ui.screens.lists_all.nav.navigateListsAll
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint

@Composable
fun AppNavHost(
    scaffoldPaddings: PaddingValues,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
//    val viewModel = AppViewModel()

    NavHost(
        navController = navController,
        startDestination = GreetingRoute,
        modifier = modifier.padding(scaffoldPaddings),
    ) {
        greeting { navController.navigateToListCreation() }
        listsAll(
//            viewModel = viewModel
        )
        creationScreen { }
    }
}
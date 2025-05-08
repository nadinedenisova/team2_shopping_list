package acr.appcradle.shoppinglist

import acr.appcradle.shoppinglist.di.DataModuleEntryPoint
import acr.appcradle.shoppinglist.model.ListElement
import acr.appcradle.shoppinglist.ui.screens.auth.nav.authScreen
import acr.appcradle.shoppinglist.ui.screens.auth.nav.navigateToAuth
import acr.appcradle.shoppinglist.ui.screens.greeting.nav.GreetingRoute
import acr.appcradle.shoppinglist.ui.screens.greeting.nav.greeting
import acr.appcradle.shoppinglist.ui.screens.listCreation.nav.creationScreen
import acr.appcradle.shoppinglist.ui.screens.listCreation.nav.navigateToListCreation
import acr.appcradle.shoppinglist.ui.screens.listShopping.nav.navigateToShoppingList
import acr.appcradle.shoppinglist.ui.screens.listShopping.nav.shoppingScreen
import acr.appcradle.shoppinglist.ui.screens.listsAll.nav.listsAll
import acr.appcradle.shoppinglist.ui.screens.listsAll.nav.navigateListsAll
import acr.appcradle.shoppinglist.ui.screens.register.nav.navigateToRegister
import acr.appcradle.shoppinglist.ui.screens.register.nav.registerScreen
import acr.appcradle.shoppinglist.utils.ThemeOption
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.EntryPointAccessors

@Composable
internal fun AppNavHost(
    scaffoldPaddings: PaddingValues,
    modifier: Modifier = Modifier,
    onThemeChange: (ThemeOption) -> Unit
) {
    val navController = rememberNavController()
    val authStateManager = EntryPointAccessors.fromApplication(
        context = LocalContext.current,
        entryPoint = DataModuleEntryPoint::class.java
    ).authStateManager()

    LaunchedEffect(Unit) {
        if (!authStateManager.checkAndRefreshToken()) {
            navController.navigateToAuth()
        }
    }

    NavHost(
        navController = navController,
        startDestination = GreetingRoute,
        modifier = modifier.padding(scaffoldPaddings),
    ) {
        authScreen(
            onNavigateToRegister = { navController.navigateToRegister() },
            onNavigateToRestorePassword = {},
            onLoginSuccess = { navController.navigateListsAll() }
        )

        registerScreen(
            onNavigateBack = { navController.popBackStack() },
            onRegisterSuccess = { navController.navigateToAuth() }
        )

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
        )

        shoppingScreen(
            onBackClick = { navController.popBackStack() },
        )
    }
}

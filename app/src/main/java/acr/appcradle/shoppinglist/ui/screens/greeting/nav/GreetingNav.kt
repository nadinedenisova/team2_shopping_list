package acr.appcradle.shoppinglist.ui.screens.greeting.nav

import acr.appcradle.shoppinglist.ui.screens.greeting.GreetingScreen
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object GreetingRoute

internal fun NavController.navigateToGreeting(navOptions: NavOptions) =
    navigate(route = GreetingRoute, navOptions)

internal fun NavGraphBuilder.greeting(
    onNextClick: () -> Unit
) {
    composable<GreetingRoute> {
        GreetingScreen(onNextClick = onNextClick)
    }
}

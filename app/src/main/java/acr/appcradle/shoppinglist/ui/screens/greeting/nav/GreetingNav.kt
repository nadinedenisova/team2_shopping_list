package acr.appcradle.shoppinglist.ui.screens.greeting.nav

import acr.appcradle.shoppinglist.ui.screens.greeting.Greeting
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object GreetingRoute

fun NavController.navigateToGreeting(navOptions: NavOptions) =
    navigate(route = GreetingRoute, navOptions)


fun NavGraphBuilder.greeting(
    onClick: () -> Unit
) {
    composable<GreetingRoute> {
        Greeting(onClick = onClick)
    }
}

package acr.appcradle.shoppinglist.ui.screens.register.nav

import acr.appcradle.shoppinglist.ui.screens.register.RegisterScreenUi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object RegisterRoute

internal fun NavController.navigateToRegister() = navigate(route = RegisterRoute)

internal fun NavGraphBuilder.registerScreen(
    onNavigateBack: () -> Unit,
    onRegisterSuccess: () -> Unit
) {
    composable<RegisterRoute> {
        RegisterScreenUi(
            onNavigateBack = onNavigateBack,
            onRegisterSuccess = onRegisterSuccess
        )
    }
} 
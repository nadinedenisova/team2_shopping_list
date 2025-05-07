package acr.appcradle.shoppinglist.ui.screens.auth.nav

import acr.appcradle.shoppinglist.ui.screens.auth.AuthScreenUi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object AuthRoute

internal fun NavController.navigateToAuth() = navigate(route = AuthRoute)

internal fun NavGraphBuilder.authScreen(
    onNavigateToRegister: () -> Unit,
    onNavigateToRestorePassword: () -> Unit,
    onLoginSuccess: () -> Unit
) {
    composable<AuthRoute> {
        AuthScreenUi(
            onNavigateToRegister = onNavigateToRegister,
            onNavigateToRestorePassword = onNavigateToRestorePassword,
            onLoginSuccess = onLoginSuccess
        )
    }
} 
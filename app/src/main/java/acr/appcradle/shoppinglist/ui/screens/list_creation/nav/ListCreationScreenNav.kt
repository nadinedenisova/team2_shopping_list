package acr.appcradle.shoppinglist.ui.screens.list_creation.nav

import acr.appcradle.shoppinglist.model.ListElement
import acr.appcradle.shoppinglist.ui.screens.list_creation.ListCreationScreen
import acr.appcradle.shoppinglist.utils.ThemeOption
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder


@Serializable
data object ListCreationRoute

private const val ROUTE_BASE = "listCreation"
private const val ARG_LIST_JSON = "listJson"

fun NavController.navigateToListCreation(list: ListElement? = null) {
    val route = if (list != null) {
        val json = Json.encodeToString(list)
        val encoded = URLEncoder.encode(json, "UTF-8")
        "$ROUTE_BASE?$ARG_LIST_JSON=$encoded"
    } else {
        ROUTE_BASE
    }
    navigate(route)
}



fun NavGraphBuilder.creationScreen(
    onBackClick: () -> Unit,
    onNextClick: () -> Unit,
    onThemeChange: (ThemeOption) -> Unit
) {
    composable(
        route = "$ROUTE_BASE?$ARG_LIST_JSON={$ARG_LIST_JSON}",
        arguments = listOf(
            navArgument(ARG_LIST_JSON) {
                type = NavType.StringType
                defaultValue = ""
                nullable = true
            }
        )
    ) { backStack ->
        val encoded = backStack.arguments?.getString(ARG_LIST_JSON).orEmpty()
        val existingList: ListElement? = encoded
            .takeIf { it.isNotBlank() }
            ?.let {
                val json = URLDecoder.decode(it, "UTF-8")
                Json.decodeFromString<ListElement>(json)
            }

        ListCreationScreen(
            onNextClick = onNextClick,
            onBackClick = onBackClick,
            existingList = existingList,
        )
    }

}

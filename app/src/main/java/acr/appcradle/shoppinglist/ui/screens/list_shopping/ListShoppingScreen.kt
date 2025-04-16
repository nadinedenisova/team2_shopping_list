package acr.appcradle.shoppinglist.ui.screens.list_shopping

import acr.appcradle.shoppinglist.data.ListRepositoryImpl
import acr.appcradle.shoppinglist.RoutesList
import acr.appcradle.shoppinglist.data.Repository
import acr.appcradle.shoppinglist.model.ShoppingElement
import acr.appcradle.shoppinglist.ui.AppViewModel
import acr.appcradle.shoppinglist.ui.components.AppNavTopBar
import acr.appcradle.shoppinglist.ui.theme.ShoppingListTheme
import acr.appcradle.shoppinglist.utils.ThemePreviews
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ListShoppingScreen(
    viewModel: AppViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
//    listOfItems: List<ShoppingElement>
) {
    val list = listOf<ShoppingElement>(
        ShoppingElement(
            name = "Яблоко",
            amount = "31",
            unit = "кг",
            checked = false
        ),
        ShoppingElement(
            name = "Груша",
            amount = "3",
            unit = "шт",
            checked = true
        ),
    )

    Scaffold(
        topBar = {
            AppNavTopBar(
                title = "Новый год",
                onMenuIconClick = {},
                onBackIconClick = { onBackClick() },
                onSearchIconClick = {},
                isBackIconEnable = true,
                isSearchIconEnabled = true,
                isMenuIconEnabled = true,
                screenRoute = RoutesList.ListShoppingRoute
            )
        }
    ) { innerPaddings ->
        Box(modifier = Modifier.padding(innerPaddings)) {
            if (list.isEmpty())
                EmptyListUi()
            else
                FilledListUi(listOfItems = list)
        }
    }
}


@ThemePreviews
@Composable
private fun ListShoppingPreview() {
    ShoppingListTheme {
        Surface {
            ListShoppingScreen(
                viewModel = AppViewModel(Repository()),
                onBackClick = {},
//                listOfItems = emptyList(),
            )
        }
    }
}
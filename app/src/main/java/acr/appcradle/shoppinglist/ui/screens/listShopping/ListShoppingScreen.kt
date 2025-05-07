package acr.appcradle.shoppinglist.ui.screens.listShopping

import acr.appcradle.shoppinglist.model.ShoppingListIntent
import acr.appcradle.shoppinglist.ui.components.AppNavTopBar
import acr.appcradle.shoppinglist.ui.components.DropDownMenus
import acr.appcradle.shoppinglist.ui.screens.listShopping.coomponents.EmptyListUi
import acr.appcradle.shoppinglist.ui.screens.listShopping.coomponents.FilledListUi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun ListShoppingScreen(
    viewModel: ShoppingListViewModel = hiltViewModel(),
    listId: Long,
    onBackClick: () -> Unit,
    listName: String,
) {
    val state = viewModel.state.collectAsState().value
    val context = LocalContext.current

    LaunchedEffect(listId) {
        viewModel.handleIntent(ShoppingListIntent.LoadItems(listId))
    }

    Scaffold(
        topBar = {
            AppNavTopBar(
                title = "$listName.",
                onBackIconClick = { onBackClick() },
                onSearchIconClick = {},
                isBackIconEnable = true,
                isSearchIconEnabled = true,
                dropDownMenu = {
                    DropDownMenus.ShoppingListMenu(
                        listId = listId,
                        onShareClick = {
                            viewModel.handleIntent(
                                ShoppingListIntent.ShareList(
                                    name = listName,
                                    list = state.items,
                                    context = context
                                )
                            )
                        }
                    )
                }
            )
        }
    ) { innerPaddings ->
        Box(modifier = Modifier.padding(innerPaddings)) {
            if (state.items.isEmpty()) {
                EmptyListUi(viewModel = viewModel, listId = listId)
            } else {
                FilledListUi(listOfItems = state.items, viewModel = viewModel, listId = listId)
            }
        }
    }
}

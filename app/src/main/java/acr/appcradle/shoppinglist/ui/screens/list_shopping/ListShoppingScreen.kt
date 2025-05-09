package acr.appcradle.shoppinglist.ui.screens.list_shopping

import acr.appcradle.shoppinglist.model.AppIntents
import acr.appcradle.shoppinglist.ui.AppViewModel
import acr.appcradle.shoppinglist.ui.components.AppNavTopBar
import acr.appcradle.shoppinglist.ui.components.DropDownMenus
import acr.appcradle.shoppinglist.utils.sendList
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ListShoppingScreen(
    viewModel: AppViewModel = hiltViewModel(),
    listId: Long,
    onBackClick: () -> Unit,
    listName: String,
) {
    LaunchedEffect(listId) {
        viewModel.actionIntent(AppIntents.LoadItems(listId))
    }
    val list = viewModel.itemsList.collectAsStateWithLifecycle().value
    val context = LocalContext.current
    Scaffold(
        topBar = {
            AppNavTopBar(
                title = listName,
                onBackIconClick = { onBackClick() },
                onSearchIconClick = {},
                isBackIconEnable = true,
                isSearchIconEnabled = true,
                dropDownMenu = {
                    DropDownMenus.ShoppingListMenu(
                        listId = listId,
                        onShareClick = {
//                            viewModel.actionIntent(
                            sendList(
                                intent =
                                    AppIntents.ShareList(
                                        name = listName,
                                        list = list,
                                        context = context
                                    )
                            )
                        })
                }
            )
        }
    ) { innerPaddings ->
        Box(modifier = Modifier.padding(innerPaddings)) {
            if (list.isEmpty())
                EmptyListUi(viewModel = viewModel, listId = listId)
            else
                FilledListUi(listOfItems = list, viewModel = viewModel, listId = listId)
        }
    }
}


//@ThemePreviews
//@Composable
//private fun ListShoppingPreview() {
//    ShoppingListTheme {
//        Surface {
//            ListShoppingScreen(
//                viewModel = AppViewModel(Repository()),
//                onBackClick = {},
////                listOfItems = emptyList(),
//            )
//        }
//    }
//}
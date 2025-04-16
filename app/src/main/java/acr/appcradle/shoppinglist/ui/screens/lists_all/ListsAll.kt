package acr.appcradle.shoppinglist.ui.screens.lists_all

import acr.appcradle.shoppinglist.RoutesList
import acr.appcradle.shoppinglist.model.ListElement
import acr.appcradle.shoppinglist.model.ListsScreenState
import acr.appcradle.shoppinglist.ui.AppViewModel
import acr.appcradle.shoppinglist.ui.components.AppNavTopBar
import acr.appcradle.shoppinglist.utils.ThemePreviews
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun ListsAll(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel = hiltViewModel(),
    onNextClick: () -> Unit
) {
    val state by viewModel.listsAllState.collectAsState()

    Scaffold(
        topBar = {
            AppNavTopBar(
                title = "Мои списки",
                onMenuIconClick = {},
                isMenuIconEnabled = true,
                onBackIconClick = {},
                screenRoute = RoutesList.ListsAllRoute
            )
        }
    ) { innerPaddings ->
        Column(
            modifier = Modifier.padding(innerPaddings),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            when {
                state.isLoading -> {
                    EmptyListAllUi()
                }
                else -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                       items(state.list) {
                           ListsItem(
                               title = it.listName,
                               icon = it.icon,
                               iconBackground = it.iconBackground,
                               boughtCount = it.boughtCount,
                               totalCount = it.totalCount
                           )
                       }
                    }
                }

            }
        }

    }
}

@ThemePreviews
@Composable
fun ListsAllPreview() {
    val fakeState = ListsScreenState(
        isLoading = false,
        list = listOf(
            ListElement(
                id = null,
                listName = "Продукты",
                icon = Icons.Default.ShoppingCart,
                iconBackground = Color(0xFFE57373),
                boughtCount = 3,
                totalCount = 10
            ),
            ListElement(
                id = null,
                listName = "Дом",
                icon = Icons.Default.Home,
                iconBackground = Color(0xFF64B5F6),
                boughtCount = 1,
                totalCount = 5
            )
        )
    )

//    ShoppingListTheme {
//        Surface {
//            ListsAll(state = fakeState)
//        }
//    }
}
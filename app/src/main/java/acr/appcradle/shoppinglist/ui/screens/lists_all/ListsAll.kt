package acr.appcradle.shoppinglist.ui.screens.lists_all

import acr.appcradle.shoppinglist.model.AppIntents
import acr.appcradle.shoppinglist.ui.AppViewModel
import acr.appcradle.shoppinglist.ui.components.AppNavTopBar
import acr.appcradle.shoppinglist.ui.components.DropDownMenus
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun ListsAll(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel = hiltViewModel(),
    createNewListClick: () -> Unit,
    onListClick: () -> Unit
) {
    val state by viewModel.listsAllState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.actionIntent(AppIntents.LoadList)
    }

    Scaffold(
        topBar = {
            AppNavTopBar(
                title = "Мои списки",
                onBackIconClick = {

                },
                dropDownMenu = { DropDownMenus.AllListsMenu() }
            )
        },
        floatingActionButton = {
            if (!state.isEmpty) {
                FloatingActionButton(
                    onClick = { createNewListClick() }
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Добавить список")
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPaddings ->
        Column(
            modifier = Modifier.padding(innerPaddings),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            when {
                state.isLoading -> {
                    CircularProgressIndicator()
                }

                state.isEmpty -> {
                    EmptyListAllUi { createNewListClick() }
                }

                else -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        items(state.list) { item ->
                            SwipeableListsItem(
                                icon = item.icon,
                                title = item.listName,
                                iconBackground = item.iconBackground,
                                totalCount = item.totalCount,
                                boughtCount = item.boughtCount,
                                onEdit = {

                                },
                                onDuplicate = {

                                },
                                onDelete = {
                                    viewModel.actionIntent(AppIntents.DeleteItem(item.id))
                                },
                                onListClick = { onListClick() }
                            )
                        }
                    }

                }

            }
        }
    }
}

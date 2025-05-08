package acr.appcradle.shoppinglist.ui.screens.listShopping.coomponents

import acr.appcradle.shoppinglist.R
import acr.appcradle.shoppinglist.model.ShoppingElement
import acr.appcradle.shoppinglist.model.ShoppingListIntent
import acr.appcradle.shoppinglist.ui.components.AppBottomSheets
import acr.appcradle.shoppinglist.ui.components.AppInputFields
import acr.appcradle.shoppinglist.ui.components.AppSwipeAbleListItem
import acr.appcradle.shoppinglist.ui.screens.listShopping.ShoppingListViewModel
import acr.appcradle.shoppinglist.ui.theme.Team2Colors
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
internal fun FilledListUi(
    listOfItems: List<ShoppingElement>,
    viewModel: ShoppingListViewModel,
    listId: Long
) {
    var editItemBottomSheetVisibility by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<ShoppingElement?>(null) }
    var searchText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            AppInputFields.MainInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                placeholderText = stringResource(R.string.enter_item_name_placeholder),
                isSearchIconNeeded = true,
                onValueChange = { searchText = it }
            )
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(
                    listOfItems.filter { item ->
                        item.name.contains(searchText, ignoreCase = true)
                    }
                ) { item ->
                    AppSwipeAbleListItem.SwipeAbleShoppingItems(
                        item = item,
                        onEdit = {
                            selectedItem = item
                            editItemBottomSheetVisibility = true
                        },
                        onDelete = {
                            viewModel.handleIntent(ShoppingListIntent.DeleteItem(item.id, listId))
                        },
                        onItemClick = {
                            viewModel.handleIntent(ShoppingListIntent.UpdateItemCheck(item))
                        }
                    )
                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White,
                        thickness = 1.dp,
                    )
                }
            }
        }

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .size(48.dp),
            onClick = { editItemBottomSheetVisibility = true },
            containerColor = Team2Colors.tortoise
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(R.string.add_product),
                tint = Color.White
            )
        }
    }

    if (editItemBottomSheetVisibility) {
        AppBottomSheets.AddItemDialog(
            onDismissCallback = {
                editItemBottomSheetVisibility = false
                selectedItem = null
            },
            onAddClick = { viewModel.handleIntent(ShoppingListIntent.AddItem(it)) },
            onEditClick = { viewModel.handleIntent(ShoppingListIntent.UpdateItem(it)) },
            editItem = selectedItem,
            listId = listId
        )
    }
}

package acr.appcradle.shoppinglist.ui.screens.listShopping.coomponents

import acr.appcradle.shoppinglist.model.ShoppingElement
import acr.appcradle.shoppinglist.model.ShoppingListIntent
import acr.appcradle.shoppinglist.ui.components.AppBottomSheets
import acr.appcradle.shoppinglist.ui.components.AppSwipeAbleListItem
import acr.appcradle.shoppinglist.ui.screens.listShopping.ShoppingListViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun FilledListUi(
    listOfItems: List<ShoppingElement>,
    viewModel: ShoppingListViewModel,
    listId: Long
) {
    var editItemBottomSheetVisibility by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<ShoppingElement?>(null) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(listOfItems) { item ->
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
                }
            }
        }

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .size(48.dp),
            onClick = { editItemBottomSheetVisibility = true }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Добавить товар"
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

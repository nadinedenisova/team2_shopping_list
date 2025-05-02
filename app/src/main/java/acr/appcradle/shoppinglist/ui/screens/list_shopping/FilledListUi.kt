package acr.appcradle.shoppinglist.ui.screens.list_shopping

import acr.appcradle.shoppinglist.model.AppIntents
import acr.appcradle.shoppinglist.model.ShoppingElement
import acr.appcradle.shoppinglist.ui.AppViewModel
import acr.appcradle.shoppinglist.ui.components.AppBottomSheets
import acr.appcradle.shoppinglist.ui.components.AppInputFields
import acr.appcradle.shoppinglist.ui.components.AppSwipeAbleListItem
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilledListUi(
    modifier: Modifier = Modifier,
    listId: Long,
    listOfItems: List<ShoppingElement>,
    viewModel: AppViewModel
) {
    var addItemBottomSheetVisibility by remember { mutableStateOf(false) }
    var editItemBottomSheetVisibility by remember { mutableStateOf(false) }
    var editItem: ShoppingElement? by remember { mutableStateOf(null) }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            AppInputFields.MainInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                isSearchIconNeeded = true,
                placeholderText = "Введите название товара"
            )
            listOfItems.forEach { item ->
                AppSwipeAbleListItem.SwipeAbleShoppingItems(
                    item = item,
                    onEdit = {
                        editItem = item
                        editItemBottomSheetVisibility = true
                    },
                    onDelete = { viewModel.actionIntent(AppIntents.DeleteItem(item.id)) },
                    onItemClick = { viewModel.actionIntent(AppIntents.UpdateItemCheck(item = item)) },
                )
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .padding(16.dp)
                .size(48.dp),
            onClick = { addItemBottomSheetVisibility = true }
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
        if (addItemBottomSheetVisibility)
            AppBottomSheets.AddItemDialog(
                onDismissCallback = { addItemBottomSheetVisibility = false },
                onAddClick = {
                    viewModel.actionIntent(AppIntents.AddItem(item = it))
                },
                listId = listId
            )
        if (editItemBottomSheetVisibility)
            AppBottomSheets.AddItemDialog(
                onDismissCallback = { editItemBottomSheetVisibility = false },
                listId = listId,
                editItem = editItem,
                onEditClick = {
                    viewModel.actionIntent(AppIntents.UpdateItem(item = it))
                    editItemBottomSheetVisibility = false
                }
            )
    }
}


//@ThemePreviews
//@Composable
//private fun Preview() {
//    ShoppingListTheme {
//        Surface {
//            FilledListUi(
//                listOfItems = listOf(
//                    ShoppingElement(
//                        name = "Яблоко",
//                        amount = "31",
//                        unit = "кг",
//                        checked = false
//                    ),
//                    ShoppingElement(
//                        name = "Груша",
//                        amount = "3",
//                        unit = "шт",
//                        checked = true
//                    ),
//                    ShoppingElement(
//                        name = "Апельсины",
//                        amount = "3",
//                        unit = "шт",
//                        checked = false
//                    ),
//                ),
//                viewModel = viewModel
//            )
//        }
//    }
//}
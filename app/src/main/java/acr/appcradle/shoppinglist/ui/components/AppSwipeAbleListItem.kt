package acr.appcradle.shoppinglist.ui.components

import acr.appcradle.shoppinglist.R
import acr.appcradle.shoppinglist.model.ShoppingElement
import acr.appcradle.shoppinglist.ui.screens.listShopping.ShoppingListItem
import acr.appcradle.shoppinglist.ui.theme.Team2Colors
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

internal object AppSwipeAbleListItem {

    @Composable
    fun SwipeAbleShoppingItems(
        modifier: Modifier = Modifier,
        item: ShoppingElement,
        onEdit: () -> Unit,
        onDelete: () -> Unit,
        onItemClick: () -> Unit,
    ) {
        val dismissState = rememberSwipeToDismissBoxState()
        val scope = rememberCoroutineScope()
        val shouldShowDialog = remember { mutableStateOf(false) }

        DeleteDialog(
            visibility = shouldShowDialog.value,
            onDismissRequest = { shouldShowDialog.value = false },
            title = "Удаление товара",
            message = "Вы действительно хотите удалить товар?"
        ) {
            onDelete()
        }

        SwipeToDismissBox(
            modifier = modifier.clickable { onItemClick() },
            state = dismissState,
            backgroundContent = {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        modifier = Modifier
                            .background(Team2Colors.team2colors_lightGray)
                            .size(72.dp),
                        onClick = {
                            onEdit()
                            scope.launch {
                                dismissState.snapTo(SwipeToDismissBoxValue.Settled)
                            }
                        }

                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_edit),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }

                    IconButton(
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(
                                    topEnd = 12.dp,
                                    bottomEnd = 12.dp
                                )
                            )
                            .background(Team2Colors.team2color_red)
                            .size(72.dp),
                        onClick = {
                            shouldShowDialog.value = true
                            scope.launch {
                                dismissState.snapTo(SwipeToDismissBoxValue.Settled)
                            }
                        }

                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_delete),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            },
            enableDismissFromStartToEnd = false,
        ) {
            ShoppingListItem(
                item = item,
                onCheckedChange = { onItemClick() }
            )
        }
    }
}

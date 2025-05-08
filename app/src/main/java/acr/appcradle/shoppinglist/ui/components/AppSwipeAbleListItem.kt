package acr.appcradle.shoppinglist.ui.components

import acr.appcradle.shoppinglist.R
import acr.appcradle.shoppinglist.model.ShoppingElement
import acr.appcradle.shoppinglist.ui.screens.listShopping.coomponents.ShoppingListItem
import acr.appcradle.shoppinglist.ui.theme.Team2Colors
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

internal object AppSwipeAbleListItem {

    @Composable
    fun SwipeAbleShoppingItems(
        modifier: Modifier = Modifier,
        item: ShoppingElement,
        onEdit: () -> Unit,
        onDelete: () -> Unit,
        onItemClick: () -> Unit,
    ) {
        val scope = rememberCoroutineScope()
        val iconSize = 56.dp
        val maxSwipe = iconSize * 2
        val maxSwipePx = with(LocalDensity.current) { maxSwipe.toPx() }
        val offsetX = remember { Animatable(0f) }

        val dragState = rememberDraggableState { delta ->
            scope.launch {
                val new = (offsetX.value + delta).coerceIn(-maxSwipePx, 0f)
                offsetX.snapTo(new)
            }
        }

        val shouldShowDialog = remember { mutableStateOf(false) }

        DeleteDialog(
            visibility = shouldShowDialog.value,
            onDismissRequest = { shouldShowDialog.value = false },
            title = stringResource(R.string.delete_product),
            message = stringResource(R.string.confirmation_deletion_product)
        ) {
            onDelete()
            shouldShowDialog.value = false
        }

        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.width(maxSwipe),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        modifier = Modifier
                            .size(iconSize)
                            .background(Team2Colors.team2colors_lightGray),
                        onClick = {
                            onEdit()
                            scope.launch { offsetX.animateTo(0f) }
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_edit),
                            contentDescription = "Edit",
                            tint = Color.White
                        )
                    }

                    IconButton(
                        modifier = Modifier
                            .size(iconSize)
                            .background(Team2Colors.team2color_red),
                        onClick = {
                            shouldShowDialog.value = true
                            scope.launch { offsetX.animateTo(0f) }
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_delete),
                            contentDescription = "Delete",
                            tint = Color.White
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .offset { IntOffset(offsetX.value.roundToInt(), 0) }
                    .draggable(
                        state = dragState,
                        orientation = Orientation.Horizontal,
                        onDragStopped = {
                            scope.launch {
                                if (offsetX.value < -maxSwipePx / 2) {
                                    offsetX.animateTo(-maxSwipePx)
                                } else {
                                    offsetX.animateTo(0f)
                                }
                            }
                        }
                    )
                    .clickable { onItemClick() }
            ) {
                ShoppingListItem(
                    item = item,
                    onCheckedChange = { onItemClick() }
                )
            }
        }
    }
}

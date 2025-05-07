package acr.appcradle.shoppinglist.ui.screens.listsAll

import acr.appcradle.shoppinglist.R
import acr.appcradle.shoppinglist.ui.components.DeleteDialog
import acr.appcradle.shoppinglist.ui.theme.Team2Colors
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
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

@Composable
internal fun SwipeListsItem(
    icon: Int,
    title: String,
    iconBackground: Color,
    boughtCount: Int,
    totalCount: Int,
    onEdit: () -> Unit,
    onDuplicate: () -> Unit,
    onDelete: () -> Unit,
    onListClick: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val iconSize = 72.dp
    val maxSwipe = iconSize * 3
    val maxSwipePx = with(LocalDensity.current) { maxSwipe.toPx() }
    val shouldShowDialog = remember { mutableStateOf(false) }

    DeleteDialog(
        visibility = shouldShowDialog.value,
        onDismissRequest = { shouldShowDialog.value = false },
        title = stringResource(R.string.delete_list),
        message = stringResource(R.string.confirmation_deletion_list)
    ) {
        onDelete()
    }

    val offsetX = remember { Animatable(0f) }

    val dragState = rememberDraggableState { delta ->
        scope.launch {
            val new = (offsetX.value + delta).coerceIn(-maxSwipePx, 0f)
            offsetX.snapTo(new)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
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
                        .background(Team2Colors.team2color_orange),
                    onClick = {
                        onDuplicate()
                        scope.launch { offsetX.animateTo(0f) }
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_copy),
                        contentDescription = "Duplicate",
                        tint = Color.White
                    )
                }

                IconButton(
                    modifier = Modifier
                        .size(iconSize)
                        .clip(RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp))
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
                    onDragStopped = { velocity ->
                        scope.launch {
                            if (offsetX.value < -maxSwipePx / 2) {
                                offsetX.animateTo(-maxSwipePx, spring())
                            } else {
                                offsetX.animateTo(0f, spring())
                            }
                        }
                    }
                )
                .clickable { onListClick() }
        ) {
            ListsItem(
                modifier = Modifier.fillMaxWidth(),
                icon = icon,
                title = title,
                iconBackground = iconBackground,
                boughtCount = boughtCount,
                totalCount = totalCount
            )
        }
    }
}

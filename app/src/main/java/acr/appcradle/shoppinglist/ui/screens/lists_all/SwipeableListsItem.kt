package acr.appcradle.shoppinglist.ui.screens.lists_all

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun SwipeableListsItem(
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
    val editAction = SwipeAction(
        icon = {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit",
                tint = Color.White
            )
        },
        background = Color(0xFFBDBDBD),
        onSwipe = onEdit
    )

    val duplicateAction = SwipeAction(
        icon = {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Duplicate",
                tint = Color.White
            )
        },
        background = Color(0xFFFFA000),
        onSwipe = onDuplicate
    )

    val deleteAction = SwipeAction(
        icon = {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                tint = Color.White
            )
        },
        background = Color(0xFFFF5252),
        onSwipe = onDelete
    )

    SwipeableActionsBox(
        modifier = Modifier.clickable { onListClick() },
        endActions = listOf(editAction, duplicateAction, deleteAction),
        swipeThreshold = 100.dp
    ) {
        ListsItem(
            icon = icon,
            title = title,
            iconBackground = iconBackground,
            boughtCount = boughtCount,
            totalCount = totalCount
        )
    }
}
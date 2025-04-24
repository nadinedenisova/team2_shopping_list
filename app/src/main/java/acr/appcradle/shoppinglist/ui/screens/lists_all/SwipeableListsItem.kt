package acr.appcradle.shoppinglist.ui.screens.lists_all

import acr.appcradle.shoppinglist.R
import acr.appcradle.shoppinglist.ui.theme.ShoppingListTheme
import acr.appcradle.shoppinglist.ui.theme.Team2Colors
import acr.appcradle.shoppinglist.utils.ThemePreviews
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


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
    val dismissState = rememberSwipeToDismissBoxState()
    val scope = rememberCoroutineScope()

    val maxSwipeOffset = 216.dp

    SwipeToDismissBox(
        modifier = Modifier.clickable {
            onListClick()
        },
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
                        .background(Team2Colors.team2color_orange)
                        .size(72.dp),
                    onClick = {
                        onDuplicate()
                        scope.launch {
                            dismissState.snapTo(SwipeToDismissBoxValue.Settled)
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_copy),
                        contentDescription = null,
                        tint = Color.White
                    )
                }

                IconButton(
                    modifier = Modifier
                        .clip(RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp))
                        .background(Team2Colors.team2color_red)
                        .size(72.dp),
                    onClick = {
                        onDelete()
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
        }
    ) {
        ListsItem(
            icon = icon,
            title = title,
            iconBackground = iconBackground,
            boughtCount = boughtCount,
            totalCount = totalCount,

            )
    }
}

@ThemePreviews
@Composable
private fun ShoppingListItemPreview() {
    ShoppingListTheme {
        Surface {
            SwipeableListsItem(
                icon = TODO(),
                title = "dfdfg",
                iconBackground = TODO(),
                boughtCount = 3,
                totalCount = 34,
                onEdit = TODO(),
                onDuplicate = TODO(),
                onDelete = TODO()
            ) { }
        }
    }
}
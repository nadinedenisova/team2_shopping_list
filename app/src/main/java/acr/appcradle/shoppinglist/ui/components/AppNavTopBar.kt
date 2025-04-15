package acr.appcradle.shoppinglist.ui.components

import acr.appcradle.shoppinglist.ui.theme.ShoppingListTheme
import acr.appcradle.shoppinglist.ui.theme.Typography
import acr.appcradle.shoppinglist.utils.ThemePreviews
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun AppNavTopBar(
    title: String,
    onBackIconClick: () -> Unit,
    onMenuIconClick: () -> Unit = {},
    onSearchIconClick: () -> Unit = {},
    isBackIconEnable: Boolean = false,
    isSearchIconEnabled: Boolean = false,
    isMenuIconEnabled: Boolean = false,
) {

    val iconsInnerSize = 22.dp

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if(isBackIconEnable) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 8.dp)
                    .size(48.dp)
                    .clickable(enabled = true) { onBackIconClick() },
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    modifier = Modifier.size(iconsInnerSize),
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        }
        Text(
            modifier = Modifier
                .weight(1f).padding(start = 20.dp),
            text = title,
            style = Typography.titleLarge,
            overflow = TextOverflow.Ellipsis
        )
        if (isSearchIconEnabled) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 8.dp)
                    .size(48.dp)
                    .clickable(enabled = true) { onSearchIconClick() },
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    modifier = Modifier.size(iconsInnerSize),
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            }
        }
        if (isMenuIconEnabled) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 8.dp)
                    .size(48.dp)
                    .clickable(enabled = true) { onMenuIconClick() },
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    modifier = Modifier.size(iconsInnerSize),
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = null
                )
            }
        }
    }
}

@ThemePreviews
@Composable
fun GreetingPreview() {
    ShoppingListTheme {
        Surface {
            AppNavTopBar(
                title = "Создать список",
                onBackIconClick = {},
                isMenuIconEnabled = true,
                isSearchIconEnabled = true
            )
        }
    }
}
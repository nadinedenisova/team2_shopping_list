package acr.appcradle.shoppinglist.ui.components

import acr.appcradle.shoppinglist.model.ShoppingListIntent
import acr.appcradle.shoppinglist.ui.screens.listShopping.ShoppingListViewModel
import acr.appcradle.shoppinglist.ui.theme.Typography
import acr.appcradle.shoppinglist.utils.ThemeOption
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

internal object DropDownMenus {

    @Composable
    fun ShoppingListMenu(
        listId: Long,
        viewModel: ShoppingListViewModel = hiltViewModel(),
        onShareClick: () -> Unit
    ) {
        var expanded by remember { mutableStateOf(false) }
        var openDeleteAllDialog by remember { mutableStateOf(false) }
        var openDeleteDialog by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .padding(horizontal = 4.dp, vertical = 8.dp)
                .size(48.dp)
                .clickable(enabled = true) { expanded = !expanded },
            contentAlignment = Alignment.Center,
        ) {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(Icons.Default.MoreVert, contentDescription = "More options")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Сортировать по алфавиту") },
                    onClick = {
                        viewModel.handleIntent(ShoppingListIntent.LoadItems(listId, true))
                        expanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Поделиться") },
                    onClick = {
                        onShareClick()
                    }
                )
                DropdownMenuItem(
                    text = { Text("Снять отметки со всех товаров") },
                    onClick = {
                        viewModel.handleIntent(ShoppingListIntent.MakeAllUnChecked(listId))
                        expanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Удалить купленные товары") },
                    onClick = { openDeleteAllDialog = true }
                )
            }
            if (openDeleteAllDialog) {
                AppDialogs.MainAppDialog(
                    dialogTitle = "Удаление купленных товаров",
                    dialogText = "Вы действительно хотите удалить все купленные товары?",
                    onDismissRequest = { openDeleteAllDialog = false },
                    onConfirmation = {
                        viewModel.handleIntent(ShoppingListIntent.DeleteAllChecked(listId))
                        openDeleteAllDialog = false
                        expanded = false
                    }
                )
            }
            if (openDeleteDialog) {
                AppDialogs.MainAppDialog(
                    dialogTitle = "Удаление товара",
                    dialogText = "Вы действительно хотите удалить товар?",
                    onDismissRequest = { openDeleteAllDialog = false },
                    onConfirmation = {
                        openDeleteDialog = false
                    }
                )
            }
        }
    }

    @Composable
    internal fun AllListsMenu(
        viewModel: ShoppingListViewModel = hiltViewModel(),
        onThemeChange: (ThemeOption) -> Unit
    ) {
        var expanded by remember { mutableStateOf(false) }
        var themeMenuExpanded by remember { mutableStateOf(false) }
        Box(
            modifier = Modifier
                .padding(horizontal = 4.dp, vertical = 8.dp)
                .size(48.dp)
                .clickable(enabled = true) { expanded = !expanded },
            contentAlignment = Alignment.Center,
        ) {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(Icons.Default.MoreVert, contentDescription = "More options")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Сортировать по алфавиту") },
                    onClick = { viewModel.handleIntent(ShoppingListIntent.LoadItems(0, true)) }
                )
                DropdownMenuItem(
                    text = { Text("Установить тему") },
                    onClick = { themeMenuExpanded = !themeMenuExpanded }
                )
            }
            DropdownMenu(
                modifier = Modifier.width(200.dp),
                expanded = themeMenuExpanded,
                onDismissRequest = { themeMenuExpanded = false },
                offset = DpOffset(x = (-50).dp, y = (110).dp)
            ) {
                Text(
                    modifier = Modifier.padding(12.dp),
                    text = "Установить тему",
                    style = Typography.bodyMedium
                )
                DropdownMenuItem(
                    text = { Text("Системная") },
                    onClick = {
                        onThemeChange(ThemeOption.SYSTEM)
                        themeMenuExpanded = false
                        expanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Темная") },
                    onClick = {
                        onThemeChange(ThemeOption.DARK)
                        themeMenuExpanded = false
                        expanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Светлая") },
                    onClick = {
                        onThemeChange(ThemeOption.LIGHT)
                        themeMenuExpanded = false
                        expanded = false
                    }
                )
            }
        }
    }
}

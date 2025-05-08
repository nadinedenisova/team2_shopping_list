package acr.appcradle.shoppinglist.ui.components

import acr.appcradle.shoppinglist.R
import acr.appcradle.shoppinglist.model.AppIntents
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
import androidx.compose.ui.res.stringResource
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
                    text = { Text(stringResource(R.string.sort_alphabetically)) },
                    onClick = {
                        viewModel.handleIntent(ShoppingListIntent.LoadItems(listId, true))
                        expanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.share)) },
                    onClick = {
                        onShareClick()
                    }
                )
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.remove_all_marks)) },
                    onClick = {
                        viewModel.handleIntent(ShoppingListIntent.MakeAllUnChecked(listId))
                        expanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.delete_all_products)) },
                    onClick = { openDeleteAllDialog = true }
                )
            }
            if (openDeleteAllDialog) {
                AppDialogs.MainAppDialog(
                    dialogTitle = stringResource(R.string.deleting_purchased_product),
                    dialogText = stringResource(R.string.confirmation_deletion_purchased_product),
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
                    dialogTitle = stringResource(R.string.delete_product),
                    dialogText = stringResource(R.string.confirmation_deletion_product),
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
                    text = { Text(stringResource(R.string.sort_alphabetically)) },
                    onClick = { viewModel.handleIntent(ShoppingListIntent.LoadItems(0, true)) }
                )
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.set_theme)) },
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
                    text = stringResource(R.string.set_theme),
                    style = Typography.bodyMedium
                )
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.system_theme)) },
                    onClick = {
                        onThemeChange(ThemeOption.SYSTEM)
                        themeMenuExpanded = false
                        expanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.dark_theme)) },
                    onClick = {
                        onThemeChange(ThemeOption.DARK)
                        themeMenuExpanded = false
                        expanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.light_theme)) },
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

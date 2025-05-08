package acr.appcradle.shoppinglist.ui.components

import acr.appcradle.shoppinglist.R
import acr.appcradle.shoppinglist.model.ListsIntent
import acr.appcradle.shoppinglist.model.ShoppingListIntent
import acr.appcradle.shoppinglist.ui.screens.listShopping.ShoppingListViewModel
import acr.appcradle.shoppinglist.ui.screens.listsAll.ListsViewModel
import acr.appcradle.shoppinglist.ui.theme.Team2Colors
import acr.appcradle.shoppinglist.ui.theme.Typography
import acr.appcradle.shoppinglist.utils.ThemeOption
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
        val interactionSource = remember { MutableInteractionSource() }
        val isPressed by interactionSource.collectIsPressedAsState()

        Box(
            modifier = Modifier
                .padding(horizontal = 4.dp, vertical = 8.dp)
                .size(48.dp),
            contentAlignment = Alignment.Center,
        ) {
            IconButton(
                modifier = Modifier.size(48.dp),
                onClick = { expanded = !expanded },
                colors = IconButtonDefaults.iconButtonColors(containerColor = if (isPressed) Team2Colors.tortoiseLight else Color.Transparent),
                interactionSource = interactionSource
            ) {
                Icon(
                    Icons.Default.MoreVert,
                    contentDescription = "More options",
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                val sortInteractionSource = remember { MutableInteractionSource() }
                val isSortPressed by sortInteractionSource.collectIsPressedAsState()

                val shareInteractionSource = remember { MutableInteractionSource() }
                val isSharePressed by shareInteractionSource.collectIsPressedAsState()

                val removeMarksInteractionSource = remember { MutableInteractionSource() }
                val isRemoveMarksPressed by removeMarksInteractionSource.collectIsPressedAsState()

                val deleteAllInteractionSource = remember { MutableInteractionSource() }
                val isDeleteAllPressed by deleteAllInteractionSource.collectIsPressedAsState()

                DropdownMenuItem(
                    text = { Text(stringResource(R.string.sort_alphabetically)) },
                    onClick = {
                        viewModel.handleIntent(ShoppingListIntent.LoadItems(listId, true))
                        expanded = false
                    },
                    modifier = Modifier.background(if (isSortPressed) Team2Colors.tortoiseLight else Color.Transparent),
                    interactionSource = sortInteractionSource
                )
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.share)) },
                    onClick = {
                        onShareClick()
                    },
                    modifier = Modifier.background(if (isSharePressed) Team2Colors.tortoiseLight else Color.Transparent),
                    interactionSource = shareInteractionSource
                )
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.remove_all_marks)) },
                    onClick = {
                        viewModel.handleIntent(ShoppingListIntent.MakeAllUnChecked(listId))
                        expanded = false
                    },
                    modifier = Modifier.background(if (isRemoveMarksPressed) Team2Colors.tortoiseLight else Color.Transparent),
                    interactionSource = removeMarksInteractionSource
                )
                DropdownMenuItem(
                    text = {Text(
                        text = stringResource(R.string.delete_all_products),
                        color = Team2Colors.team2color_red) },
                    onClick = { openDeleteAllDialog = true },
                    modifier = Modifier.background(if (isDeleteAllPressed) Team2Colors.tortoiseLight else Color.Transparent),
                    interactionSource = deleteAllInteractionSource,

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
        viewModel: ListsViewModel = hiltViewModel(),
        onThemeChange: (ThemeOption) -> Unit
    ) {
        var expanded by remember { mutableStateOf(false) }
        var themeMenuExpanded by remember { mutableStateOf(false) }
        val interactionSource = remember { MutableInteractionSource() }
        val isPressed by interactionSource.collectIsPressedAsState()

        Box(
            modifier = Modifier
                .padding(horizontal = 4.dp, vertical = 8.dp)
                .size(48.dp),
            contentAlignment = Alignment.Center,
        ) {
            IconButton(
                modifier = Modifier.size(48.dp),
                onClick = { expanded = !expanded },
                colors = IconButtonDefaults.iconButtonColors(containerColor = if (isPressed) Team2Colors.tortoiseLight else Color.Transparent),
                interactionSource = interactionSource
            ) {
                Icon(
                    Icons.Default.MoreVert,
                    contentDescription = "More options",
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                val sortInteractionSource = remember { MutableInteractionSource() }
                val isSortPressed by sortInteractionSource.collectIsPressedAsState()

                val themeInteractionSource = remember { MutableInteractionSource() }
                val isThemePressed by themeInteractionSource.collectIsPressedAsState()

                DropdownMenuItem(
                    text = { Text(stringResource(R.string.sort_alphabetically)) },
                    onClick = { viewModel.handleIntent(ListsIntent.LoadLists(true)) },
                    modifier = Modifier.background(if (isSortPressed) Team2Colors.tortoiseLight else Color.Transparent),
                    interactionSource = sortInteractionSource
                )
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.set_theme)) },
                    onClick = { themeMenuExpanded = !themeMenuExpanded },
                    modifier = Modifier.background(if (isThemePressed) Team2Colors.tortoiseLight else Color.Transparent),
                    interactionSource = themeInteractionSource,
                    trailingIcon = {
                        Icon(
                            modifier = Modifier.size(10.dp),
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = null
                        )
                    }
                )
            }
            DropdownMenu(
                modifier = Modifier.width(200.dp),
                expanded = themeMenuExpanded,
                onDismissRequest = { themeMenuExpanded = false },
                offset = DpOffset(x = (-50).dp, y = (110).dp)
            ) {
                val systemThemeInteractionSource = remember { MutableInteractionSource() }
                val isSystemThemePressed by systemThemeInteractionSource.collectIsPressedAsState()

                val darkThemeInteractionSource = remember { MutableInteractionSource() }
                val isDarkThemePressed by darkThemeInteractionSource.collectIsPressedAsState()

                val lightThemeInteractionSource = remember { MutableInteractionSource() }
                val isLightThemePressed by lightThemeInteractionSource.collectIsPressedAsState()

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
                    },
                    modifier = Modifier.background(if (isSystemThemePressed) Team2Colors.tortoiseLight else Color.Transparent),
                    interactionSource = systemThemeInteractionSource
                )
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.dark_theme)) },
                    onClick = {
                        onThemeChange(ThemeOption.DARK)
                        themeMenuExpanded = false
                        expanded = false
                    },
                    modifier = Modifier.background(if (isDarkThemePressed) Team2Colors.tortoiseLight else Color.Transparent),
                    interactionSource = darkThemeInteractionSource
                )
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.light_theme)) },
                    onClick = {
                        onThemeChange(ThemeOption.LIGHT)
                        themeMenuExpanded = false
                        expanded = false
                    },
                    modifier = Modifier.background(if (isLightThemePressed) Team2Colors.tortoiseLight else Color.Transparent),
                    interactionSource = lightThemeInteractionSource
                )
            }
        }
    }
}


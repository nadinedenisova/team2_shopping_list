package acr.appcradle.shoppinglist.ui.components

import acr.appcradle.shoppinglist.RoutesList
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

@Composable
fun AppDropdownMenu(
    screenRoute: RoutesList,
    onThemeChange: (ThemeOption) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var themeMenuExpanded by remember { mutableStateOf(false) }

    when (screenRoute) {

        RoutesList.ListsAllRoute -> {
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
                        onClick = { /* Do something... */ }
                    )
                    DropdownMenuItem(
                        text = { Text("Установить тему") },
                        onClick = { themeMenuExpanded = true }
                    )
                    DropdownMenu(
                        modifier = Modifier.width(200.dp),
                        expanded = themeMenuExpanded,
                        onDismissRequest = { themeMenuExpanded = false },
                        offset = DpOffset(x = 150.dp, y = (-8).dp)
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

        RoutesList.ListShoppingRoute -> {
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
                        onClick = { /* Do something... */ }
                    )
                    DropdownMenuItem(
                        text = { Text("Поделиться") },
                        onClick = { /* Do something... */ }
                    )
                    DropdownMenuItem(
                        text = { Text("Снять отметки со всех товаров") },
                        onClick = { /* Do something... */ }
                    )
                    DropdownMenuItem(
                        text = { Text("Удалить купленные товары") },
                        onClick = { /* Do something... */ }
                    )
                }
            }
        }

        else -> {}
    }
}


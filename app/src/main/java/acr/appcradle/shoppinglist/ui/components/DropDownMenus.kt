package acr.appcradle.shoppinglist.ui.components

import acr.appcradle.shoppinglist.model.AppIntents
import acr.appcradle.shoppinglist.ui.AppViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

object DropDownMenus {

    @Composable
    fun ShoppingListMenu(
        listId: Long,
        viewModel: AppViewModel = hiltViewModel()
    ) {
        var expanded by remember { mutableStateOf(false) }

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
                    onClick = { viewModel.actionIntent(AppIntents.LoadSortedItems(listId)) }
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

    @Composable
    fun AllListsMenu(
        viewModel: AppViewModel = hiltViewModel()
    ) {
        var expanded by remember { mutableStateOf(false) }

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
                    onClick = { viewModel.actionIntent(AppIntents.LoadSortedLists)}
                )
                DropdownMenuItem(
                    text = { Text("Установить тему") },
                    onClick = { /* Do something... */ }
                )
            }
        }
    }
}
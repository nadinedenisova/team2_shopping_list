package acr.appcradle.shoppinglist.ui.screens.list_creation

import acr.appcradle.shoppinglist.ui.components.AppNavTopBar
import acr.appcradle.shoppinglist.ui.components.appInputField
import acr.appcradle.shoppinglist.ui.theme.ShoppingListTheme
import acr.appcradle.shoppinglist.utils.ThemePreviews
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListCreationScreen(
    onBackClick: () -> Unit
) {
    var inputText by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            AppNavTopBar(
                title = "Создать список",
                onBackIconClick = { onBackClick }
            )
        }
    ) { innerPaddings ->
        Column(modifier = Modifier.padding(innerPaddings)) {
            inputText = appInputField(
                placeholderText = "Введите название списка"
            )
            Text(text = inputText)
        }
    }
}

@ThemePreviews
@Composable
fun GreetingPreview() {
    ShoppingListTheme {
        Surface {
            ListCreationScreen(onBackClick = { })
        }
    }
}
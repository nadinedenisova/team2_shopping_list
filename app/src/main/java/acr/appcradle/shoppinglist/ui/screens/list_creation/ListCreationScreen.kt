package acr.appcradle.shoppinglist.ui.screens.list_creation

import acr.appcradle.shoppinglist.model.IconsIntent
import acr.appcradle.shoppinglist.model.NewListData
import acr.appcradle.shoppinglist.ui.AppViewModel
import acr.appcradle.shoppinglist.ui.components.AppLargeButton
import acr.appcradle.shoppinglist.ui.components.AppNavTopBar
import acr.appcradle.shoppinglist.ui.components.appInputField
import acr.appcradle.shoppinglist.ui.theme.ShoppingListTheme
import acr.appcradle.shoppinglist.utils.ThemePreviews
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ListCreationScreen(
    viewModel: AppViewModel,
    onBackClick: () -> Unit
) {
    val iconState by viewModel.iconState.collectAsStateWithLifecycle()

    ListCreationScreenUi(
        iconsState = iconState,
        onBackClick = onBackClick,
        onIconClick = { viewModel.iconsIntent(IconsIntent.ChangeIcon(it)) },
        onColorClick = { viewModel.iconsIntent(IconsIntent.ChangeColor(it)) }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListCreationScreenUi(
    iconsState: NewListData,
    onBackClick: () -> Unit,
    onIconClick: (Int) -> Unit,
    onColorClick: (Color) -> Unit
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
        Column(
            modifier = Modifier
                .padding(innerPaddings),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            inputText = appInputField(placeholderText = "Введите название списка")
            ColorPalette(
                modifier = Modifier.padding(vertical = 16.dp),
                onColorClick = onColorClick,
                iconsState = iconsState
            )
            IconsPalette(
                iconsState = iconsState,
                onIconClick = onIconClick
            )
            Spacer(Modifier.weight(1f))
            AppLargeButton(onClick = {}, text = "Создать")
        }
    }
}


@ThemePreviews
@Composable
fun GreetingPreview() {
    ShoppingListTheme {
        Surface {
            ListCreationScreen(onBackClick = { }, viewModel = AppViewModel())
        }
    }
}
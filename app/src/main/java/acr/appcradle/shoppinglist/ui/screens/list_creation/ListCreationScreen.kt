package acr.appcradle.shoppinglist.ui.screens.list_creation

import acr.appcradle.shoppinglist.model.IconsIntent
import acr.appcradle.shoppinglist.model.NewListData
import acr.appcradle.shoppinglist.ui.AppViewModel
import acr.appcradle.shoppinglist.ui.components.AppInputFields
import acr.appcradle.shoppinglist.ui.components.AppNavTopBar
import acr.appcradle.shoppinglist.ui.components.ShoppingListButtons
import acr.appcradle.shoppinglist.utils.ThemeOption
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ListCreationScreen(
    viewModel: AppViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onNextClick: () -> Unit,
    onThemeChange: (ThemeOption) -> Unit,
) {
    val iconState by viewModel.iconState.collectAsStateWithLifecycle()

    ListCreationScreenUi(
        iconsState = iconState,
        onBackClick = onBackClick,
        onIconClick = { viewModel.iconsIntent(IconsIntent.ChangeIcon(it)) },
        onColorClick = { viewModel.iconsIntent(IconsIntent.ChangeColor(it)) },
        onNextClick = onNextClick,
        onThemeChange = onThemeChange
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListCreationScreenUi(
    iconsState: NewListData,
    onBackClick: () -> Unit,
    onIconClick: (Int) -> Unit,
    onColorClick: (Color) -> Unit,
    onNextClick: () -> Unit,
    onThemeChange: (ThemeOption) -> Unit

) {
    val scroll = rememberScrollState()
    var inputText by remember { mutableStateOf("") }
    val viewModel: AppViewModel = hiltViewModel()

    Scaffold(
        topBar = {
            AppNavTopBar(
                isBackIconEnable = true,
                title = "Создать список",
                onBackIconClick = { onBackClick() },
            )
        }
    ) { innerPaddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddings)
                .verticalScroll(scroll),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppInputFields.MainInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                placeholderText = "Введите название списка",
                onValueChange = { inputText = it }
            )
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
            ShoppingListButtons.AppLargeButton(
                onClick = {
                    if (inputText.isNotBlank()) {
                        viewModel.createNewList(inputText.trim()) {
                            onNextClick()
                        }
                    }
                },
                text = "Создать"
            )
        }
    }
}


//@ThemePreviews
//@Composable
//fun GreetingPreview() {
//    ShoppingListTheme {
//        Surface {
//            ListCreationScreen(
//                viewModel = AppViewModel(ListRepository()),
//                onBackClick = {},
//                onNextClick = {}
//            )
//        }
//    }
//}
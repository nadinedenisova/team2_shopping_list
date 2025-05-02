package acr.appcradle.shoppinglist.ui.screens.list_creation

import acr.appcradle.shoppinglist.model.IconsIntent
import acr.appcradle.shoppinglist.model.ListElement
import acr.appcradle.shoppinglist.model.NewListData
import acr.appcradle.shoppinglist.ui.AppViewModel
import acr.appcradle.shoppinglist.ui.components.AppInputFields
import acr.appcradle.shoppinglist.ui.components.AppNavTopBar
import acr.appcradle.shoppinglist.ui.components.ShoppingListButtons
import acr.appcradle.shoppinglist.ui.theme.Team2Colors
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    existingList: ListElement?,
) {
    val iconState by viewModel.iconState.collectAsStateWithLifecycle()

    LaunchedEffect(existingList) {
        existingList?.let {
            viewModel.iconsIntent(IconsIntent.ChangeIcon(it.icon))
            viewModel.iconsIntent(IconsIntent.ChangeColor(Color(it.iconBackground.toULong())))
        }
    }

    ListCreationScreenUi(
        iconsState = iconState,
        onBackClick = onBackClick,
        onIconClick = { viewModel.iconsIntent(IconsIntent.ChangeIcon(it)) },
        onColorClick = { viewModel.iconsIntent(IconsIntent.ChangeColor(it)) },
        onNextClick = {
            if (existingList != null) {
                val update = existingList.copy(
                    listName = it,
                    icon = iconState.icon!!,
                    iconBackground = iconState.iconColor!!.value.toLong()
                )
                viewModel.updateList(update) { onNextClick() }
            } else {
                viewModel.createNewList(it) { onNextClick() }
            }
        },
        existingList = existingList,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListCreationScreenUi(
    iconsState: NewListData,
    onBackClick: () -> Unit,
    onIconClick: (Int) -> Unit,
    onColorClick: (Color) -> Unit,
    onNextClick: (String) -> Unit,
    existingList: ListElement?,
    viewModel: AppViewModel = hiltViewModel()
) {
    val scroll = rememberScrollState()
    var inputText by remember(existingList) { mutableStateOf(existingList?.listName ?: "") }
    val isEditing = existingList != null
    val isDuplicate by viewModel.isTitleDuplicate.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AppNavTopBar(
                isBackIconEnable = true,
                title = if (isEditing) "Редактировать список" else "Создать список",
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
                editedValue = inputText,
                isError = isDuplicate,
                onValueChange = {
                    inputText = it
                    viewModel.checkTitleUniqueness(it) },

                )
            if (isDuplicate) {
                Text(
                    text = "Это название уже используется, пожалуйста, измените его.",
                    color = Team2Colors.team2color_red,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(horizontal = 32.dp)
                )
            }
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
                enabled = inputText.isNotBlank() && !isDuplicate,
                onClick = { if (inputText.isNotBlank()) onNextClick(inputText.trim()) },
                text = if (isEditing) "Сохранить" else "Создать"
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
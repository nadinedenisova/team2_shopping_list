package acr.appcradle.shoppinglist.ui.screens.listCreation

import acr.appcradle.shoppinglist.model.ListCreationIntent
import acr.appcradle.shoppinglist.model.ListElement
import acr.appcradle.shoppinglist.model.NewListData
import acr.appcradle.shoppinglist.ui.components.AppInputFields
import acr.appcradle.shoppinglist.ui.components.AppNavTopBar
import acr.appcradle.shoppinglist.ui.components.ShoppingListButtons
import acr.appcradle.shoppinglist.ui.viewmodels.ListCreationViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun ListCreationScreen(
    viewModel: ListCreationViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onNextClick: () -> Unit,
    existingList: ListElement?,
) {
    val scroll = rememberScrollState()
    var inputText by remember(existingList) { mutableStateOf(existingList?.listName ?: "") }
    val isEditing = existingList != null
    val state by viewModel.state.collectAsState()

    LaunchedEffect(existingList) {
        existingList?.let {
            viewModel.handleIntent(ListCreationIntent.SetExistingList(it))
        }
    }

    LaunchedEffect(inputText) {
        if (inputText.isNotEmpty() && !isEditing) {
            viewModel.handleIntent(ListCreationIntent.CheckTitleUniqueness(inputText))
        }
    }

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
            Spacer(modifier = Modifier.padding(16.dp))

            AppInputFields.MainInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                placeholderText = "Введите название списка",
                editedValue = inputText,
                isError = state.isTitleDuplicate,
                onValueChange = {
                    inputText = it
                    viewModel.handleIntent(ListCreationIntent.ChangeTitle(it))
                }
            )

            if (state.isTitleDuplicate) {
                Text(
                    text = "Список с таким названием уже существует",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.padding(16.dp))

            val newListData = NewListData(
                title = inputText,
                icon = state.icon,
                iconColor = state.iconColor
            )

            ColorPalette(
                iconsState = newListData,
                onColorClick = { color ->
                    viewModel.handleIntent(ListCreationIntent.ChangeColor(color))
                }
            )

            Spacer(modifier = Modifier.padding(16.dp))

            IconsPalette(
                iconsState = newListData,
                onIconClick = { icon ->
                    viewModel.handleIntent(ListCreationIntent.ChangeIcon(icon))
                }
            )

            Spacer(modifier = Modifier.padding(32.dp))

            ShoppingListButtons.AppLargeButton(
                text = if (isEditing) "Сохранить" else "Создать",
                onClick = {
                    if (!state.isTitleDuplicate) {
                        if (isEditing && existingList != null) {
                            viewModel.handleIntent(ListCreationIntent.UpdateList(existingList))
                        } else {
                            viewModel.handleIntent(ListCreationIntent.CreateList(inputText))
                        }
                        onNextClick()
                    }
                },
                enabled = inputText.isNotEmpty() && !state.isTitleDuplicate && state.icon != null && state.iconColor != null
            )
        }
    }
}

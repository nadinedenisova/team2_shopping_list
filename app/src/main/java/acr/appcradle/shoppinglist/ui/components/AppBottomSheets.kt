package acr.appcradle.shoppinglist.ui.components

import acr.appcradle.shoppinglist.model.ShoppingElement
import acr.appcradle.shoppinglist.ui.components.AppBottomSheets.Constants.WEIGHT
import acr.appcradle.shoppinglist.ui.theme.Team2Colors
import acr.appcradle.shoppinglist.ui.theme.Typography
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

internal object AppBottomSheets {

    private object Constants {
        const val WEIGHT = 0.5f
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AddItemDialog(
        modifier: Modifier = Modifier,
        listId: Long,
        onDismissCallback: () -> Unit,
        onAddClick: (ShoppingElement) -> Unit = {},
        onEditClick: (ShoppingElement) -> Unit = {},
        editItem: ShoppingElement? = null,
        existingNames: List<String> = emptyList()
    ) {
        val sheetState = rememberModalBottomSheetState()
        var errorText by remember { mutableStateOf<String?>(null) }

        if (editItem == null) {
            var newItem by remember {
                mutableStateOf(
                    ShoppingElement(
                        id = 0L,
                        name = "",
                        amount = "",
                        unit = "шт",
                        checked = false,
                        listId = listId,
                    )
                )
            }
            ModalBottomSheet(
                modifier = modifier
                    .fillMaxWidth()
                    .height(548.dp),
                sheetState = sheetState,
                onDismissRequest = {
                    onDismissCallback()
                }
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(vertical = 18.dp, horizontal = 16.dp)
                                .height(48.dp)
                                .weight(1f),
                            text = "Добавление товара",
                            style = Typography.titleLarge
                        )
                        Box(
                            modifier = Modifier
                                .padding(vertical = 8.dp, horizontal = 16.dp)
                                .size(48.dp)
                                .clickable {
                                    val nameNormalized = newItem.name.trim().lowercase()
                                    if (nameNormalized.isEmpty() || newItem.amount.isEmpty()) return@clickable
                                    if (existingNames.contains(nameNormalized)) {
                                        errorText = "Этот товар уже есть в списке, добавьте другой"
                                    } else {
                                        onAddClick(newItem)
                                        onDismissCallback()
                                    }
                                }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Team2Colors.team2colors_gray
                            )
                        }
                    }
                }
                AppInputFields.MainInputField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    placeholderText = "Введите название товара",
                    onValueChange = {
                        newItem = newItem.copy(name = it)
                        errorText = null
                    }
                )
                if (errorText != null) {
                    Text(
                        text = errorText!!,
                        color = Color.Red,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                ) {
                    AppInputFields.MainInputField(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 6.dp)
                            .weight(WEIGHT),
                        placeholderText = "1",
                        onValueChange = { newItem = newItem.copy(amount = it) },
                        isNumeric = true
                    )
                    AppInputFields.AppDropdownItemChooser(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .weight(WEIGHT),
                        onValueChange = { newItem = newItem.copy(unit = it) }
                    )
                }
            }
        } else {
            var newItem = editItem!!

            ModalBottomSheet(
                modifier = modifier
                    .fillMaxWidth()
                    .height(548.dp),
                sheetState = sheetState,
                onDismissRequest = {
                    onDismissCallback()
                }
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(vertical = 18.dp, horizontal = 16.dp)
                                .height(48.dp)
                                .weight(1f),
                            text = "Добавление товара",
                            style = Typography.titleLarge
                        )
                        Box(
                            modifier = Modifier
                                .padding(vertical = 8.dp, horizontal = 16.dp)
                                .size(48.dp)
                                .clickable {
                                    if (newItem.name.isNotEmpty() && newItem.amount.isNotEmpty()) {
                                        onEditClick(newItem)
                                        onDismissCallback()
                                    }
                                }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Team2Colors.team2colors_gray
                            )
                        }
                    }
                }
                AppInputFields.MainInputField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    editedValue = newItem.name,
                    placeholderText = "Введите название товара",
                    onValueChange = {
                        newItem = newItem.copy(name = it)
                    },
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                ) {
                    AppInputFields.MainInputField(
                        modifier = Modifier
                            .padding(
                                start = 16.dp,
                                end = 6.dp
                            )
                            .weight(WEIGHT),
                        editedValue = newItem.amount,
                        placeholderText = "1",
                        isNumeric = true,
                        onValueChange = {
                            newItem = newItem.copy(amount = it)
                        }
                    )
                    AppInputFields.AppDropdownItemChooser(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .weight(WEIGHT),
                        onValueChange = { newItem = newItem.copy(unit = it) }
                    )
                }
            }
        }
    }
}

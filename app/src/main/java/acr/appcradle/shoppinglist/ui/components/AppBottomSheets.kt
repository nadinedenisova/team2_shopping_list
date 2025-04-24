package acr.appcradle.shoppinglist.ui.components

import acr.appcradle.shoppinglist.model.ShoppingElement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

object AppBottomSheets {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AddItemDialog(
        modifier: Modifier = Modifier,
        listId: Long,
        onDismissCallback: () -> Unit,
        onConfirmClick: (ShoppingElement) -> Unit
    ) {
        val sheetState = rememberModalBottomSheetState()
        var newItem = ShoppingElement(
            id = 0L,
            name = "",
            amount = "",
            unit = "шт",
            checked = false,
            listId = listId,
        )

        ModalBottomSheet(
            modifier = modifier
                .fillMaxWidth()
                .height(548.dp),
            sheetState = sheetState,
            onDismissRequest = { onDismissCallback() }) {
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
                                    onConfirmClick(newItem)
                                    onDismissCallback()
                                }
                            }) {
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
                onValueChange = { newItem = newItem.copy(name = it) })
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                AppInputFields.MainInputField(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 6.dp)
                        .weight(0.5f),
                    placeholderText = "1",
                    onValueChange = { newItem = newItem.copy(amount = it) })
                AppInputFields.MainInputField(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .weight(0.5f),
                    placeholderText = "шт",
                    onValueChange = { newItem = newItem.copy(unit = it) })
            }
        }
    }
}
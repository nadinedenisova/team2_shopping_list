package acr.appcradle.shoppinglist.ui.components

import acr.appcradle.shoppinglist.ui.theme.Team2Colors
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun DeleteDialog(
    visibility: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
    if (visibility) {
        AlertDialog(
            onDismissRequest = {
                onDismissRequest()
            },
            title = {
                Text(
                    text = "Удаление списка"
                )
            },
            text = {
                Text(
                    text = "Вы действительно хотите удалить список?"
                )
            },
            confirmButton = {
                TextButton(onClick = onConfirmation) {
                    Text(
                        text = "Удалить",
                        color = Team2Colors.team2color_red
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = onDismissRequest) {
                    Text("Отменить")
                }
            }
        )
    }
}
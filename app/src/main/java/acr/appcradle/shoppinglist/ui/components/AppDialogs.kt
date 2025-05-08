package acr.appcradle.shoppinglist.ui.components

import acr.appcradle.shoppinglist.R
import acr.appcradle.shoppinglist.ui.theme.Team2Colors
import acr.appcradle.shoppinglist.ui.theme.Typography
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

internal object AppDialogs {

    @Composable
    fun MainAppDialog(
        dialogTitle: String,
        dialogText: String,
        onDismissRequest: () -> Unit,
        onConfirmation: () -> Unit,
    ) {
        AlertDialog(
            title = {
                Text(
                    text = dialogTitle,
                    style = Typography.titleLarge,
                )
            },
            text = {
                Text(
                    text = dialogText,
                    style = Typography.bodyMedium,
                )
            },
            onDismissRequest = { onDismissRequest() },
            confirmButton = {
                TextButton(
                    onClick = { onConfirmation() }
                ) {
                    Text(
                        text = stringResource(R.string.delete),
                        style = Typography.labelLarge,
                        color = Team2Colors.team2color_red
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { onDismissRequest() }
                ) {
                    Text(
                        text = stringResource(R.string.cancel),
                        style = Typography.labelLarge
                    )
                }
            }
        )
    }
}

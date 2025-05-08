package acr.appcradle.shoppinglist.ui.components

import acr.appcradle.shoppinglist.R
import acr.appcradle.shoppinglist.ui.theme.Team2Colors
import acr.appcradle.shoppinglist.ui.theme.Typography
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
internal fun DeleteDialog(
    visibility: Boolean,
    title: String,
    message: String,
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
                    text = title,
                    style = Typography.titleLarge
                )
            },
            text = {
                Text(
                    text = message,
                    style = Typography.bodyMedium
                )
            },
            confirmButton = {
                TextButton(onClick = onConfirmation) {
                    Text(
                        text = stringResource(R.string.delete),
                        style = Typography.labelLarge,
                        color = Team2Colors.team2color_red
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = onDismissRequest) {
                    Text(
                        text = stringResource(R.string.cancel),
                        style = Typography.labelLarge
                    )
                }
            }
        )
    }
}

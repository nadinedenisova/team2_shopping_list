package acr.appcradle.shoppinglist.ui.components

import acr.appcradle.shoppinglist.ui.theme.Team2Colors
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object ShoppingListButtons {

    @Composable
    fun AppLargeButton(
        modifier: Modifier = Modifier,
        onClick: () -> Unit,
        text: String,
        enabled: Boolean = true
    ) {
        FilledTonalButton(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Team2Colors.tortoise,
                contentColor = Color.White
            ),
            onClick = { onClick() },
            enabled = enabled
        ) {
            Text(text)
        }
    }
}

package acr.appcradle.shoppinglist.ui.screens.list_shopping

import acr.appcradle.shoppinglist.model.ShoppingElement
import acr.appcradle.shoppinglist.ui.theme.ShoppingListTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ShoppingListItem(items: ShoppingElement, onCheckedChange: () -> Unit) {
    Row {
        Checkbox(
            checked = items.checked,
            onCheckedChange = { onCheckedChange() },
            modifier = Modifier.padding(end = 16.dp)
        )
        Text(
            text = items.name,
        )
        Text(
            text = "${items.amount} ${items.unit}"
        )
    }
}

@Preview
@Composable
private fun ShoppingListItemPreview() {
    ShoppingListTheme {
        Surface {
        }
    }
}
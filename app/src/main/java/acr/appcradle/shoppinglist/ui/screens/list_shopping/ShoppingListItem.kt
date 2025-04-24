package acr.appcradle.shoppinglist.ui.screens.list_shopping

import acr.appcradle.shoppinglist.model.ShoppingElement
import acr.appcradle.shoppinglist.ui.theme.ShoppingListTheme
import acr.appcradle.shoppinglist.utils.ThemePreviews
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShoppingListItem(
    items: ShoppingElement,
    onCheckedChange: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 3.5.dp)
                .size(48.dp),
            contentAlignment = Alignment.Center
        ) {
            Checkbox(
                checked = items.checked,
                onCheckedChange = { onCheckedChange() },
            )
        }
        Text(
            modifier = Modifier.weight(1f),
            text = items.name,
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            text = "${items.amount} ${items.unit}"
        )
    }
}

@ThemePreviews
@Composable
private fun ShoppingListItemPreview() {
    ShoppingListTheme {
        Surface {
            ShoppingListItem(
                items = ShoppingElement(
                    listId = 2,
                    name = "Груша",
                    amount = "3",
                    unit = "шт",
                    checked = false
                )
            ) { }
        }
    }
}
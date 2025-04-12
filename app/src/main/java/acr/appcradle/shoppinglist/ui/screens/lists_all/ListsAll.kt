package acr.appcradle.shoppinglist.ui.screens.lists_all

import acr.appcradle.shoppinglist.ui.theme.ShoppingListTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun ListsAll(modifier: Modifier = Modifier) {
    Text("2")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ShoppingListTheme {
        ListsAll()
    }
}
package acr.appcradle.shoppinglist.ui.screens.greeting

import acr.appcradle.shoppinglist.ui.theme.ShoppingListTheme
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun Greeting(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Text("1")
    Button(onClick = { onClick() }) { }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ShoppingListTheme {
        Greeting(onClick = {  })
    }
}
package acr.appcradle.shoppinglist.ui.screens.lists_all

import acr.appcradle.shoppinglist.ui.AppViewModel
import acr.appcradle.shoppinglist.ui.theme.ShoppingListTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun ListsAll(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel
) {
    val state by viewModel.state.collectAsState()

    Text("2")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ShoppingListTheme {
        ListsAll(
            viewModel = AppViewModel()
        )
    }
}
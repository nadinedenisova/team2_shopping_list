package acr.appcradle.shoppinglist.ui.screens.list_shopping

import acr.appcradle.shoppinglist.model.ShoppingElement
import acr.appcradle.shoppinglist.ui.theme.ShoppingListTheme
import acr.appcradle.shoppinglist.utils.ThemePreviews
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FilledListUi(
    modifier: Modifier = Modifier,
    listOfItems: List<ShoppingElement>
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        listOfItems.forEach { item ->
            ShoppingListItem(item) { }
        }
    }
}


@ThemePreviews
@Composable
private fun Preview() {
    ShoppingListTheme {
        Surface {
            FilledListUi(
                listOfItems = listOf(
                    ShoppingElement(
                        name = "Яблоко",
                        amount = "31",
                        unit = "кг",
                        checked = false
                    ),
                    ShoppingElement(
                        name = "Груша",
                        amount = "3",
                        unit = "шт",
                        checked = true
                    ),
                    ShoppingElement(
                        name = "Апельсины",
                        amount = "3",
                        unit = "шт",
                        checked = false
                    ),
                )
            )
        }
    }
}
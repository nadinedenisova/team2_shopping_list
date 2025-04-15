package acr.appcradle.shoppinglist.ui.screens.list_shopping

import acr.appcradle.shoppinglist.R
import acr.appcradle.shoppinglist.data.Repository
import acr.appcradle.shoppinglist.ui.AppViewModel
import acr.appcradle.shoppinglist.ui.components.AppLargeButton
import acr.appcradle.shoppinglist.ui.theme.ShoppingListTheme
import acr.appcradle.shoppinglist.ui.theme.Typography
import acr.appcradle.shoppinglist.utils.ThemePreviews
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun EmptyListUi(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(64.dp))

        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(R.drawable.empty_shopping_image),
            contentDescription = null
        )

        Text(
            modifier = Modifier.padding(top = 52.dp),
            text = "Давайте спланируем покупки!",
            style = Typography.headlineSmall.copy(textAlign = TextAlign.Center)
        )

        Text(
            modifier = Modifier.padding(top = 12.dp),
            text = "Начните добавлять товары",
            style = Typography.bodyLarge.copy(textAlign = TextAlign.Center)

        )

        Spacer(Modifier.weight(1f))

        AppLargeButton(
            text = "Добавить товар",
            onClick = {}
        )
    }
}


@ThemePreviews
@Composable
private fun ListShoppingPreview() {
    ShoppingListTheme {
        Surface {
            EmptyListUi()
        }
    }
}
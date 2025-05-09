package acr.appcradle.shoppinglist.ui.screens.greeting

import acr.appcradle.shoppinglist.R
import acr.appcradle.shoppinglist.ui.components.ShoppingListButtons
import acr.appcradle.shoppinglist.ui.theme.ShoppingListTheme
import acr.appcradle.shoppinglist.ui.theme.Typography
import acr.appcradle.shoppinglist.utils.ThemePreviews
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun GreetingScreen(onNextClick: () -> Unit) {
    val scroll = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scroll),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(vertical = 40.dp),
            text = stringResource(R.string.greeting),
            style = Typography.headlineLarge.copy(textAlign = TextAlign.Center)
        )
        Image(
            painter = painterResource(R.drawable.greeting_image),
            contentDescription = null
        )
        Text(
            modifier = Modifier
                .padding(top = 28.dp)
                .padding(horizontal = 16.dp),
            text = stringResource(R.string.under_greets_advise),
            style = Typography.headlineSmall.copy(textAlign = TextAlign.Center)
        )
        Text(
            modifier = Modifier
                .padding(top = 12.dp)
                .padding(horizontal = 16.dp),
            text = stringResource(R.string.under_greets_advise_2),
            style = Typography.bodyLarge.copy(textAlign = TextAlign.Center)
        )
        Spacer(Modifier.weight(1f))
        ShoppingListButtons.AppLargeButton(
            text = stringResource(R.string.greeting_button_text),
            onClick = { onNextClick() }
        )
    }
}

@ThemePreviews
@Composable
fun GreetingPreview() {
    ShoppingListTheme {
        Surface {
            GreetingScreen(onNextClick = { })
        }
    }
}
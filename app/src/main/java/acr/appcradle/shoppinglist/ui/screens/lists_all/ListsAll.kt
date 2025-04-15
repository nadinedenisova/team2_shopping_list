package acr.appcradle.shoppinglist.ui.screens.lists_all

import acr.appcradle.shoppinglist.R
import acr.appcradle.shoppinglist.data.Repository
import acr.appcradle.shoppinglist.ui.AppViewModel
import acr.appcradle.shoppinglist.ui.components.AppLargeButton
import acr.appcradle.shoppinglist.ui.components.AppNavTopBar
import acr.appcradle.shoppinglist.ui.theme.ShoppingListTheme
import acr.appcradle.shoppinglist.ui.theme.Typography
import acr.appcradle.shoppinglist.utils.ThemePreviews
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun ListsAll(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            AppNavTopBar(
                title = "Мои списки",
                onMenuIconClick = {},
                isMenuIconEnabled = true,
                onBackIconClick = {}
            )
        }
    ) { innerPaddings ->
        Column(
            modifier = Modifier.padding(innerPaddings),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(
                modifier.height(104.dp)
            )

            Image(
                painterResource(R.drawable.empty_list_image),
                contentDescription = null
            )

            Spacer(
                modifier.height(52.dp)
            )

            Text(
                text = "Давайте спланируем покупки!",
                style = Typography.titleLarge.copy(textAlign = TextAlign.Center),
            )

            Text(
                modifier = modifier.padding(top = 12.dp),
                text = "Создайте свой первый список",
                style = Typography.bodyLarge.copy(textAlign = TextAlign.Center)
            )

            Spacer(modifier.weight(1f))

            AppLargeButton(
                text = "Создать список",
                onClick = {}
            )
        }
    }

}

@ThemePreviews
@Composable
fun GreetingPreview() {
    ShoppingListTheme {
        Surface {
            ListsAll(
                viewModel = AppViewModel(Repository())
            )
        }
    }
}
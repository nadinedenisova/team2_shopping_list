package acr.appcradle.shoppinglist.ui.screens.lists_all

import acr.appcradle.shoppinglist.R
import acr.appcradle.shoppinglist.ui.components.AppLargeButton
import acr.appcradle.shoppinglist.ui.theme.Typography
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun EmptyListAllUi(onNextClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(104.dp))

        Image(
            painterResource(R.drawable.empty_list_image),
            contentDescription = null
        )

        Spacer(Modifier.height(52.dp))

        Text(
            text = "Давайте спланируем покупки!",
            style = Typography.titleLarge.copy(textAlign = TextAlign.Center),
        )

        Text(
            modifier = Modifier.padding(top = 12.dp),
            text = "Создайте свой первый список",
            style = Typography.bodyLarge.copy(textAlign = TextAlign.Center)
        )

        Spacer(Modifier.weight(1f))

        AppLargeButton(
            text = "Создать список",
            onClick = {
                onNextClick()
            }
        )
    }
}

@Preview
@Composable
private fun EmptyListPreview() {
    EmptyListAllUi() {}
}
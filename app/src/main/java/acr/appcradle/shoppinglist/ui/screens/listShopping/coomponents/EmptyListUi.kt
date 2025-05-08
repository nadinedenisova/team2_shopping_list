package acr.appcradle.shoppinglist.ui.screens.listShopping.coomponents

import acr.appcradle.shoppinglist.R
import acr.appcradle.shoppinglist.model.ShoppingListIntent
import acr.appcradle.shoppinglist.ui.components.AppBottomSheets
import acr.appcradle.shoppinglist.ui.components.ShoppingListButtons
import acr.appcradle.shoppinglist.ui.theme.Typography
import acr.appcradle.shoppinglist.ui.screens.listShopping.ShoppingListViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
internal fun EmptyListUi(
    listId: Long,
    modifier: Modifier = Modifier,
    viewModel: ShoppingListViewModel,
) {
    var addItemBottomSheetVisibility by rememberSaveable { mutableStateOf(false) }

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
            text = stringResource(R.string.plan_our_purchases),
            style = Typography.headlineSmall.copy(textAlign = TextAlign.Center)
        )

        Text(
            modifier = Modifier.padding(top = 12.dp),
            text = stringResource(R.string.start_adding_products),
            style = Typography.bodyLarge.copy(textAlign = TextAlign.Center)
        )

        Spacer(Modifier.weight(1f))

        ShoppingListButtons.AppLargeButton(
            text = stringResource(R.string.add_product),
            onClick = { addItemBottomSheetVisibility = true }
        )
        if (addItemBottomSheetVisibility) {
            AppBottomSheets.AddItemDialog(
                onDismissCallback = { addItemBottomSheetVisibility = false },
                onAddClick = { viewModel.handleIntent(ShoppingListIntent.AddItem(it)) },
                listId = listId
            )
        }
    }
}

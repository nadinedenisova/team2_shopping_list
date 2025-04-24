package acr.appcradle.shoppinglist.ui.screens.list_creation

import acr.appcradle.shoppinglist.R
import acr.appcradle.shoppinglist.model.NewListData
import acr.appcradle.shoppinglist.ui.theme.ShoppingListTheme
import acr.appcradle.shoppinglist.ui.theme.Team2Colors
import acr.appcradle.shoppinglist.ui.theme.Typography
import acr.appcradle.shoppinglist.utils.ThemePreviews
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun IconsPalette(
    iconsState: NewListData,
    modifier: Modifier = Modifier,
    onIconClick: (Int) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(296.dp)
            .padding(horizontal = 16.dp),
    ) {
        Column {
            Text(
                modifier = Modifier
                    .padding(top = 12.dp, start = 16.dp),
                text = "Выберите иконку",
                style = Typography.bodyLarge
            )
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly, // Отступы между столбцами
                verticalArrangement = Arrangement.spacedBy(12.dp), // Отступы между строками
                columns = GridCells.Fixed(5),
                content = {
                    items(listOfIcons.size) { index ->
                        Box(
                            modifier = Modifier
                                .requiredSize(48.dp)
                                .aspectRatio(1f)
                                .clickable { onIconClick(listOfIcons[index]) }
                                .background(
                                    color = if (listOfIcons[index] == iconsState.icon) iconsState.iconColor
                                        ?: Team2Colors.iconsBg else Team2Colors.iconsBg,
                                    shape = RoundedCornerShape(24.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                modifier = Modifier.size(22.5.dp),
                                painter = painterResource(listOfIcons[index]),
                                contentDescription = "Иконка ${index + 1}",
                                tint = Color.White
                            )
                        }
                    }
                }
            )
        }
    }
}

@ThemePreviews
@Composable
private fun Preview2() {
    ShoppingListTheme {
        Surface {
            IconsPalette(
                iconsState = NewListData(
                    title = null,
                    icon = R.drawable.airplane_outline,
                    iconColor = Team2Colors.tortoise
                )
            ) {}
        }
    }
}
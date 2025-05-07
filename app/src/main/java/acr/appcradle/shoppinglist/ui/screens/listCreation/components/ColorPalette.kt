package acr.appcradle.shoppinglist.ui.screens.listCreation.components

import acr.appcradle.shoppinglist.model.NewListData
import acr.appcradle.shoppinglist.ui.theme.Team2Colors
import acr.appcradle.shoppinglist.ui.theme.Typography
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
internal fun ColorPalette(
    iconsState: NewListData,
    modifier: Modifier = Modifier,
    onColorClick: (Color) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(112.dp)
            .padding(horizontal = 16.dp),
    ) {
        Column {
            Text(
                modifier = Modifier.padding(top = 12.dp, start = 16.dp, bottom = 12.dp),
                text = "Выберите дизайн",
                style = Typography.bodyLarge
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp, top = 4.dp)
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                listOfColors.forEach {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clickable { onColorClick(it) }
                            .background(
                                color = it,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .drawBehind {
                                if (it == iconsState.iconColor) {
                                    val strokeWidth = 2.dp.toPx()
                                    val circleRadius = size.minDimension / 2 + strokeWidth * 2

                                    drawCircle(
                                        color = Team2Colors.tortoise,
                                        radius = circleRadius,
                                        center = center,
                                        style = Stroke(width = strokeWidth)
                                    )
                                }
                            },
                    )
                }
            }
        }
    }
}

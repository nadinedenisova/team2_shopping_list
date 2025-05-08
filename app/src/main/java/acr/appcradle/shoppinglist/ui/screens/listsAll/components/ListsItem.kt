package acr.appcradle.shoppinglist.ui.screens.listsAll.components

import acr.appcradle.shoppinglist.ui.theme.RubikFontFamily
import acr.appcradle.shoppinglist.ui.theme.Typography
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun ListsItem(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    title: String,
    iconBackground: Color,
    boughtCount: Int,
    totalCount: Int,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp)
            .background(
                color = colorScheme.surfaceContainer,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(start = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(iconBackground, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(20.dp)
            )
        }

        Text(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f),
            text = title,
            style = Typography.bodyLarge
        )

        Text(
            modifier = Modifier.padding(end = 16.dp),
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    fontFamily = RubikFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp)) {
                    append("$boughtCount")
                }
                append("/")
                withStyle(style = SpanStyle(
                    fontFamily = RubikFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp)) {
                    append("$totalCount")
                }
            }
        )
    }
}

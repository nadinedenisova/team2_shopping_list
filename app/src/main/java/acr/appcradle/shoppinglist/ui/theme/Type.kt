package acr.appcradle.shoppinglist.ui.theme

import acr.appcradle.shoppinglist.R
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val RubikFontFamily = FontFamily(
    Font(R.font.rubik_variablefont_wght, FontWeight.W400, FontStyle.Italic),
    Font(R.font.rubik_variablefont_wght, FontWeight.W500, FontStyle.Normal),
)

val Typography = CustomTypography(
    header = TextStyle(
        fontFamily = RubikFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 30.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    body = TextStyle(
        fontFamily = RubikFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
)

data class CustomTypography(
    val header: TextStyle,
    val body: TextStyle,
)
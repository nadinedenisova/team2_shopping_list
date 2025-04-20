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

data class CustomTypography(
    val headlineLarge: TextStyle,
    val headlineSmall: TextStyle,
    val titleLarge: TextStyle,
    val bodyLarge: TextStyle,
    val bodyMedium: TextStyle,
)

val Typography = CustomTypography(
    headlineLarge = TextStyle(
        fontFamily = RubikFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 40.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = RubikFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = RubikFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = RubikFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = RubikFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
)


package acr.appcradle.shoppinglist.model

import androidx.compose.ui.graphics.Color

internal sealed interface IconsIntent {
    data class ChangeIcon(val icon: Int) : IconsIntent
    data class ChangeColor(val color: Color) : IconsIntent
    data class ChangeTitle(val title: String) : IconsIntent
}

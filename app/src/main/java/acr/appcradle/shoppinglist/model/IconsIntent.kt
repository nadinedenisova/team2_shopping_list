package acr.appcradle.shoppinglist.model

import androidx.compose.ui.graphics.Color

sealed interface IconsIntent {
    data class Update(
        val icon: Int? = null,
        val color: Color? = null,
        val title: String? = null
    ) : IconsIntent
}
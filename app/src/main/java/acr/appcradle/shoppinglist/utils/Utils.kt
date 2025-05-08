package acr.appcradle.shoppinglist.utils

import acr.appcradle.shoppinglist.model.ShoppingElement
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light theme")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark theme")
internal annotation class ThemePreviews

fun shareList(name: String, list: List<ShoppingElement>, context: Context) {
    val text = buildString {
        appendLine("Название списка: $name")
        appendLine()
        appendLine("Список товаров:")
        list.forEach {
            appendLine("- ${it.name}")
        }
    }

    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, text)
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }

    val shareIntent = Intent.createChooser(sendIntent, "Поделиться списком")
    context.startActivity(shareIntent)
}
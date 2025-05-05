package acr.appcradle.shoppinglist.utils

import acr.appcradle.shoppinglist.model.AppIntents
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light theme")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark theme")
internal annotation class ThemePreviews


internal fun sendList(intent: AppIntents.ShareList) {
    val text = buildString {
        appendLine("Название списка: ${intent.name}")
        appendLine()
        appendLine("Список товаров:")
        intent.list.forEach {
            appendLine("- ${it.name}")
        }
    }

    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, text)
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }

    val shareIntent = Intent.createChooser(sendIntent, "Поделиться списком")
    intent.context.startActivity(shareIntent)
}
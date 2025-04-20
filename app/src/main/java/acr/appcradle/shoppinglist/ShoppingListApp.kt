package acr.appcradle.shoppinglist

import acr.appcradle.shoppinglist.di.DataModuleEntryPoint
import acr.appcradle.shoppinglist.ui.theme.ShoppingListTheme
import acr.appcradle.shoppinglist.utils.ThemeOption
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import dagger.hilt.android.EntryPointAccessors

@Composable
fun ShoppingListApp() {
    val context = LocalContext.current
    val entryPoint = EntryPointAccessors.fromApplication(
        context = context,
        entryPoint = DataModuleEntryPoint::class.java
    )
    val themeRemember = entryPoint.themeRemember()
    var theme by remember { mutableStateOf(themeRemember.getTheme()) }
    themeRemember.saveTheme(theme)
    val isDarkTheme = when (theme) {
        ThemeOption.SYSTEM -> isSystemInDarkTheme()
        ThemeOption.DARK -> true
        ThemeOption.LIGHT -> false
    }

    ShoppingListTheme(
        darkTheme = isDarkTheme
    ) {
        Surface {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                AppNavHost(
                    scaffoldPaddings = innerPadding,
                    onThemeChange = { theme = it }
                )
            }
        }
    }
}


package acr.appcradle.shoppinglist

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.github.aakira.napier.Napier
import io.github.aakira.napier.LogLevel

@HiltAndroidApp
class ShoppingListApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Napier.base(DebugAntilog())
    }
}

internal class DebugAntilog : io.github.aakira.napier.Antilog() {
    override fun performLog(priority: LogLevel, tag: String?, throwable: Throwable?, message: String?) {
        when (priority) {
            LogLevel.VERBOSE -> android.util.Log.v(tag, message, throwable)
            LogLevel.DEBUG -> android.util.Log.d(tag, message, throwable)
            LogLevel.INFO -> android.util.Log.i(tag, message, throwable)
            LogLevel.WARNING -> android.util.Log.w(tag, message, throwable)
            LogLevel.ERROR -> android.util.Log.e(tag, message, throwable)
            LogLevel.ASSERT -> android.util.Log.wtf(tag, message, throwable)
        }
    }
}

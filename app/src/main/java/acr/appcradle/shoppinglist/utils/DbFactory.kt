package acr.appcradle.shoppinglist.utils

import acr.appcradle.shoppinglist.ShoppingDatabase
import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DbFactory @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun createDatabase(): ShoppingDatabase {
        val driver = AndroidSqliteDriver(
            schema = ShoppingDatabase.Schema,
            context = context,
            name = "shopping.db"
        )
        return ShoppingDatabase(driver)
    }
}
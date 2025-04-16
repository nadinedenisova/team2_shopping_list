package acr.appcradle.shoppinglist.utils

import acr.appcradle.shoppinglist.ShoppingDatabase
import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

class DbFactory(private val context: Context) {

    fun createDatabase(): ShoppingDatabase {
        val driver = AndroidSqliteDriver(
            schema = ShoppingDatabase.Schema,
            context = context,
            name = "shopping.db"
        )
        return ShoppingDatabase(driver)
    }


}
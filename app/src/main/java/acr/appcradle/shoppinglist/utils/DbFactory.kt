package acr.appcradle.shoppinglist.utils

import acr.appcradle.shoppinglist.ShoppingDatabase
import acr.appcradle.shoppinglist.ShoppingItems
import android.content.Context
import app.cash.sqldelight.ColumnAdapter
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
        return ShoppingDatabase(
            driver = driver,
        )
    }
}

object DbAdapters {
    val booleanToLong = object : ColumnAdapter<Boolean, Long> {
        override fun decode(databaseValue: Long): Boolean = databaseValue != 0L
        override fun encode(value: Boolean): Long = if (value) 1L else 0L
    }
}
package acr.appcradle.shoppinglist.di

import acr.appcradle.shoppinglist.ShoppingDatabase
import acr.appcradle.shoppinglist.ShoppingItemsQueries
import acr.appcradle.shoppinglist.ShoppingListQueries
import acr.appcradle.shoppinglist.utils.DbFactory
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModules {

    @Provides
    @Singleton
    fun provideDbFactory(
        @ApplicationContext context: Context
    ): DbFactory = DbFactory(context)

    @Provides
    @Singleton
    fun provideShoppingDatabase(
        dbFactory: DbFactory
    ): ShoppingDatabase = dbFactory.createDatabase()

    @Provides
    fun provideShoppingQueries(
        database: ShoppingDatabase
    ): ShoppingListQueries = database.shoppingListQueries

    @Provides
    fun provideShoppingItemsQueries(
        database: ShoppingDatabase
    ): ShoppingItemsQueries = database.shoppingItemsQueries
}

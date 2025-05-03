package acr.appcradle.shoppinglist.di

import acr.appcradle.shoppinglist.data.db.SqlDelightLocalDataSource
import acr.appcradle.shoppinglist.data.listAll.ListRepositoryImpl
import acr.appcradle.shoppinglist.data.listShopping.ItemsRepositoryImpl
import acr.appcradle.shoppinglist.model.ItemsRepository
import acr.appcradle.shoppinglist.model.ListRepository
import acr.appcradle.shoppinglist.model.ShoppingLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {
    @Binds
    fun bindListRepository(impl: ListRepositoryImpl): ListRepository

    @Binds
    fun bindItemsRepository(impl: ItemsRepositoryImpl): ItemsRepository

    @Binds
    fun bindShoppingLocalDataSource(impl: SqlDelightLocalDataSource): ShoppingLocalDataSource
}

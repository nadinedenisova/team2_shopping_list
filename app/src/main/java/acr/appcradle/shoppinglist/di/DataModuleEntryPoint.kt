package acr.appcradle.shoppinglist.di

import acr.appcradle.shoppinglist.data.repository.AuthStateManager
import acr.appcradle.shoppinglist.utils.ThemeRemember
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
internal interface DataModuleEntryPoint {
    fun themeRemember(): ThemeRemember
    fun authStateManager(): AuthStateManager
}

package acr.appcradle.shoppinglist.di

import acr.appcradle.shoppinglist.utils.ThemeRemember
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface DataModuleEntryPoint {
    fun themeRemember(): ThemeRemember
}
package acr.appcradle.shoppinglist.di

import acr.appcradle.shoppinglist.utils.ThemeRemember
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SavedDataModule {

    @Provides
    @Singleton
    fun providePrefs(
        @ApplicationContext context: Context
    ): ThemeRemember = ThemeRemember(context)

}



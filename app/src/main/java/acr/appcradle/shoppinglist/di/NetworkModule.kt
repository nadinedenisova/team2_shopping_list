package acr.appcradle.shoppinglist.di

import acr.appcradle.shoppinglist.data.local.TokenStorage
import acr.appcradle.shoppinglist.data.remote.AuthApi
import acr.appcradle.shoppinglist.data.repository.AuthRepository
import acr.appcradle.shoppinglist.data.repository.AuthStateManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import io.github.aakira.napier.Napier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
            install(Logging) {
                level = LogLevel.ALL
                logger = object : io.ktor.client.plugins.logging.Logger {
                    override fun log(message: String) {
                        Napier.d(message = message, tag = "Ktor")
                    }
                }
            }
        }
    }

    @Provides
    @Singleton
    fun provideAuthApi(client: HttpClient): AuthApi {
        return AuthApi(client)
    }

    @Provides
    @Singleton
    fun provideTokenStorage(@ApplicationContext context: android.content.Context): TokenStorage {
        return TokenStorage(context)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthApi, tokenStorage: TokenStorage): AuthRepository {
        return AuthRepository(api, tokenStorage)
    }

    @Provides
    @Singleton
    fun provideAuthStateManager(repository: AuthRepository): AuthStateManager {
        return AuthStateManager(repository)
    }
} 
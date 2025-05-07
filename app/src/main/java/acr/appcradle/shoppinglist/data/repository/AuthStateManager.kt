package acr.appcradle.shoppinglist.data.repository

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthStateManager @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun checkAndRefreshToken(): Boolean {
        return try {
            Log.e("auth", "token auth valid")
            authRepository.getValidAccessToken().isSuccess
        } catch (e: Exception) {
            Log.e("auth", "${e.message}")
            false
        }
    }
} 
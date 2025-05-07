package acr.appcradle.shoppinglist.data.repository

import acr.appcradle.shoppinglist.data.local.TokenStorage
import acr.appcradle.shoppinglist.data.model.AuthRequest
import acr.appcradle.shoppinglist.data.model.AuthResponse
import acr.appcradle.shoppinglist.data.model.RefreshTokenRequest
import acr.appcradle.shoppinglist.data.remote.AuthApi
import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val authApi: AuthApi,
    private val tokenStorage: TokenStorage
) {
    suspend fun register(email: String, password: String): Result<AuthResponse> {
        return try {
            val response = authApi.register(AuthRequest(email, password))
            saveTokens(response)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(email: String, password: String): Result<AuthResponse> {
        return try {
            val response = authApi.login(AuthRequest(email, password))
            saveTokens(response)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun refreshToken(refreshToken: String): Result<AuthResponse> {
        return try {
            val response = authApi.refreshToken(RefreshTokenRequest(refreshToken))
            saveTokens(response)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun checkToken(token: String): Result<Boolean> {
        return try {
            val response = authApi.checkToken(token)
            Result.success(response.is_valid)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getValidAccessToken(): Result<String> {
        val accessToken = tokenStorage.getAccessToken() ?: return Result.failure(Exception("No access token"))
        Log.d("auth", "token check")
        return try {
            val isValid = checkToken(accessToken).getOrNull() == true
            
            if (isValid) {
                Log.d("auth", "token valid")
                Result.success(accessToken)
            } else {
                val refreshToken = tokenStorage.getRefreshToken() ?: return Result.failure(Exception("No refresh token"))
                val refreshResult = refreshToken(refreshToken).getOrNull()
                
                if (refreshResult != null) {
                    Log.d("auth", "token refreshed")
                    Result.success(refreshResult.access_token)
                } else {
                    Log.d("auth", "token check failed")
                    Result.failure(Exception("Token refresh failed"))
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun saveTokens(response: AuthResponse) {
        tokenStorage.saveTokens(response.access_token, response.refresh_token)
    }
} 
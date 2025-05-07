package acr.appcradle.shoppinglist.data.repository

import acr.appcradle.shoppinglist.data.model.AuthRequest
import acr.appcradle.shoppinglist.data.model.AuthResponse
import acr.appcradle.shoppinglist.data.model.RefreshTokenRequest
import acr.appcradle.shoppinglist.data.remote.AuthApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val authApi: AuthApi
) {
    private var currentAccessToken: String? = null
    private var currentRefreshToken: String? = null

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
        val accessToken = currentAccessToken ?: return Result.failure(Exception("No access token"))
        
        return try {
            val isValid = checkToken(accessToken).getOrNull() == true
            
            if (isValid) {
                Result.success(accessToken)
            } else {
                val refreshToken = currentRefreshToken ?: return Result.failure(Exception("No refresh token"))
                val refreshResult = refreshToken(refreshToken).getOrNull()
                
                if (refreshResult != null) {
                    Result.success(refreshResult.access_token)
                } else {
                    Result.failure(Exception("Token refresh failed"))
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun saveTokens(response: AuthResponse) {
        currentAccessToken = response.access_token
        currentRefreshToken = response.refresh_token
    }

    fun clearTokens() {
        currentAccessToken = null
        currentRefreshToken = null
    }
} 
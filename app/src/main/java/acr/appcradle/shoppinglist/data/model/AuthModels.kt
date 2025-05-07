package acr.appcradle.shoppinglist.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest(
    val email: String,
    val password: String
)

@Serializable
data class RefreshTokenRequest(
    val refresh_token: String
)

@Serializable
data class AuthResponse(
    val access_token: String,
    val refresh_token: String,
    val user_id: Long
)

@Serializable
data class TokenValidationResponse(
    val is_valid: Boolean
) 
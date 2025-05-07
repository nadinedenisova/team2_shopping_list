package acr.appcradle.shoppinglist.data.remote

import acr.appcradle.shoppinglist.data.model.AuthRequest
import acr.appcradle.shoppinglist.data.model.AuthResponse
import acr.appcradle.shoppinglist.data.model.RefreshTokenRequest
import acr.appcradle.shoppinglist.data.model.TokenValidationResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

class AuthApi @Inject constructor(
    private val client: HttpClient
) {
    private val baseUrl = "http://130.193.44.66:8080"

    suspend fun register(request: AuthRequest): AuthResponse {
        return client.post("$baseUrl/auth/registration") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    suspend fun login(request: AuthRequest): AuthResponse {
        return client.post("$baseUrl/auth/login") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    suspend fun refreshToken(request: RefreshTokenRequest): AuthResponse {
        return client.post("$baseUrl/auth/refresh") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    suspend fun checkToken(token: String): TokenValidationResponse {
        return client.get("$baseUrl/auth/check") {
            header("Authorization", "Bearer $token")
        }.body()
    }
} 
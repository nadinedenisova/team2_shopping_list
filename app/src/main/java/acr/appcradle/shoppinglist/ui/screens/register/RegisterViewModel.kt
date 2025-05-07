package acr.appcradle.shoppinglist.ui.screens.register

import acr.appcradle.shoppinglist.data.model.AuthResponse
import acr.appcradle.shoppinglist.data.repository.AuthRepository
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<RegisterUiState>(RegisterUiState.Initial)
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun register(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = RegisterUiState.Loading
            authRepository.register(email, password)
                .onSuccess { response ->
                    _uiState.value = RegisterUiState.Success(response)
                }
                .onFailure { error ->
                    _uiState.value = RegisterUiState.Error(error.message ?: "Ошибка регистрации")
                    Log.e("http", "${error.message}")
                }
        }
    }
}

sealed class RegisterUiState {
    object Initial : RegisterUiState()
    object Loading : RegisterUiState()
    data class Success(val response: AuthResponse) :
        RegisterUiState()

    data class Error(val message: String) : RegisterUiState()
} 
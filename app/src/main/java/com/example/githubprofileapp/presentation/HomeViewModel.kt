package com.example.githubprofileapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubprofileapp.data.model.GithubRepositoryModel
import com.example.githubprofileapp.domain.usecase.GetUserRepositoriesUsecase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class UiState {
    data object Initial : UiState()
    data object Loading : UiState()
    data class Success(val repositories: List<GithubRepositoryModel>) : UiState()
    data class NetworkError(val message: String) : UiState()
    data class UserNotFound(val username: String) : UiState()
}

class HomeViewModel(
    private val getUserRepositoriesUsecase: GetUserRepositoriesUsecase,
) : ViewModel() {

    private val _username = MutableStateFlow<String>("")
    val username: Flow<String>
        get() = _username

    private val _repositories = MutableStateFlow<List<GithubRepositoryModel>>(emptyList())
    val repositories: Flow<List<GithubRepositoryModel>>
        get() = _repositories

    private val _uiState = MutableStateFlow<UiState>(UiState.Initial)
    val uiState: StateFlow<UiState> = _uiState

    fun getUserRepositories(username: String) {
        _username.value = username
        _uiState.value = UiState.Loading

        viewModelScope.launch {
            try {
                val repos = getUserRepositoriesUsecase.execute(username)

                if (repos.isEmpty()) {
                    _uiState.value = UiState.UserNotFound(username)
                } else {
                    _repositories.value = repos
                    _uiState.value = UiState.Success(repos)
                }
            } catch (e: Exception) {
                _uiState.value = UiState.NetworkError(e.message ?: "Error desconocido")
            }
        }
    }

    fun resetState() {
        _uiState.value = UiState.Initial
    }
}
package com.example.githubprofileapp.data.repository

import com.example.githubprofileapp.data.model.GithubRepositoryModel
import com.example.githubprofileapp.data.remote.IApiClient
import com.example.githubprofileapp.domain.repository.IGithubRepository
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode

class GithubRepository(private val apiClient: IApiClient) : IGithubRepository {
    override suspend fun getUserRepositories(username: String): List<GithubRepositoryModel> {
        val response = apiClient.httpClient.get("https://api.github.com/users/$username/repos")

        when (response.status) {
            HttpStatusCode.NotFound -> throw UserNotFoundException("Usuario $username no encontrado")
            HttpStatusCode.OK -> return response.body()
            else -> throw NetworkException("Error de red: ${response.status}")
        }
    }
}

class UserNotFoundException(message: String) : Exception(message)
class NetworkException(message: String) : Exception(message)
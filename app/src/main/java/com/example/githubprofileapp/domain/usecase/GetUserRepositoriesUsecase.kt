package com.example.githubprofileapp.domain.usecase

import com.example.githubprofileapp.data.model.GithubRepositoryModel
import com.example.githubprofileapp.data.repository.NetworkException
import com.example.githubprofileapp.data.repository.UserNotFoundException
import com.example.githubprofileapp.domain.repository.IGithubRepository

class GetUserRepositoriesUsecase(private val iGithubRepository: IGithubRepository) {
    suspend fun execute(username: String): List<GithubRepositoryModel> {
        try {
            return iGithubRepository.getUserRepositories(username)
        } catch (e: UserNotFoundException) {
            throw e
        } catch (e: Exception) {
            throw NetworkException("A network error has occurred. Check your Internet connection and try again later.")
        }
    }
}
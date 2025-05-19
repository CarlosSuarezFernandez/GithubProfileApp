package com.example.githubprofileapp.domain.repository

import com.example.githubprofileapp.data.model.GithubRepositoryModel

interface IGithubRepository {
    suspend fun getUserRepositories(username: String): List<GithubRepositoryModel>
}
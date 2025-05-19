package com.example.githubprofileapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GithubRepositoryModel(
    val id: Long,
    val name: String,
    val description: String? = null,
    @SerialName("html_url") val htmlUrl: String,
    @SerialName("full_name") val fullName: String,
    @SerialName("stargazers_count") val stars: Int = 0,
    val language: String? = null,
    @SerialName("fork") val isFork: Boolean = false,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
    val owner: OwnerModel,
    @SerialName("forks_count") val forksCount: Int = 0,
    val visibility: String = "public"
)

@Serializable
data class OwnerModel(
    val login: String,
    val id: Long,
    @SerialName("avatar_url") val avatarUrl: String,
    @SerialName("html_url") val htmlUrl: String
)
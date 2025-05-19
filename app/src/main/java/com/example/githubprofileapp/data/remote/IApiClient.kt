package com.example.githubprofileapp.data.remote

import io.ktor.client.HttpClient

interface IApiClient {
    val httpClient: HttpClient
}
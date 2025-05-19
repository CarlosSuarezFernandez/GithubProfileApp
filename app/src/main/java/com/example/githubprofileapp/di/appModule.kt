package com.example.githubprofileapp.di

import com.example.githubprofileapp.data.remote.ApiClient
import com.example.githubprofileapp.data.remote.IApiClient
import com.example.githubprofileapp.data.repository.GithubRepository
import com.example.githubprofileapp.domain.repository.IGithubRepository
import com.example.githubprofileapp.domain.usecase.GetUserRepositoriesUsecase
import com.example.githubprofileapp.presentation.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<IApiClient> { ApiClient() }

    single<IGithubRepository> { GithubRepository(get()) }

    factory {
        GetUserRepositoriesUsecase(get())
    }

    viewModel { HomeViewModel(get()) }
}
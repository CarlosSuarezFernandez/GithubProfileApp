package com.example.githubprofileapp

import android.app.Application
import com.example.githubprofileapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.androix.startup.KoinStartup.onKoinStartup

class GithubProfileApp: Application() {

    init {
        // Use AndroidX Startup for Koin

        onKoinStartup {
            androidContext(this@GithubProfileApp)
            modules(appModule)
        }
    }

    override fun onCreate() {
        super.onCreate()
    }
}
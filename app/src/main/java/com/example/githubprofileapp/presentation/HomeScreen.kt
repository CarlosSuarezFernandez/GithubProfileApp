package com.example.githubprofileapp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.githubprofileapp.presentation.components.NetworkErrorDialog
import com.example.githubprofileapp.presentation.components.UserNotFoundDialog
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(homeViewModel: HomeViewModel = koinViewModel()) {
    val navController = rememberNavController()
    val uiState by homeViewModel.uiState.collectAsState()

    val currentUsername by homeViewModel.username.collectAsState(initial = "")

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onSearchClick = { username ->
                    homeViewModel.getUserRepositories(username)
                },
                uiState = uiState,
                onErrorDialogDismiss = {
                    homeViewModel.resetState()
                },
                navigateToProfile = { username ->
                    if (uiState is UiState.Success) {
                        navController.navigate("profile/$username") {
                            launchSingleTop = true
                            homeViewModel.resetState()
                        }
                    }
                }
            )
        }
        composable("profile/{username}") { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: ""
            val repositories by homeViewModel.repositories.collectAsState(initial = emptyList())
            ProfileScreen(
                username = username,
                avatarUrl = "https://avatars.githubusercontent.com/$username",
                repositories = repositories,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onSearchClick: (String) -> Unit = {},
    uiState: UiState = UiState.Initial,
    onErrorDialogDismiss: () -> Unit = {},
    navigateToProfile: (String) -> Unit = {}
) {
    var username by remember { mutableStateOf("") }

    LaunchedEffect(uiState) {
        if (uiState is UiState.Success) {
            navigateToProfile(username)
        }
    }

    when (uiState) {
        is UiState.NetworkError -> {
            NetworkErrorDialog(onDismiss = onErrorDialogDismiss)
        }
        is UiState.UserNotFound -> {
            UserNotFoundDialog(
                username = uiState.username,
                onDismiss = onErrorDialogDismiss
            )
        }
        is UiState.Success -> {
            navigateToProfile(username)
        }
        else -> {}
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("GitHub Viewer") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Usuario de GitHub") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                singleLine = true
            )

            Button(
                onClick = { onSearchClick(username) },
                modifier = Modifier.fillMaxWidth(),
                enabled = uiState !is UiState.Loading
            ) {
                if (uiState is UiState.Loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Text(
                        text = "Search",
                        modifier = Modifier.padding(vertical = 4.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
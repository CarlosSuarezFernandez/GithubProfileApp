package com.example.githubprofileapp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.githubprofileapp.data.model.GithubRepositoryModel
import com.example.githubprofileapp.data.model.OwnerModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    username: String,
    avatarUrl: String,
    repositories: List<GithubRepositoryModel>,
    onBackClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "volver"
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        AsyncImage(
                            model = avatarUrl,
                            contentDescription = "foto de perfil",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(120.dp)
                                .clip(CircleShape)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = username,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(paddingValues)
        ) {
            items(repositories) { repo ->
                RepositoryItem(repository = repo)
            }
        }
    }
}

@Composable
fun RepositoryItem(repository: GithubRepositoryModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = repository.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            repository.language?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    val mockRepos = listOf(
        GithubRepositoryModel(
            id = 1,
            name = "android-sample",
            description = "Sample Android app",
            htmlUrl = "",
            fullName = "user/android-sample",
            stars = 5,
            language = "Kotlin",
            isFork = false,
            createdAt = "",
            updatedAt = "",
            owner = OwnerModel(
                login = "user",
                id = 1,
                avatarUrl = "",
                htmlUrl = ""
            ),
            forksCount = 2
        ),
        GithubRepositoryModel(
            id = 2,
            name = "web-project",
            description = "Web project sample",
            htmlUrl = "",
            fullName = "user/web-project",
            stars = 3,
            language = "JavaScript",
            isFork = true,
            createdAt = "",
            updatedAt = "",
            owner = OwnerModel(
                login = "user",
                id = 1,
                avatarUrl = "",
                htmlUrl = ""
            ),
            forksCount = 0
        )
    )

    ProfileScreen(
        username = "CarlosSuarezFernandez",
        avatarUrl = "https://avatars.githubusercontent.com/u/105274746?v=4",
        repositories = mockRepos
    )
}
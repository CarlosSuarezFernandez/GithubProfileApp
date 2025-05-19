package com.example.githubprofileapp.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ErrorDialog(
    title: String,
    message: String,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = title) },
        text = { Text(text = message) },
        confirmButton = {
            Button(
                onClick = onDismiss
            ) {
                Text("OK")
            }
        }
    )
}

@Composable
fun NetworkErrorDialog(onDismiss: () -> Unit) {
    ErrorDialog(
        title = "Error de red",
        message = "A network error has occurred. Check your Internet connection and try again later.",
        onDismiss = onDismiss
    )
}

@Composable
fun UserNotFoundDialog(username: String, onDismiss: () -> Unit) {
    ErrorDialog(
        title = "Usuario no encontrado",
        message = "User not found. Please enter another name",
        onDismiss = onDismiss
    )
}
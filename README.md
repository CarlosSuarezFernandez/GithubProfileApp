# GitHub Profile App

A simple Android application that allows users to search for GitHub profiles and view their public repositories.

## Features

*   **Search GitHub Profile**: Enter a GitHub username to view their details.
*   **View Profile Information**: Displays the avatar and username of the searched profile.
*   **List Repositories**: Shows a list of the user's public repositories.
*   **State Handling**:
    *   Loading indicator while fetching data.
    *   Error message in case of network issues.
    *   Specific message if the user is not found.
*   **Navigation**: Home screen for search and profile screen to display details.

## Technologies Used

*   **Kotlin**: Primary programming language.
*   **Jetpack Compose**: For building the UI declaratively.
*   **ViewModel**: To manage UI state and business logic.
*   **Kotlin Coroutines & Flow**: For asynchronous programming.
*   **Koin**: For dependency injection.
*   **Coil**: For image loading (avatars).
*   **Ktor: Used for GitHub API calls.
*   **Material 3**: For UI design components.
*   **Android Navigation Component**: To manage navigation between screens.

## Project Structure (based on provided files)

*   **`data`**:
    *   `model`: Contains data models (e.g., `GithubRepositoryModel`, `OwnerModel`).
    *   `repository`: (Implied) Would contain logic for fetching data from the network or local sources.
*   **`domain`**:
    *   `usecase`: Contains application use cases (e.g., `GetUserRepositoriesUsecase`).
    *   `repository`: (Interface) Defines the contract for data repositories (e.g., `IGithubRepository`).
*   **`presentation`**:
    *   Contains UI Composables (`HomeScreen`, `ProfileScreen`), ViewModels (`HomeViewModel`), and UI state management (`UiState`).
    *   `components`: (Implied) Could contain reusable UI components (e.g., `NetworkErrorDialog`, `UserNotFoundDialog`).

## How to Use

1.  Launch the application.
2.  On the home screen, enter a GitHub username in the text field.
3.  Press the "Search" button.
4.  If the user is found and there are no network errors, you will be redirected to the profile screen where you'll see their avatar, username, and a list of their repositories.
5.  If a network error occurs or the user is not found, an informational dialog will be displayed.
6.  From the profile screen, you can return to the search screen using the back button.

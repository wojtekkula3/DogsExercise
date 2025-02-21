# 🐕 Dogs exercise

This application displays a list of dog breeds fetched from a REST API. When a breed name is tapped, it opens a screen with
the breed name.

### Features

- Fetches and displays a list of dog breeds from a REST API.
- Opens a detailed screen with the breed name when a breed is selected.

### Architecture

- The `breeds_list` module is implemented using the **MVVM** architecture with Jetpack Compose for the UI layer.
- The `breed_details` module has Fragment to show that Compose and Fragments can live side by side.

### Setup

1. Clone the repository.
2. Open the project in Android Studio.
3. Build the project to download dependencies and set up the environment.

### Refactoring

The `breeds_list` module was at first structured according to **VIPER** architecture and has been refactored to use the
**MVVM** architecture to better accommodate potential future business requirement changes.

# Introduction
Welcome to the Keep Note Android app! Keep Note is a simple yet powerful note-taking application built for Android devices. This app is designed to help you keep track of your thoughts, ideas, to-do lists, and important notes with ease. It is built using modern Android development technologies and follows best practices, including Kotlin, MVVM architecture, View Binding, Dagger 2, Room, Navigation, Clean architecture, and SOLID principles.

# Features
The Keep Note Android app comes with a range of features to enhance your note-taking experience:

- **Add Note**: Easily create new notes, give them a title and a description.
- **View Note**: List and view your notes. You can tap on a note to view its details.
- **Edit Note**: You can also edit your notes if you need to make changes.
- **Delete Note**: Remove notes you no longer need.

# Technologies Used
The Keep Note Android app is developed using the following technologies:

- **Kotlin**: The primary programming language used for Android app development.
- **MVVM (Model-View-ViewModel) Architecture**: The app follows the MVVM architectural pattern, separating concerns and making it easier to maintain and test.
- **View Binding**: A feature that simplifies view interaction and reduces the risk of null pointer exceptions.
- **Dagger 2**: A dependency injection framework for efficient management of object creation and the app's component dependencies.
- **Room Database**: A SQLite object mapping library that provides an abstraction layer over the underlying database, making data persistence and retrieval easier.
- **Navigation Component**: An Android Jetpack library used for navigation and in-app navigation graphs.
- **Clean Architecture**: A software architecture that separates the app into multiple layers (presentation, domain, data) for improved maintainability and testability.
- **SOLID Principles**: Principles for writing maintainable and scalable code.

# App Structure
The app is structured with the following key components:

- '**app**': This module contains the framework package which core business logic and the presentation package which handles the user interface and view-related logic, following the MVVM architecture.
- '**core**': This module is responsible for managing data storage, and defining use cases.

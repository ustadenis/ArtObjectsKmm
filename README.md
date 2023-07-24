# ArtObjectsKmm
- Use the Rijksmuseum API, see documentation here: https://data.rijksmuseum.nl/object-metadata/api/
- We would like to see an app with at least two screens: \
  An overview page with a list of items: \
      - Should be visually splitin sections with headers, grouped by author, with the author's name in the header. \
      - Items should have text and image. \
      - Screenshouldbepaginated. \
  A detail page, with more details about the item.
- Loading of data and images should be asynchronous, a loading indicator should be shown.
- Automated tests should be present (full coverage not needed of course).
- Please use Fragments (used heavily in our code base) and a single Activity.
- Please use Kotlin (100% of our codebase is).

## Project details:

![KMM Logo](https://mobiraft.com/wp-content/uploads/2020/04/SwiftUI-3.png)

The project was developed using the ***Kotlin Multiplatform*** framework and must adhere to the specified requirements. \
https://kotlinlang.org/docs/multiplatform-mobile-getting-started.html

Currently, it supports both Android and iOS platforms.

To build the project, ensure that you have installed the ***Kotlin Multiplatform plugin*** in Android Studio. Also please note, that KMM compiles using java 11 version. 
Before gradle sync update your java version in android srudio preferences. You also need to verify your environment configuration using ***kdoctor***

> // Terminal \
> brew install kdoctor \
> kdoctor

Please follow instruction of kdoctor.

You can assemble the iOS build using Xcode, and both iOS and Android builds can be created using Android Studio.

## Notes:
1. PaginatedList compose view implementation was splitted by platforms.\
   Resons:
   - Show native android paging implementation
   - Show technical ability of the composable implementation splitting
Basicaly it is possible to use iOS implementation in common module.
1. At present, the implementation of the art objects list suffers from low performance. The main reason behind this issue is the high resolution of the images received from the backend (2000X2500). Library which was used for image loading doesn't support resize before cachign, and there are no other libs because of little KMM community at this point of time.\
   Possible solutions:
   - Implement your own image loading and resize images before caching (it takes quite big effort, don't thing that it is ok for test task)
   - Image resolution recieved from backend should be variable.

## Unit testing
Unit tests were written for data repository, domain use cases and view models. For now it runs only for android platform because of mockk library.\
To run tests you can use the following commands in terminal:
> ./gradlew assemble shared:testDebug // for viewmodels tests\
> ./gradlew assemble shared:data:testDebug // for repo tests\
> ./gradlew assemble shared:domain:testDebug // for usecases tests

## Used libraries:

https://ktor.io/docs/getting-started-ktor-client-multiplatform-mobile.html - network

https://github.com/AlexGladkov/Odyssey - navigation

https://github.com/rickclephas/KMM-ViewModel - crossplatform viewmodel

https://insert-koin.io/ - DI

https://github.com/icerockdev/moko-resources/tree/master - resources (Strings, Colors, Drawables)

https://github.com/Kamel-Media/Kamel - Image loader

## Project stracture:

Project contains 3 modules:
- Android application (androidApp gradle module)
- iOS application (xcode project)
- Shared library (common feature code)

Shared library contains almost all code in this project. Basically it is feature module with can be provided to android and iOS projects independantly.\
Shared module as feature module is splitted by clean architecture layers (data, domain).\
Shared, data and domain modules are crossplatform modules and may contain different implementation for the particular platform (PaginatedList as an example).

## Debug build:
Debug build can be found by the following link: https://drive.google.com/file/d/1v48gSkJhuk4B0QYG9qgSuap20Lnw0F3e/view?usp=sharing

## Screen shots:

![Art objects list](https://github.com/ustadenis/ArtObjectsKmm/blob/main/pictures/Screen%20Shot%202023-07-24%20at%2012.14.42%20AM.png)
![Art object details](https://github.com/ustadenis/ArtObjectsKmm/blob/main/pictures/Screen%20Shot%202023-07-24%20at%2012.15.39%20AM.png)
![Art object details](https://github.com/ustadenis/ArtObjectsKmm/blob/main/pictures/Screen%20Shot%202023-07-24%20at%2012.16.19%20AM.png)

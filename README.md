# Android application for showing Universe



Universe App is a fully functional Android app built entirely with Kotlin and Jetpack Compose. This repository serves as an Android project architecture showcase. It follows Android design and development best practices and is intended to be a useful reference for developers.

![Universe Screenshot](https://github.com/stevan-milovanovic/Universe/assets/10914936/822c74dc-808c-4890-a839-422e14d1d0cd)
![Planet List Screenshot](https://github.com/stevan-milovanovic/Universe/assets/10914936/7241607e-f731-4c6a-9147-972801b7e2ec)
![Planet Details Screenshot](https://github.com/stevan-milovanovic/Universe/assets/10914936/c7680da8-39b9-45ba-afaf-e6f0835d1e59)

## Thanks To

Repository was initially based on the official [Now in Android App](https://github.com/android/nowinandroid) and it is
updated to use latest tech stack in Android ecosystem. This project uses free [API](https://swapi.py4e.com/api/) for fetching the data.


## Future Improvements

- Improve the user interface to make the application look more attractive to users
- Cover remaining code with unit tests
- Implement Residents and Resident Details screens


### Libraries

* [Jetpack Core][core]
* [MVVM architectural pattern][mvvm]
* [Dependency injection with Hilt][hilt]
* [Jetpack Compose UI][compose]
* [Room][room] to save data in a local database
* [Material Design 3][material3]
* [Retrofit][retrofit] for REST API communication
* [Moshi][moshi] for parsing JSON into Kotlin classes
* [Paging3][paging3] for loading and displaying paged data

[paging3]: https://developer.android.com/topic/libraries/architecture/paging/v3-paged-data

[core]: https://developer.android.com/jetpack/androidx/releases/core

[mvvm]: https://developer.android.com/topic/libraries/architecture/viewmodel

[hilt]: https://developer.android.com/training/dependency-injection/hilt-android

[compose]: https://developer.android.com/jetpack/compose

[material3]: https://m3.material.io/develop/android/mdc-android

[retrofit]: http://square.github.io/retrofit

[moshi]: https://github.com/square/moshi

[room]: https://developer.android.com/jetpack/androidx/releases/room

# Flicker Client

## Packaging

This project uses packaging by feature or module, which means that top level packages would represent feature or modules of the application, and the structure goes like this

- **core**: is a package where the base implementation and common code which will be resused acroos multiple features or modules
- **injection**: is a package where the dependency injection code of the application lies, explanation of that is elaborated below
- **photosearch**: is the package containing implmentation of a feature where the user can type free text and and searches Flickr's public photos for photos matching this description in title, description of tags
- **photodetails**: is the package where we show the details for each photo that is retreived in search by previous module
- **util**: common utilities used by the app

Each feature package would be split into the layers of the architecture used

## Architecture (MVP - Model View Presenter)
In the application, MVP architecture is used where each feature has the following layers

- **Model**: This layer is responsible for data retreival from any data provider (Cloud, Database... etc). This layer contains implementation for the Repository pattern which is defined in the presentation layer, this pattern represents the communication point between the Model and the Presenter
- **Presenter**: This layer is responsible for communication with the Model to retreive the model, format its values and manipluate the View to show the data accordingly
- **View**: This layer contains Android Framwork classes that are used to present the UI to the user (Activity, Fragment, Adapters and Android Views). Implemented through an interface which provides abstraction between the View layer and the Presenter layer

## Build Requirements
This is an Android Studio project using the Gradle build system, to build this project you need
- Build tools version 24.0.3
- API level 24
- All other dependencies should be retreived automatically from Maven if not cached already
- Android APT plugin is used for code generation, which is used by Dagger and ButterKnife to generate injection code

## Third party libraries
- Android support libraries v4 - v7: used mainly for backward compatability support for Fragments, Styling and Appcompat Theme
- ButterKnife: used for view injection, and view click events
- Dagger: used for injecting dependencies to facilitate having modular parts of the application that are easily testable. Example of modules being injected refer to [NetworkModule](https://github.com/melmorabea/Flicker_Client/blob/develop/app/src/main/java/com/mobiquity/flicker/injection/network/NetworkModule.java) and [AppModule](https://github.com/melmorabea/Flicker_Client/blob/develop/app/src/main/java/com/mobiquity/flicker/injection/AppModule.java)
- Retrofit: used for making HTTP requests and parsing results in an interface looking way
- OkHttp: used for making the actual HTTP requests by Retrofit
- Gson: used for parsing network calls responses by Retrofit and is used through Retrofit's ConverterFactory
- RxJava: adding a reactive flavor for the glue between our layers, all communication through Repository, Model and Presenter are through RxJava's Observables
- Picasso: used for image loading and caching in memory and on disk
- LeakCanary: used for detecting memory leaks [Only in debug mode]
- JUnit and Mockito used for unit testing and mocking dependencies in the test environment

#### More about the project
- Product flavors is used to inject the constants values that needs to be altered by flavor, like base URLs and API keys [We only have one flavor now, but adding more flavors with different values would be seamless to the application]
- Gradle properties extensions is used to define common third libraies versions to avoid conflicts and for easy version updating

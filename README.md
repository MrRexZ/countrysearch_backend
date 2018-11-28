# Country Search (Backend)

My first experimentation with building a simple web app in Spring Boot.
Pairable with [my other frontend app](https://github.com/MrRexZ/countrysearch_frontend).


## Table of Contents
1. [Requirements](#requirements)
2. [Instructions](#instructions)
3. [Project Structure](#project-structure)


## Requirements
1. Java 8 or above
2. Maven
3. An open port of `48042` at your local system. Otherwise, change the default port at `application.properties`.
If the default port is changed, and if you're using [this frontend app](https://github.com/MrRexZ/countrysearch_frontend), you'll need to change the target port there too. 

## Instructions
To launch the app, you can either refer to the `Release` tabs in Github or building from source.

To build from source :

1. Navigate to root, then run `mvn package`.
2. With the generated `target` folder, run `java -jar countrysearch_backend-0.0.1-SNAPSHOT.jar`.


## Project Structure
In `/src/main/java` folder:
* `config` - Contains app-specific configuration data.
* `controller` - Contains controller of the application, API handlers are declared here. Used as interface to communicate with outside the app. 
* `model` - Contains model/domain data of the application
* `repository` - The interface to data layer
* `service` - Contains business rules/logic of the application, bridge between `controller` and `repository`
* `tasks` - Tasks that are run independently to user requests and/or periodically.
* `util` - Utilities files containing data/functions which can be shared through the entirety of the app.
 
In `/src/main/resources` folder:
* `json` - Directory that contains the input data `countries_metadata.json`

In `src/test` folder:
* `testdata`-  Folder contains testing data as mock that belongs to the direct parent folder & its children that it resides within.
* `*Test` - Contains test for particular class. Currently only `CountryServiceTest` and `CountryFilterServiceTest` are  tested only as those are the most customized parts from the entire application.



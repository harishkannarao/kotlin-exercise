# Kotlin Exercise

## Required Software, Tools and Version
* Java JDK Version: 11 Adopt OpenJDK (`java -version`)
* Git Client: Any latest version (`git --version`)
* Integrated Development Environment: Any version of IntelliJ Idea or Eclipse

## Github Actions Build status
[![Build Status](https://github.com/harishkannarao/kotlin-exercise/workflows/CI-master/badge.svg)](https://github.com/harishkannarao/kotlin-exercise/actions?query=workflow%3ACI-master)

## Commands

### Build (test and assemble)

    ./gradlew clean build
    
### Execute the application

#### Gradle

    ./gradlew clean run
    
#### Java

Assemble the Jar file:

    ./gradlew clean assemble

Simpler Way:

    java -jar application/build/libs/application-exec.jar

Verbose Way with explicit class name to execute:

    java -cp application/build/libs/application-exec.jar com.harishkannarao.kotlin.exercise.DemoApplication

### Change project version

    ./gradlew clean build -PprojectVersion=1.0.0
 

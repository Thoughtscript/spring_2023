# Spring 2023

[![](https://img.shields.io/badge/Java-1.18-yellowgreen.svg)](https://www.oracle.com/java/technologies/downloads/#java18)
[![](https://img.shields.io/badge/Spring%20Boot-2.7.5-green.svg)](https://spring.io/projects/spring-boot)
[![](https://img.shields.io/badge/Gradle-7.5.1-darkslategray.svg)](https://gradle.org/)
[![](https://img.shields.io/badge/Maven-3.8.6-white.svg)](https://maven.apache.org/download.cgi)

## Install

Maven, Gradle, etc.

## Use

```BASH
mvn clean
mvn install
mvn spring-boot:run
```

Gradle:

```BASH
./gradlew clean
./gradlew build
./gradlew run
```

## SSL

```BASH
keytool -genkey \
  -alias interviewhelper \
  -keystore interviewhelper.p12 \
  -storetype PKCS12 \
  -keyalg RSA \
  -storepass F#4%DQwe734__6f*ed72 \
  -validity 730 \
  -keysize 4096
```

## API

1. http://localhost:8080/api/functional/user/all

```
{"users":[
    {"username":"userTwo","name":"John Doe","phone":"222-222-2222","contacts":[],"email":null},
    {"username":"userOne","name":"Jane Doe","phone":"111-111-1111","contacts":[],"email":null},
    {"username":"userThree","name":"Every Person","phone":"333-333-3333","contacts":[],"email":null}],
    "contacts":[],
    "message":"Success!"
 }
```

## Notes

Quite a bit has changed in WebFlux, etc.

1. First, `.block()` is no longer allowed in any **Reactive Thread**.
2. Second, needed to remove some configuration that was more permissively allowed previously. (Some have been upgraded from **Warnings** to **Exceptions** or **Errors**)
3. Need to reenable some CORS settings once frontend is updated: `allowCredentials(false)` needs to be reset to `true` and the **HTTP Header** needs to be passed.
4. Looks like `@DBRef` has changed (this may have been misconfigured previously but wasn't blocking or throwing an **Exceptions**)!
5. Some caching needs to be corrected.
6. I swapped out email smtp, etc. 
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

### Secured

1. Secured - getOneUser
```
POST http://localhost:8080/api/flux/students/one
```

2. Secured - saveOneUser
```
POST http://localhost:8080/api/flux/students/one/new
```

3. Secured - getOneUser
```
DELETE http://localhost:8080/api/flux/students/one
```

4. Secured - updateOneUser
```
POST http://localhost:8080/api/flux/students/one
```

5. Secured - getAllUsers
```
POST http://localhost:8080/api/flux/students/all
```

### Public

6. getOneLanguageStudent
```
POST http://localhost:8080/api/functional/students/one
```

7. saveOneLanguageStudent
```
POST http://localhost:8080/api/functional/students/new
```

8. deleteOneLanguageStudent
```
DELETE http://localhost:8080/api/functional/students/one
```

9. updateOneLanguageStudent
```
PUT http://localhost:8080/api/functional/students/one
```

10. getAllLanguageStudent
```
GET http://localhost:8080/api/functional/students/all
```
```JSON
[
    {"name":"bob","languageIds":["english","german","korean"],"primaryLanguageString":"german"},
    {"name":"adam","languageIds":["english","java","python","javascript"],"primaryLanguageString":"english"},
    {"name":"mary","languageIds":["java","python","javascript"],"primaryLanguageString":"java"}
]
```

### Auth

11. magiclink
```
POST http://localhost:8080/magiclink
```

## Notes

Quite a bit has changed in WebFlux, etc.

1. First, `.block()` is no longer allowed in any **Reactive Thread**.
2. Second, needed to remove some configuration that was more permissively allowed previously. (Some have been upgraded from **Warnings** to **Exceptions** or **Errors**)
3. Need to reenable some CORS settings once frontend is updated: `allowCredentials(false)` needs to be reset to `true` and the **HTTP Header** needs to be passed.
4. Looks like `@DBRef` has changed (this may have been misconfigured previously but wasn't blocking or throwing an **Exceptions**)!
5. Some caching needs to be corrected.
6. I swapped out email smtp, etc. 

## Resources and Links

1. https://szymonsawicki.net/webflux-and-mongodb-rest-api-using-ddd/
2. https://www.linkedin.com/pulse/introduction-reactive-programming-spring-webflux-aneshka-goyal/?trk=pulse-article_more-articles_related-content-card
3. https://howtodoinjava.com/spring-webflux/spring-webflux-tutorial/
4. https://www.javaguides.net/2023/01/spring-webflux-reactive-crud-rest-api.html
5. https://www.baeldung.com/spring-webflux-404
6. https://bootify.io/mongodb/document-reference-in-spring-boot-mongodb.html
7. https://www.baeldung.com/spring-mongodb-dbref-annotation
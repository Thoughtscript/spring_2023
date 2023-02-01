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

#### Secured - getOneLanguageStudent
```
POST http://localhost:8080/api/students/one
```

#### Secured - saveOneLanguageStudent
```
POST http://localhost:8080/api/students/one/new
```

#### Secured - deleteOneLanguageStudent
```
DELETE http://localhost:8080/api/students/one
```

#### Secured - updateOneLanguageStudent
```
POST http://localhost:8080/api/students/one
```

#### Secured - getAllLanguageStudents
```
POST http://localhost:8080/api/students/all
```

#### Secured - getOneLanguage
```
POST http://localhost:8080/api/flux/languages/one
```

#### Secured - saveOneLanguage
```
POST http://localhost:8080/api/flux/languages/one/new
```

#### Secured - deleteOneLanguage
```
DELETE http://localhost:8080/api/flux/languages/one
```

#### Secured - updateOneLanguage
```
POST http://localhost:8080/api/flux/languages/one
```

#### Secured - getAllLanguages
```
POST http://localhost:8080/api/flux/languages/all
```

### Public

#### Unsecured - getAllLanguages
```
GET http://localhost:8080/api/unsecured/languages/all
```
```JSON
[
   {"name":"german","greeting":"guten tag"},
   {"name":"korean","greeting":"Anyoung haseyo"},
   {"name":"english","greeting":"hello"},
   {"name":"python","greeting":"hello world"},
   {"name":"java","greeting":"hello world"},
   {"name":"javascript","greeting":"hello world"}
]
```

#### Functional - getOneLanguage
```
POST http://localhost:8080/api/functional/languages/one
```
Body:
```JSON
{
    "name": "korean"
}
```
Response:
```JSON
{
    "name": "korean",
    "greeting": "Anyoung haseyo"
}
```

#### Functional - saveOneLanguage
```
POST http://localhost:8080/api/functional/languages/new
```
Body:
```JSON
{
    "name": "korean",
    "greeting": "Anyoung haseyo"
}
```
Response:
```JSON
{
    "name": "korean",
    "greeting": "Anyoung haseyo"
}
```
#### Functional - deleteOneLanguage
```
DELETE http://localhost:8080/api/functional/languages/one
```

#### Functional - updateOneLanguage
```
PUT http://localhost:8080/api/functional/languages/one
```
Body:
```JSON
{
    "name": "korean",
    "greeting": "seffsfefsefsfefes haseyo"
}
```
Response:
```JSON
{
    "name": "korean",
    "greeting": "seffsfefsefsfefes haseyo"
}
```
#### Functional - getAllLanguages
```
GET http://localhost:8080/api/functional/languages/all
```
```JSON
[
   {"name":"german","greeting":"guten tag"},
   {"name":"korean","greeting":"Anyoung haseyo"},
   {"name":"english","greeting":"hello"},
   {"name":"python","greeting":"hello world"},
   {"name":"java","greeting":"hello world"},
   {"name":"javascript","greeting":"hello world"}
]
```

### Auth

#### magiclink
```
POST http://localhost:8080/magiclink
```

## Notes

Quite a bit has changed in WebFlux, etc.

1. First, `.block()` is no longer allowed in any **Reactive Thread**. Removed.
2. Divided handlers and repositories into blocking and non-blocking services.
3. Two MongoDB connections (one reactive, one blocking) are now configured. Needed to refamiliarize myself with `MongoTemplate` and `Repository` patterns (also basic configuration vs. `AbstractMongoClientConfiguration`).
4. Second, needed to remove some configuration that was more permissively allowed pr**e**viously. (Some have been upgraded from **Warnings** to **Exceptions** or **Errors**)
5. Need to reenable some CORS settings once frontend is updated: `allowCredentials(false)` needs to be reset to `true` and the **HTTP Header** needs to be passed.
6. Some caching needs to be corrected.
7. Proper `pom.xml` for MongoDB drivers: `mongodb-driver-sync`.
8. I swapped out email smtp, etc. 
9. Spring Data Mongo DB no longer supports `@DBRef` or any `@DocumentReference` within a reactive infrastructure.
   * https://github.com/spring-projects/spring-data-mongodb/issues/3808
   * Looks like `@DBRef` has changed (this may have been misconfigured previously but wasn't blocking or throwing an **Exceptions**)!
   * Bottom of [18.5.9](https://docs.spring.io/spring-data/mongodb/docs/3.3.0-M2/reference/html/#mapping-usage.document-references) in comment: `There is no support for reading document references using reactive infrastructure.`

## Resources and Links

1. https://szymonsawicki.net/webflux-and-mongodb-rest-api-using-ddd/
2. https://www.linkedin.com/pulse/introduction-reactive-programming-spring-webflux-aneshka-goyal/?trk=pulse-article_more-articles_related-content-card
3. https://howtodoinjava.com/spring-webflux/spring-webflux-tutorial/
4. https://www.javaguides.net/2023/01/spring-webflux-reactive-crud-rest-api.html
5. https://www.baeldung.com/spring-webflux-404
6. https://bootify.io/mongodb/document-reference-in-spring-boot-mongodb.html
7. https://www.baeldung.com/spring-mongodb-dbref-annotation
8. https://docs.spring.io/spring-data/mongodb/docs/3.3.0-M2/reference/html/#mapping-usage.document-references
9. https://thepracticaldeveloper.com/full-reactive-stack-2-backend-webflux/
10. https://www.devglan.com/spring-boot/spring-boot-mongodb-crud
11. https://www.baeldung.com/spring-data-mongodb-tutorial
# Spring 2023

[![](https://img.shields.io/badge/React-18.2.0-orange.svg)](https://reactjs.org) [![](https://img.shields.io/badge/parcel-2.7.0-royalblue.svg)](https://parceljs.org/) [![](https://img.shields.io/badge/sqlitebroswer-3.12.2-white.svg)](https://sqlitebrowser.org/) [![](https://img.shields.io/badge/Java-1.18-yellowgreen.svg)](https://www.oracle.com/java/technologies/downloads/#java18)
[![](https://img.shields.io/badge/Spring%20Boot-2.7.5-green.svg)](https://spring.io/projects/spring-boot)
[![](https://img.shields.io/badge/Gradle-7.5.1-darkslategray.svg)](https://gradle.org/)
[![](https://img.shields.io/badge/Maven-3.8.6-white.svg)](https://maven.apache.org/download.cgi)
[![](https://img.shields.io/badge/Redis-5.7.40-red.svg)](https://redis.io/)
[![](https://img.shields.io/badge/MongoDb-4.2-023430.svg)](https://www.mongodb.com/)
[![](https://img.shields.io/badge/rabbitmq-3.11.3-green.svg)](https://www.rabbitmq.com/tutorials/tutorial-one-spring-amqp.html)

## Install

Maven, Gradle, etc.

```BASH
nvm install 16.17.0
nvm use 16.17.0
```

## Use

React:

```BASH
cd dist
npm run build-parcel-prod
npx serve
```

Maven:

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

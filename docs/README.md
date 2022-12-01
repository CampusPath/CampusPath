# CampusPath Documentation

## Development Environment

Before running any gradle tasks with the gradle wrapper, ensure that Java 17 (minimum)
is on the path:

```
C:\Users\dev>java -version
java version "18.0.2.1" 2022-08-18
Java(TM) SE Runtime Environment (build 18.0.2.1+1-1)
Java HotSpot(TM) 64-Bit Server VM (build 18.0.2.1+1-1, mixed mode, sharing)
```

If an unsupported Java version is not installed, the JDK installer is available from Oracle
[here](https://www.oracle.com/java/technologies/downloads/).

## Server

### Starting Development Server

The server can be started using the gradlew wrapper script:

```
gradlew campuspath-api:bootRun
```

## App

### Starting Development Server

If you already have NPM and Angular CLI installed, running the following commands will work:

```
cd campuspath-web
ng serve
```

Otherwise, using the provided `ngServe` gradle task will download all the required packages
and start the angular development server:

```
gradlew campuspath-web:ngServe
```

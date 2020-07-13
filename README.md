# MasterCard Code Challenge
This application determines if a road connects two cities.

## Background
Two cities are considered connected if there are a series of roads that may be traveled
from one city to another.

The list of roads between cities is available in a file.

Given two city names, the application will return a `yes` if they are connected, and a `no`
if they are not, or upon any invalid input.  As a well-behaved REST application, it also
returns an HTTP status code of 200 upon finding a route and a 404 upon not finding a route.

The application endpoint takes two required parameters, `origin` and `destination`.  Here
is a valid URL:
```http://localhost:8080/connected?origin=Boston&destination=Newark```

The application reads the cities and routes via an input text file.  The application expects
this file to be in the directory `src/main/resources` and to be named `city.txt`. The file
should be in a Comma-Separated Value (CSV) format, with one origin and one destination per
line, e.g., `ORIGIN,DESTINATION`.

### Prerequisites
This application builds with OpenJDK 14.0.1 and Gradle 6.5.1.  Ensure that you have a
compatible JDK in order to build and run this application.  The Gradle wrapper provides
Gradle for this project.

A sample `city.txt` is extant in the `src/main/resources` folder for your edification; feel
 free to use that or modify it per your needs.

While it was developed using IntelliJ IDEA Ultimate 2020.1, you may import this project into
any modern IDE.

## Testing strategy
This project contains unit and integration tests, currently all combined in the `src/test`
folder.  Future developers who have more time may choose to segregate the integration tests
into a separate sourceSet and associate that sourceSet with a different Gradle task.

The project used Test Driven Development (TDD), hence the code coverage is fairly high,
except for the main method to the entry class.  However, since that method's functionality
is to execute another method, which itself is covered by tests, this should be acceptable.
Naming conventions for the tests followed the Behavior Driven Development (BDD)
recommendations of When/Then method names.  Future developers who have more time may choose
to add additional edge case tests and greater input validation tests.  Since the requirements
mentioned that the application should return `no` upon any invalid input, the application
performs only basic input validation tests.

All development requires assumptions.  The application makes the major assumption that the
input file will be located at `src/main/resources/city.txt`.

## Running the tests
You may use Gradle to compile the code and run the unit and integration tests.  On a Windows
system, at the command prompt, issue a
```
gradle clean test
```
while at a POSIX system's command prompt, issue a
```
./gradlew clean test
```

### Running
You may use Gradle to run the application.  
compile the code and run the unit and integration tests.  On a Windows system, at the command
prompt, issue a
```
gradle clean run
```
while at a POSIX system's command prompt, issue a
```
./gradlew clean run
```

Given the sample input file consisting of
```
Boston, New York
Philadelphia, Newark
Newark, Boston
Trenton, Albany
```
a URL of 
```http://localhost:8080/connected?origin=Boston&destination=Newark```
should return `yes`, whereas a URL of
```http://localhost:8080/connected?origin=Boston&destination=Philadelphia```
should return yes, whereas a URL of
```http://localhost:8080/connected?origin=Philadelphia&destination=Albany```
should return no.

### Design
The application uses layers to separate logic.  Presentation logic resides in the
`presentation` package.  Business logic resides in the `service` package.  Data access logic
and a Data Transfer Object (DTO) lives in the `data` package.  The entry class is located at
the root package level.

The application uses a Depth-First Search (DFS) algorithm to search for routes between
cities.

The application uses Swagger/OpenAPI for ease with development.  I left that Swagger/OpenAPI
in the application for the benefit of future developers.  It may easily be removed by
removing the `config` package, its two dependencies in the Gradle build file, and respective
annotations in the DTO class.

## Future Enhancements
To be a pure REST application, I would suggest that a future version not return `yes` or
`no` but instead return HTTP status codes of 204 upon route found and 404 upon route not
found.  After all, a fundamental principle of REST is brevity, and an HTTP status code
in the response header could provide the requisite information to consumers without
needing to provide a response body.

Per requirements, this project used Java and Spring Boot and no extra libraries.

Depending on business requirements, the application could be enhanced by adding the ability
to specify the input file via such means as a command-line parameter.

Moreover, given more time, the integration tests could be separated into their own Gradle
source set.

Of course, the application could benefit from adding much more robust testing than was
permitted in the time allotted.

## Built With
* [OpenJDK 14](https://openjdk.java.net/projects/jdk/14/) - OpenJDK version for this project
* [Gradle](https://gradle.org/) - Dependency Management
* [JUnit 5](https://junit.org/junit5/) - Testing framework

## Author
Anurup Joseph (`anurup_joseph@yahoo.com`) thanks you for the opportunity to take this fun
challenge.
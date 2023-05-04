# Navigation System
This is a simple navigation system project that allows you to track mobile stations and their locations using a RESTful API. The project is built using Java and the Spring Boot framework.

## Key Features
Mobile stations can be registered and tracked using unique UUIDs.
Mobile stations' locations are recorded and can be retrieved via the API.
H2 Database in Server Mode is used for data persistence.
### Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites
- Java 8 or higher
- Maven
- H2 Database
### Running the Application
- After you cloned the repository, change into the project directory:
```
cd navigationsystem
```
- Start the H2 Database in Server Mode:
```
java -cp /home/user/.m2/repository/com/h2database/h2/2.1.214/h2-2.1.214.jar org.h2.tools.Server
```
**Note**: This command assumes that the H2 JAR file is located at ```/home/user/.m2/repository/com/h2database/h2/2.1.214/h2-2.1.214.jar```. Adjust the path to the JAR file if necessary.


The **H2 Database** is used in **Server Mode** to allow multiple connections to the same database, which is useful for development and testing purposes. Additionally, this mode allows easy access to the H2 Console for managing and querying the database.

You can access the H2 Database console at http://localhost:8080/h2-console. The JDBC URL should be ```jdbc:h2:tcp://localhost/./data/testdb```. 
The default username is ```sa``` and there is no password.

- In a new terminal, build the project using Maven:
```
mvn clean install
```
- Run the application:
```
mvn spring-boot:run
```
The application will start, and the RESTful API will be available at http://localhost:8080.

## API Endpoints
- ```GET /location/{uuid}```: Retrieve the location of a mobile station with the given UUID.

- ```POST /report```: Receive a detection report containing the ID of the base station, along with an array of reports including the ID of each mobile station, the distance to the base station, and the timestamp of the report.
---
### License
This project is licensed under the MIT License.
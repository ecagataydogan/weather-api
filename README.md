![Ekran Resmi 2024-03-27 16 03 55](https://github.com/ecagataydogan/weather-api/assets/101594855/b93bc6e9-aa2e-4a8f-89d1-28d3cdd974ca)

## Open Weather Application

This application provides current weather reports for specified cities via `/v1/api/open-weather/{city}` endpoint.

### Aim of project

Preventing unnecessary requests from being sent to the weather stack api together with the in-memory database and cache

### Functionality

The application fetches the current weather report either from the database or the WeatherStackAPI with the provided API_KEY.

- If the latest data for the requested city is not older than 10 minutes in the database, the data is fetched from the database.
- If the city either does not exist in the database or the data is older than 30 minutes, a request is sent to the WeatherStackAPI, and the result is stored in the cache.
- If there is a cached value for the requested city, the response is returned directly from the cache.

### Technologies Used

- Java 17
- Spring Boot 3.0
- Spring Data JPA
- H2 In-Memory Database
- RESTful API
- Maven
- Docker

### Docker Run

To run the application using Docker, follow these steps:

```bash
$ cd weather-api
$ docker-compose up -d
```

### Maven RUN

```bash
$ cd weather-api
$ mvn clean install
$ mvn spring-boot:run
```


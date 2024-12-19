# Project: ReactiveSpringApp

## Description

This is a **Spring Boot** 🌱 project designed to work with **reactive web applications** ♻️ and supports two databases: **PostgreSQL** 🐘 and **Cassandra** 🗄️. Users can configure the application to use either database as per their requirements. The project leverages modern **Java** ☕ features and frameworks like **WebFlux** ♻️ and **R2DBC** for **reactive programming** ⚡.

---

## Features
- ⚡ **Reactive programming** with **WebFlux** ♻️.
- 📊 Support for **PostgreSQL** 🐘 and **Cassandra** 🗄️ databases.
- 🛠️ Built with **Java 21** ☕.
- 🖥️ Ready for native image compilation using **GraalVM**.

---

## Prerequisites

Before running the application, ensure you have the following installed:

- **Java Development Kit (JDK) 21** ☕ or higher.
- **PostgreSQL database** 🐘 (if using PostgreSQL).
- **Cassandra database** 🗄️ (if using Cassandra).
- **Docker** (optional, for containerized database setup).
- **Maven** or **Gradle** for dependency management.

---

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/Jaxongirshoh/reactivespringapp.git
cd reactivespringapp
```

### Build the Project

```bash
gradle build
```

---

## Database Configuration

### PostgreSQL Configuration 🐘

1. Update the `application.yml` or `application.properties` file:

```yaml
spring:
  r2dbc:
    url: r2dbc:postgresql://localhost:5432/your_database
    username: your_username
    password: your_password
```

2. Ensure the PostgreSQL database is running. You can use Docker to set up a PostgreSQL instance:

```bash
docker run --name postgres-db -e POSTGRES_USER=your_username -e POSTGRES_PASSWORD=your_password -e POSTGRES_DB=your_database -p 5432:5432 -d postgres
```

### Cassandra Configuration 🗄️

1. Update the `application.yml` or `application.properties` file:

```yaml
spring:
  cassandra:
    port: 9042
    contact-points: localhost
    keyspace-name: your_keyspace
    username: your_username
    password: your_password
```

2. Ensure the Cassandra database is running. You can use Docker to set up a Cassandra instance:

```bash
docker run --name cassandra-db -e CASSANDRA_KEYSPACE=your_keyspace -p 9042:9042 -d cassandra
```

---

## Running the Application

To run the application locally, use:

```bash
gradle bootRun
```

The application will start on `http://localhost:8080` by default.

---

## Testing

Run the test suite using:

```bash
gradle test
```

---

## Build Native Image

If you wish to build a native image using GraalVM:

```bash
gradle nativeCompile
```

---

## Contribution

Contributions are welcome! Please fork the repository and create a pull request for any changes or improvements.

---

## License

This project is licensed under the [MIT License](LICENSE).

---

## Contact

For questions or support, connect with me on [LinkedIn](https://www.linkedin.com/in/jakhongirkhudoyorov/)! 😊

Check out my projects on [GitHub](https://github.com/Jaxongirshoh)! 



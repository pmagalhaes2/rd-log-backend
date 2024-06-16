# RD Log API

Welcome to the RD Logistics API. This API provides various endpoints to manage and retrieve logistics information.

## Table of Contents

-   [Technologies](#-technologies)
-   [Dependencies](#%EF%B8%8F-dependencies)
-   [Installation and Usage](#-installation-and-usage)
-   [API Endpoints](#-api-endpoints)
-   [Environment Variables](#-environment-variables)
-   [Database](#database)
-   [Contributing](#contributing)
-   [License](#license)


## 🚀 Technologies

-   **Backend**: Java e Spring
-   **Database**: PostgreSQL

## ⚠️  Dependencies

Before you begin, you will need to have the following tools installed on your machine:

-   [Git](https://git-scm.com) (optional for cloning the repository)
-   [Java](https://www.java.com/pt-BR/) (required for running the application)
-   [Maven](https://maven.apache.org) (required for managing dependencies and building the project)
-   An editor like [IntelliJ](https://www.jetbrains.com/idea/) (optional, but recommended for development)


## 📥  Installation  and usage

```bash  
  # Clone this repository
  
$ git clone https://github.com/pmagalhaes2/rd-log-api


# Access the project folder in your terminal

$ cd rd-log-api


# Install dependencies with Maven

$ mvn clean install


# Run the application

$ mvn spring-boot:run


# The API will be accessible at http://localhost:8080/

```  

##  📖 API Endpoints

### Authentication

To access the API endpoints for GET, POST and PUT requests, no authentication is required. However, for DELETE requests, you need to authenticate using Basic Auth with the following credentials:

- Username: Admin
- Password: Admin@1.

The API provides the following endpoints:


### Logistic Companies

-   `GET /logistic-companies`: Retrieves a list of all logistic companies.
-   `GET /logistic-companies/{id}`: Retrieves a logistic company by ID.
-   `POST /logistic-companies`: Registers a new logistic company.
-   `POST /logistic-companies/login`: Login using the new logistic company registration.
-   `PUT /logistic-companies/{id}`: Updates a logistic company's information.
-   `DELETE /logistic-companies/{id}`: Deletes a logistic company.



Example request body for `POST /logistic-companies`:

```json
{
  "name": "John Doe",
  "cnpj": "11223334444555",
  "opening_hours": "08:30:00",
  "closing_hours": "18:00:00",
  "phone_number": "11970707070",
  "email": "email@example.com",
  "password": "P@ssW0rd",
  "price_km": 1.00,
  "address": {
    "value": "Rua da Liberdade",
    "number": 315,
    "city": "São Paulo",
    "state": "SP",
    "zipCode": "01045010"
  }
}
```


Example response for `GET /logistic-companies/{id}`:


```json
{
  "id": 1,
  "name": "John Doe",
  "cnpj": "11223334444555",
  "opening_hours": "08:30:00",
  "closing_hours": "18:00:00",
  "phone_number": "11970707070",
  "email": "email@example.com",
  "price_km": 1.00,
  "address": {
    "id": 1,
    "value": "Rua da Liberdade",
    "number": 315,
    "city": "São Paulo",
    "state": "SP",
    "zipCode": "01045010",
    "complement": ""
  }
}
```

### Delivery Details

-   `GET /delivery-details`: This endpoint retrieves the delivery details between two zip codes including the closest and cheapest logistics companies.

**Request Parameters:**

- `origins`: The origin zip code.
- `destinations`: The destination zip code.
- `key`: API key for authentication.

Example response for `GET /delivery-details?origins=12345678&destinations=87654321&key=your_api_key`:


```json
[
  {
    "logistic_id": 1,
    "logistic_name": "Logistic Company A",
    "distance": "10.5 km",
    "duration": "15 mins",
    "price_km": 20.5,
    "closest_company": true
  },
  {
    "logistic_id": 2,
    "logistic_name": "Logistic Company B",
    "distance": "12.0 km",
    "duration": "18 mins",
    "price_km": 18.0,
    "closest_company": false
  }
]
```

## Swagger Documentation
For detailed information on all available endpoints, please refer to the Swagger documentation. The Swagger UI provides an interactive interface to explore and test the API endpoints.

You can access the Swagger UI at the following URL:

http://localhost:8080/swagger-ui/index.html


## 🔧 Environment Variables

To run the application, you will need to configure the following environment variables:

-   `DB_URL`: URL of the database connection.
-   `DB_USERNAME`: Database username.
-   `DB_PASSWORD`: Database password.

Example configuration in a `.env` file:

```bash
DB_URL=jdbc:postgresql://localhost:5432/logistic
DB_USERNAME=postgres
DB_PASSWORD=password
```


## Database

The project uses PostgreSQL as the database. You have two options to configure the database: use AWS RDS or set up PostgreSQL locally.

### Using AWS RDS

Use the JDBC URL `jdbc:postgresql://rd-log.cvu8eiue4670.us-east-2.rds.amazonaws.com:5433/postgres` and the default username and password (`postgres`) to connect.

### Setting Up PostgreSQL Locally

If you prefer to set up PostgreSQL locally, follow these steps:

#### 1. Install PostgreSQL

Download and install PostgreSQL from the [official site](https://www.postgresql.org/download/windows/).


#### 2. Start the PostgreSQL Server

-   **Windows**: PostgreSQL usually starts automatically after installation. If not, start the service via pgAdmin or Command Prompt:

    `net start postgresql`

-   **MacOS** and **Linux**:

    `sudo service postgresql start`

#### 3. Set Up the Database

-   **Create a new database**:

    `sudo -u postgres psql
    CREATE DATABASE logistics;`

-   **Create a new user**:

    `CREATE USER yourusername WITH PASSWORD 'yourpassword';`

-   **Grant privileges to the user on the database**:

    `GRANT ALL PRIVILEGES ON DATABASE logistics TO yourusername;`


#### 4. Configure Environment Variables

Add the following environment variables to your `.env` file:

```bash
DB_URL=jdbc:postgresql://localhost:5432/logistics
DB_USERNAME=postgres
DB_PASSWORD=password
```

#### 5. Manage Database Migrations with Flyway

Flyway is used for managing database migrations. Migrations are applied automatically when the application starts. Ensure the Flyway configuration in `application.properties` or `application.yml` is correct and points to your local database:

```bash
spring.flyway.url=jdbc:postgresql://localhost:5432/logistics
spring.flyway.user=yourusername
spring.flyway.password=yourpassword 
```

### Verifying the Configuration

After setting up PostgreSQL locally and adjusting the environment variables, you can verify if the application is correctly connecting to the local database by running it:

`mvn spring-boot:run`

The API will be accessible at `http://localhost:8080/` and connected to your local PostgreSQL database.

## Contributing

If you would like to contribute to this project, please follow these steps:

1.  Fork the repository.
2.  Create a new branch with your feature or fix: `git checkout -b my-feature`.
3.  Commit your changes: `git commit -m 'Add some feature'`.
4.  Push to the branch: `git push origin my-feature`.
5.  Open a pull request.

Please make sure to update tests as appropriate.

## License

This project is licensed under the MIT License. See the LICENSE file for details.

  ---

Made by [Patricia Magalhães](https://github.com/pmagalhaes2), [Cristina Giardini](https://github.com/cristina-giardini), [Thamires Barbosa](https://github.com/thamirescandidabarbosa), [Katherine Uchoas](https://github.com/katherineuchoas) e [Sophia Contesini](https://github.com/sophiacontesini) 💙
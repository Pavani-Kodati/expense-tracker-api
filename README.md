# Expense Tracker REST API

A RESTful API built with **Java 17**, **Spring Boot 3**, and **H2 (in-memory SQL database)** for managing personal expenses by category, date, and amount.

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Language | Java 17 |
| Framework | Spring Boot 3.2 |
| Database | H2 (in-memory) / easily swappable to PostgreSQL/MySQL |
| ORM | Spring Data JPA / Hibernate |
| Testing | JUnit 5 + Mockito |
| API Docs | Swagger / OpenAPI 3 |
| Build | Maven |

## Features

- Full **CRUD** operations for expenses
- Filter expenses by **category** or **date range**
- Get **total spending** per category
- Input **validation** with descriptive error messages
- **Swagger UI** for interactive API testing
- **H2 Console** for viewing the database in browser
- Pre-loaded sample data on startup

## Getting Started

### Prerequisites
- Java 17+
- Maven 3.6+

### Run the Application

```bash
# Clone the repo
git clone https://github.com/YOUR_USERNAME/expense-tracker.git
cd expense-tracker

# Run
mvn spring-boot:run
```

The app starts at `http://localhost:8080`

### Useful URLs

| URL | Description |
|-----|-------------|
| `http://localhost:8080/swagger-ui.html` | Interactive API docs |
| `http://localhost:8080/h2-console` | H2 database console |
| `http://localhost:8080/api/expenses` | Main API endpoint |

> H2 Console settings: JDBC URL = `jdbc:h2:mem:expensedb`, Username = `sa`, Password = *(leave blank)*

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/expenses` | Get all expenses |
| GET | `/api/expenses/{id}` | Get expense by ID |
| POST | `/api/expenses` | Create new expense |
| PUT | `/api/expenses/{id}` | Update expense |
| DELETE | `/api/expenses/{id}` | Delete expense |
| GET | `/api/expenses/category/{category}` | Filter by category |
| GET | `/api/expenses/date-range?startDate=&endDate=` | Filter by date range |
| GET | `/api/expenses/category/{category}/total` | Total spend by category |
| GET | `/api/expenses/categories` | List all categories |

## Sample Request

```bash
# Create an expense
curl -X POST http://localhost:8080/api/expenses \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Grocery Run",
    "amount": 95.50,
    "category": "Food",
    "date": "2026-02-12",
    "description": "Weekly groceries"
  }'
```

## Running Tests

```bash
mvn test
```

## Project Structure

```
src/
├── main/java/com/pavani/expensetracker/
│   ├── controller/    # REST controllers
│   ├── model/         # JPA entities
│   ├── repository/    # Spring Data repositories
│   ├── service/       # Business logic
│   └── DataLoader.java
└── test/              # JUnit + Mockito tests
```
## API Demonstration

### 1. Server Initialization
When the application starts, it successfully initializes the OpenJDK runtime and sets up the local network server.

### 2. Testing with Postman
I used Postman to verify the REST endpoints. The following screenshots show the API returning a `200 OK` status and the JSON data for the expenses, including newly added items.

![Postman GET Request](image_6c7b3e.jpg)

![Postman API Verification](image_6cd139.jpg)

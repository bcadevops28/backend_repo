# Court Case Management System - Setup Guide

## Overview
This is a Spring Boot application for managing court cases with a modern web interface.

## Technology Stack
- **Backend**: Spring Boot 4.0.2, Java 17, JPA/Hibernate
- **Database**: H2 In-Memory Database
- **Frontend**: HTML5, CSS3, Vanilla JavaScript
- **Build Tool**: Maven Wrapper (mvnw)

## Architecture

### Backend Components

#### 1. **Model Layer** (`src/main/java/com/example/demo/model/`)
- **CourtCase.java**: Main entity with fields:
  - `id` (Long) - Primary key
  - `caseNumber` (String) - Unique identifier
  - `caseType` (CaseType Enum) - CRIMINAL or CIVIL
  - `status` (CaseStatus Enum) - PENDING, IN_PROGRESS, or CLOSED
  - `description` (String) - Case details
  - `defendantName` (String) - Required field
  - `affendantName` (String) - Required field

- **CaseType.java**: Enum with values CRIMINAL, CIVIL
- **CaseStatus.java**: Enum with values PENDING, IN_PROGRESS, CLOSED

#### 2. **Repository Layer** (`src/main/java/com/example/demo/repository/`)
- **CourtCaseRepository.java**: JPA Repository for database operations
  - `findByCaseNumber(String)`: Find case by case number
  - Standard CRUD operations inherited from JpaRepository

#### 3. **Service Layer** (`src/main/java/com/example/demo/service/`)
- **CourtCaseService.java**: Business logic for court cases
  - `createCase()`: Create new case with validation
  - `updateCase()`: Update existing case (handles all fields)
  - `deleteCase()`: Delete case by ID
  - `getAllCases()`: Retrieve all cases
  - `getCaseById()`: Get case by ID
  - `getCaseByCaseNumber()`: Get case by case number

#### 4. **Controller Layer** (`src/main/java/com/example/demo/controller/`)
- **CourtCaseController.java**: REST API endpoints
  - `POST /api/court-cases`: Create new case (with validation)
  - `GET /api/court-cases`: Get all cases
  - `GET /api/court-cases/{id}`: Get case by ID
  - `GET /api/court-cases/number/{caseNumber}`: Get case by case number
  - `PUT /api/court-cases/{id}`: Update case
  - `DELETE /api/court-cases/{id}`: Delete case
  - `DELETE /api/court-cases`: Delete all cases

### Frontend Components

#### 1. **index.html** (`src/main/resources/static/`)
- Add new case form with all required fields
- Filter and search functionality
- Statistics dashboard (Total, Pending, In Progress, Closed)
- Edit and delete modals

#### 2. **script.js** (`src/main/resources/static/`)
- `loadCases()`: Fetch and display all cases
- `handleAddCase()`: Submit new case with validation
- `handleEditCase()`: Update existing case
- `deleteCase()`: Delete case
- `applyFilters()`: Filter by status and type
- `displayCases()`: Render cases list
- `updateStats()`: Update statistics

#### 3. **style.css** (`src/main/resources/static/`)
- Responsive design
- Color-coded status badges
- Form styling
- Modal dialogs

## Database Configuration

**File**: `src/main/resources/application.properties`

```properties
# H2 Database Configuration
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# H2 Console
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# JPA/Hibernate
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

**Access H2 Console**: http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:mem:testdb
- Username: sa
- Password: (empty)

## Running the Application

### Prerequisites
- Java 17 or higher
- Maven Wrapper (included in project)

### Start the Server
```bash
cd c:\Users\ASUS\vetri23suca28\demo
.\mvnw spring-boot:run
```

### Access the Application
- **Web Application**: http://localhost:8080
- **API Base URL**: http://localhost:8080/api/court-cases
- **H2 Console**: http://localhost:8080/h2-console

## API Endpoints

### Create Court Case
```
POST /api/court-cases
Content-Type: application/json

{
  "caseNumber": "CASE-2026-001",
  "caseType": "CRIMINAL",
  "status": "PENDING",
  "description": "Case description here",
  "defendantName": "John Doe",
  "affendantName": "Jane Smith"
}
```

**Required Fields**: caseNumber, caseType, status, defendantName, affendantName
**Optional Fields**: description

### Get All Cases
```
GET /api/court-cases
```

### Get Case by ID
```
GET /api/court-cases/{id}
```

### Get Case by Case Number
```
GET /api/court-cases/number/{caseNumber}
```

### Update Case
```
PUT /api/court-cases/{id}
Content-Type: application/json

{
  "caseNumber": "CASE-2026-001",
  "caseType": "CRIMINAL",
  "status": "IN_PROGRESS",
  "description": "Updated description",
  "defendantName": "John Doe",
  "affendantName": "Jane Smith"
}
```

### Delete Case
```
DELETE /api/court-cases/{id}
```

### Delete All Cases
```
DELETE /api/court-cases
```

## Error Handling

### Common Errors and Solutions

**1. "Case number is required"**
- Solution: Ensure the Case Number field is filled in the form

**2. "Case type is required"**
- Solution: Select a Case Type (Criminal or Civil) from the dropdown

**3. "Status is required"**
- Solution: Select a Status (Pending, In Progress, or Closed) from the dropdown

**4. "Defendant name is required"**
- Solution: Fill in the Defendant Name field

**5. "Affendant name is required"**
- Solution: Fill in the Affendant Name field

**6. Failed to load cases / Connection refused**
- Solution: 
  1. Check if the server is running: `.\mvnw spring-boot:run`
  2. Verify the API URL is correct: http://localhost:8080/api/court-cases
  3. Check browser console (F12) for detailed error messages

## Build and Deployment

### Clean Build
```bash
.\mvnw clean package -DskipTests
```

### Run Tests
```bash
.\mvnw test
```

### Package as JAR
```bash
.\mvnw package
```

The JAR file will be created at: `target/demo-0.0.1-SNAPSHOT.jar`

### Run JAR file
```bash
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

## Development Workflow

1. **Make Changes**: Edit Java or JavaScript files
2. **Rebuild**: `.\mvnw clean package -DskipTests`
3. **Stop Old Server**: `taskkill /F /IM java.exe`
4. **Start New Server**: `.\mvnw spring-boot:run`
5. **Test**: Open http://localhost:8080 and test functionality

## Features

✅ **Add Cases**: Fill form with required information
✅ **View Cases**: Display all cases with statistics
✅ **Search Cases**: Find by case number or description
✅ **Filter Cases**: Filter by status and case type
✅ **Edit Cases**: Update case information
✅ **Delete Cases**: Remove single or all cases
✅ **Statistics**: Real-time count of total, pending, in-progress, and closed cases
✅ **Responsive Design**: Works on desktop and mobile devices
✅ **Database Console**: H2 Console for direct database access

## Troubleshooting

### Port Already in Use
If port 8080 is already in use:
```bash
# Change port in application.properties
server.port=8081
```

### Database Issues
- Database is in-memory (H2), data is lost when application stops
- To persist data, change to MySQL or PostgreSQL

### CORS Issues
- CORS is enabled for all origins in the controller
- If still having issues, verify Origin header in browser requests

## Project Structure
```
demo/
├── src/
│   ├── main/
│   │   ├── java/com/example/demo/
│   │   │   ├── config/
│   │   │   │   └── CorsConfig.java
│   │   │   ├── controller/
│   │   │   │   └── CourtCaseController.java
│   │   │   ├── model/
│   │   │   │   ├── CourtCase.java
│   │   │   │   ├── CaseType.java
│   │   │   │   └── CaseStatus.java
│   │   │   ├── repository/
│   │   │   │   └── CourtCaseRepository.java
│   │   │   ├── service/
│   │   │   │   └── CourtCaseService.java
│   │   │   └── DemoApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/
│   │           ├── index.html
│   │           ├── script.js
│   │           └── style.css
│   └── test/
│       └── java/com/example/demo/
│           └── DemoApplicationTests.java
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

## Next Steps

1. **Persistent Database**: Configure PostgreSQL or MySQL instead of H2
2. **Authentication**: Add Spring Security for user login
3. **Logging**: Integrate SLF4J with Log4j2 for file-based logging
4. **Testing**: Add unit and integration tests
5. **Deployment**: Deploy to Azure App Service or Docker Container

## Support

For issues or questions, check:
- Browser Developer Console (F12) for JavaScript errors
- Application logs in the terminal running the server
- H2 Console at http://localhost:8080/h2-console to verify database state

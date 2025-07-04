# Machine Time Display Application

A Spring Boot web application that displays the current machine time with real-time updates.

## Features

- **Server Time Display**: Shows the current server time when the page loads
- **Live Time Updates**: Real-time clock that updates every second using JavaScript
- **Time Zone Information**: Displays the system's default time zone
- **Unix Timestamp**: Shows the current Unix epoch timestamp
- **Responsive Design**: Clean, modern UI with Bootstrap styling

## Technologies Used

- Spring Boot 3.0.6
- Java 17
- Thymeleaf templating
- Bootstrap for UI
- H2 Database
- MyBatis

## Running the Application

1. Clone this repository
2. Make sure you have Java 17 installed
3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
4. Open your browser and navigate to `http://localhost:8080`

## Time Display Features

The application shows:
- **Current Server Time**: The time when the page was loaded from the server
- **Live Client Time**: A real-time clock that updates every second
- **Time Zone**: The system's default time zone
- **Unix Timestamp**: Current time in milliseconds since epoch

The time display includes both server-side (Java) and client-side (JavaScript) time information for comparison.


- **UI Layer**: Handles interaction with the user  
- **Service Layer**: Contains business logic  
- **DAO Layer**: Handles database queries  
- **Database**: MySQL backend  

---

## Security

- Role-based access control (HR Admin vs Employee)
- Employees can only view their own data
- HR Admin has full CRUD access
- Password hashing is used for authentication

---

## Setup Instructions

1. Create a MySQL database
2. Run `schema.sql`
3. Run `seed.sql`
4. Add MySQL connector JAR to `lib/`
5. Configure database connection in `DBConnection.java`
6. Compile and run `Main.java`

---

## Notes

- No frameworks are used (per assignment requirements)
- All functionality is implemented using core Java and MySQL
- Designed to scale as the company grows

---





---

## Folder Descriptions

### `database/`
Contains all SQL scripts related to the database:
- `schema.sql` → Creates tables and defines relationships
- `seed.sql` → Inserts initial/sample data for testing

---

### `src/main/`

Main source code of the application.

#### `Main.java`
- Entry point of the application
- Starts the program and initializes the UI

---

### `model/`
Represents the core data structures (entities) of the system:
- `Employee` → Employee personal and job information
- `Payroll` → Salary and pay history
- `Division` → Organizational divisions
- `JobTitle` → Employee roles/titles
- `User` → Login credentials and user info
- `Role` → Defines access levels (HR Admin vs Employee)

---

### `dao/` (Data Access Layer)
Handles all database interactions:
- Executes SQL queries
- Performs CRUD operations
- Communicates directly with MySQL

Examples:
- `EmployeeDAO` → Fetch/update employee data
- `PayrollDAO` → Retrieve payroll records
- `ReportDAO` → Generate report queries

---

### `service/` (Business Logic Layer)
Contains application logic and rules:
- Validates data
- Applies business rules (e.g., salary updates by percentage ranges)
- Coordinates between UI and DAO layers

Examples:
- `LoginService` → Handles authentication
- `EmployeeService` → Employee-related logic
- `ReportService` → Report generation logic
- `AuthorizationService` → Role-based access control

---

### `ui/` (User Interface Layer)
Handles user interaction (console, Swing, or JavaFX):
- Displays menus and screens
- Collects user input
- Calls service layer methods

Examples:
- `LoginUI` → Login screen
- `HRAdminMenuUI` → Admin functionality menu
- `EmployeeMenuUI` → Employee view menu
- `ReportsUI` → Displays reports

---

### `util/`
Utility/helper classes:
- `DBConnection` → Manages database connections
- `InputValidator` → Validates user input
- `PasswordHasher` → Secures passwords
- `DateUtils` → Date formatting and handling

---

### `lib/`
Contains external dependencies:
- MySQL Connector JAR for database connectivity

---

## Architecture Overview

This project follows a layered architecture:
   

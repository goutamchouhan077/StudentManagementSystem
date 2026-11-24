# Student Management System – Medicaps University

A web-based college Student Management System based on the supplied SRS and presentation. The application uses HTML/CSS/JavaScript, Java 21, Spring Boot, Spring Data JPA and MySQL.

## Implemented modules

- Session-based login/logout with BCrypt password hashing and role checks on API routes.
- Administrator, Teacher and Student role views.
- Student CRUD, unique Student ID and search by ID/name.
- Teacher, course and subject records.
- Daily attendance entry and student attendance history/percentage.
- Marks entry with range validation and configurable maximum marks; grade calculation uses a default scale (A+ >=90, A >=80, B >=70, C >=60, D >=50, otherwise F).
- Fee records with pending amount calculated as total minus paid.
- Dashboard summary and attendance report.
- Responsive HTML/CSS/JavaScript interface.

## Requirements

- Java 21+
- Maven 3.9+
- MySQL 8+

## Run locally

1. Create a MySQL database (or allow the configured URL to create it):
   ```sql
   CREATE DATABASE student_management_system;
   ```
2. Set environment variables, or edit `src/main/resources/application.properties`:
   - `DB_URL` (default `jdbc:mysql://localhost:3306/student_management_system?...`)
   - `DB_USERNAME` (default `root`)
   - `DB_PASSWORD` (default empty)
3. From the project root run:
   ```bash
   mvn spring-boot:run
   ```
4. Open `http://localhost:8080`.

On first start, the application creates an administrator account:

- Administrator: `admin` / `Admin@123`
- Teacher: `teacher` / `Teacher@123`
- Student: `STU001` / `Student@123`

These are demonstration credentials and must be changed or removed before exposing the application to a network. Demo accounts are seeded only when the users table is empty. A sample student, teacher, course, subject, attendance entry, mark and fee record are also inserted into empty tables.

## Creating user accounts

The administrator-only endpoint is `POST /api/auth/users`:

```json
{
  "username": "TEACHER001",
  "name": "Demo Teacher",
  "email": "teacher@example.com",
  "password": "ChangeThisPassword!",
  "role": "TEACHER"
}
```

For a student account, set `username` to the student's exact `studentId` and `role` to `STUDENT`. The student portal uses this username to load that student's records. For a teacher, use role `TEACHER`. Allowed roles are `ADMIN`, `TEACHER`, and `STUDENT`.

## API overview

| Method | Endpoint | Access | Purpose |
|---|---|---|---|
| POST | `/api/auth/login` | Public | Login |
| POST | `/api/auth/logout` | Authenticated | Logout |
| GET | `/api/auth/me` | Authenticated | Current session |
| POST | `/api/auth/users` | Admin | Create login account |
| GET/POST | `/api/students` | Admin/Teacher (GET), Admin (POST) | Search/list and create students |
| PUT/DELETE | `/api/students/{id}` | Admin | Update/delete student |
| GET/POST | `/api/teachers` | Admin/Teacher (GET), Admin (POST) | Teacher records |
| GET/POST | `/api/courses` | Authenticated (GET), Admin (POST) | Courses |
| GET/POST | `/api/subjects` | Authenticated (GET), Admin (POST) | Subjects |
| GET/POST | `/api/attendance` | Authenticated (GET), Admin/Teacher (POST) | Attendance |
| GET/POST | `/api/marks` | Authenticated (GET), Admin/Teacher (POST) | Marks and grades |
| GET/POST | `/api/fees` | Authenticated (GET), Admin (POST) | Fee records |
| GET | `/api/reports/summary` | Admin/Teacher | Summary report |

All endpoints use the same-origin browser session. API permissions are checked server-side; hiding a navigation item is not the security boundary.

## Database

Hibernate creates/updates tables from the entity definitions (`spring.jpa.hibernate.ddl-auto=update`). The initial entity model includes users, students, teachers, courses, subjects, attendance, marks and fees. An illustrative SQL schema is provided in `database/schema.sql`.

## Notes and remaining production hardening

This is a runnable academic project baseline, not a security-audited production service. Before real institutional use:

- Add CSRF protection suitable for session authentication, HTTPS, secure cookie settings and strict CORS policy if frontend/backend are split.
- Add password reset, account lifecycle management, audit logs, rate limiting and stronger password policy.
- Add pagination, export formats, backup/restore automation and integration tests.
- Review institutional grading rules, attendance policy, teacher-course assignments and the final permissions matrix with the college.
- Add a migration tool such as Flyway before production schema changes.
- Configure a strong external database password and replace the demo administrator password.

## Project layout

```text
src/main/java/com/medicaps/sms/
  SmsApplication.java
  controller/ApiController.java
  model/                 JPA entities
  repository/            Spring Data repositories
src/main/resources/
  application.properties
  static/                HTML, CSS, JavaScript frontend
 database/schema.sql
 docs/diagrams.md
```

## Frontend preview (no backend required)

Open `preview.html` directly in Chrome or Edge to see and interact with the UI using sample data. This is a standalone visual/demo preview; changes made there stay in the page memory and are not saved to MySQL.

## Run the connected application

Do **not** open `pom.xml` in a browser. It is Maven's XML build configuration, not a web page. Open the project folder in VS Code/IntelliJ and run the Spring Boot application with `mvn spring-boot:run`. Then browse to `http://localhost:8080`; Spring Boot serves the connected frontend from `src/main/resources/static/index.html` and its API-backed JavaScript.

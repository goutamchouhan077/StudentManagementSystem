# System diagrams (implementation-aligned)

These Mermaid diagrams restate the workflows shown in the supplied presentation. They are source diagrams for documentation and can be rendered in Mermaid-compatible Markdown viewers.

## Use case diagram

```mermaid
flowchart LR
 Student([Student])
 Teacher([Teacher])
 Admin([Administrator])
 subgraph SMS[Student Management System]
  Auth((Register / Login))
  Stu((Manage Students))
  Att((Manage Attendance))
  Marks((Manage Marks / Grades))
  Fees((Manage Fees))
  Profile((View Profile))
  ViewAtt((View Attendance))
  ViewMarks((View Marks / Grades))
  Reports((Dashboards / Reports))
 end
 Student --- Auth
 Student --- Profile
 Student --- ViewAtt
 Student --- ViewMarks
 Teacher --- Auth
 Teacher --- Att
 Teacher --- Marks
 Teacher --- Reports
 Admin --- Auth
 Admin --- Stu
 Admin --- Att
 Admin --- Marks
 Admin --- Fees
 Admin --- Reports
```

## Activity diagram

```mermaid
flowchart TD
 Start((Start)) --> Login[Open system and enter ID / password]
 Login --> Valid{Credentials valid?}
 Valid -- No --> Error[Show error message] --> Login
 Valid -- Yes --> Role{Identify user role}
 Role -->|Student| Student[View profile, attendance, marks and fees]
 Role -->|Teacher| Teacher[Mark attendance and enter marks]
 Role -->|Administrator| Admin[Manage students, fees and reports]
 Student --> Validate[Validate input / request]
 Teacher --> Validate
 Admin --> Validate
 Validate --> DB[(Save or retrieve database records)]
 DB --> Result[Display result or report]
 Result --> Logout[Logout]
 Logout --> End((End))
```

## Swimlane sequence

```mermaid
sequenceDiagram
 participant U as User
 participant W as Web Application
 participant S as Server / Business Logic
 participant D as Database
 U->>W: Enter ID and password
 W->>S: Send login request
 S->>D: Check user record
 D-->>S: User record / role
 S-->>W: Authentication result
 W-->>U: Role-based dashboard or error
 U->>W: Select task
 W->>S: Send task request
 S->>S: Validate input and authorization
 S->>D: Read or save records
 D-->>S: Result
 S-->>W: Response
 W-->>U: Display result / report
 U->>W: Logout
```

## DFD Level 0 – Context diagram

```mermaid
flowchart LR
 Student[Student] -->|Login and view requests| SMS((0: Student Management System))
 SMS -->|Profile, attendance, marks, fees| Student
 Teacher[Teacher] -->|Attendance and marks| SMS
 SMS -->|Student information and results| Teacher
 Admin[Administrator] -->|Student and fee updates| SMS
 SMS -->|Reports and dashboards| Admin
```

## DFD Level 1

```mermaid
flowchart TB
 Users[All Users] -->|Login details| P1((1.0 Register / Login))
 P1 -->|Credentials / session| D1[(D1 User Accounts)]
 Admin[Administrator] -->|Student details| P2((2.0 Manage Students))
 P2 <--> D2[(D2 Student Records)]
 Admin -->|Fee details| P3((3.0 Manage Fees))
 P3 <--> D3[(D3 Fee Records)]
 Teacher[Teacher] -->|Attendance| P4((4.0 Manage Attendance))
 P4 <--> D4[(D4 Attendance)]
 Teacher -->|Marks / grades| P5((5.0 Manage Marks))
 P5 <--> D5[(D5 Marks / Grades)]
 Student[Student] -->|View request| P6((6.0 View Records & Reports))
 D2 --> P6
 D3 --> P6
 D4 --> P6
 D5 --> P6
 P6 -->|Records and reports| Student
```

## Architecture

```mermaid
flowchart LR
 U[User] --> B[Browser]
 B --> F[HTML / CSS / JavaScript]
 F --> API[Spring Boot REST API]
 API --> S[Service and validation logic]
 S --> R[Spring Data JPA repositories]
 R --> DB[(MySQL Database)]
```

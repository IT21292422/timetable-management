# University Timetable Management System

The University Timetable Management System is a RESTful API built with Spring Boot to manage class schedules for students, faculty, and administrative staff. It provides functionalities for course management, session scheduling, user roles, authentication, notifications, and more.

## Setup Instructions

### Prerequisites
- JDK (Java Development Kit) version 18 or higher
- Maven
- MongoDB

### Steps
1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd university-timetable-management-system
2. **Configure MongoDB:**
- Install MongoDB and start the MongoDB server.
- Update the MongoDB connection details in the application.properties file.

## API Endpoint Documentation
### Course Management
- Create Course: POST /api/courses
- Get All Courses: GET /api/courses
- Get Course by ID: GET /api/courses/{courseId}
- Update Course: PUT /api/courses/{courseId}
- Delete Course: DELETE /api/courses/{courseId}
### Session Management
- Create Session: POST /api/sessions
- Get All Sessions: GET /api/sessions
- Get Session by ID: GET /api/sessions/{sessionId}
- Update Session: PUT /api/sessions/{sessionId}
- Delete Session: DELETE /api/sessions/{sessionId}
### Auth Management
- Register User: POST /api/auth/register
- Login: POST /api/auth/login


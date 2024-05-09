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
   cd timetable-management
2. **Configure MongoDB:**
- Configure database in MongoDB Atlas.
- Update the MongoDB connection string details in the .env file.

## API Endpoint Documentation
[https://documenter.getpostman.com/view/32400012/2sA3Bj9Zep](https://documenter.getpostman.com/view/32400012/2sA3Bj9Zep)
### Auth Management
- Register User: POST /api/auth/register
- Login: POST /api/auth/login
### Booking Management
- Create Booking: POST /api/booking
- Get All Booking: GET /api/booking
- Get Booking by ID: GET /api/booking/{bookingId}
- BookRoom: POST /api/booking/rooms/{roomId}
- BookResource: POST /api/booking/resources/{resourceId}
- Update Booking: PUT /api/booking/{bookingId}
- Delete Booking: DELETE /api/booking/{bookingId}
### Course Management
- Create Course: POST /api/createCourse
- Get All Courses: GET /api/course
- Get Course by ID: GET /api/course/{courseId}
- Update Course: PUT /api/updateCourse/{courseId}
- Assign Session To Course: PUT /api/course/assignSession/{courseId}/sessions
- Update Session In Course: PUT /api/course/updateSession/{courseId}/sessions
- Assign Faculty To Course: PUT /api/course/assignFaculty/{courseId}/faculty/{facultyId}
- Delete Course: DELETE /api/course/deleteCourse/{courseId}
- Remove Session From Course: DELETE /api/course/removeSession/{courseId}/sessions/{sessionId}
### Enrollment Management
- Get Enrolled Courses By Student: GET /api/enrollment/getStudent/{studentId}
- Get Enrolled Courses By Course: GET /api/enrollment/getCourse/{courseId}
- Update Enrollment: PUT /api/enrollment/update/{enrollmentId}
- Delete Enrollment: DELETE /api/enrollment/delete/{enrollmentId}
### Faculty Management
- Create Faculty: POST /api/faculty/createFaculty
- Get All Faculty: GET /api/faculty
- Get Faculty by ID: GET /api/faculty/{facultyId}
- Update Faculty: PUT /api/faculty/{facultyId}
- Delete Faculty: DELETE /api/faculty/{facultyId}
### Notification Management
- Create Notification: POST /api/notification/create
- Get All Notification: GET /api/notification
- Get Notification by ID: GET /api/notification/{notificationId}
- Update Notification: PUT /api/notification/update/{notificationId}
- Delete Notification: DELETE /api/notification/delete/{notificationId}
### Resource Management
- Create Resource: POST /api/resource/createResource
- Get All Resource: GET /api/resource
- Get Resource by ID: GET /api/resource/{resourceId}
- Update Resource: PUT /api/resource/updateResource/{resourceId}
- Delete Resource: DELETE /api/resource/deleteResource/{resourceId}
### Role Management
- Create Role: POST /api/role/createRole
- Get All Role: GET /api/role
- Get Role by ID: GET /api/role/{roleId}
- Update Role: PUT /api/role/updateRole/{roleId}
- Delete Role: DELETE /api/role/deleteRole/{roleId}
### Room Management
- Create Room: POST /api/room/createRoom
- Get All Room: GET /api/room
- Get Room by ID: GET /api/room/{roomId}
- Update Room: PUT /api/room/updateRoom/{roomId}
- Delete Room: DELETE /api/room/deleteRoom/{roomId}
### Session Management
- Create Session: POST /api/session/createSession
- Get All Sessions: GET /api/session
- Get Session by ID: GET /api/session/{sessionId}
- Update Session: PUT /api/session/updateSession/{sessionId}
- Delete Session: DELETE /api/session/deleteSession/{sessionId}
### Student Management
- Enroll In Course: POST /api/student/{studentId}/enroll/{courseCode}
- Get Enrolled Courses: GET /api/student/getEnroll/{studentId}/courses
### Timetable Management
- Create Timetable: POST /api/timetable/createTimetable
- Get All Timetable: GET /api/timetable
- Get Timetable by ID: GET /api/timetable/{timetableId}
- Update Timetable: PUT /api/timetable/updateTimetable/{timetableId}
- Assign Course To Timetable: PUT /api/timetable/assignCourse/{timetableId}/courses/{courseId}
- Update Course In Timetable: PUT /api/timetable/{timetableId}/courses
- Remove Course From Timetable: DELETE /api/timetable/removeCourse/{timetableId}/courses/{courseId}
- Delete Timetable: DELETE /api/timetable/deleteTimetable/{timetableId}

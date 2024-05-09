package com.web.timetable.timetablemanagement;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.web.timetable.timetablemanagement.model.Course;
import com.web.timetable.timetablemanagement.model.Enrollment;
import com.web.timetable.timetablemanagement.model.Student;
import com.web.timetable.timetablemanagement.repository.EnrollmentRepository;
import com.web.timetable.timetablemanagement.repository.StudentRepository;
import com.web.timetable.timetablemanagement.service.CourseService;
import com.web.timetable.timetablemanagement.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepo;

    @Mock
    private EnrollmentRepository enrollmentRepo;

    @Mock
    private CourseService courseService;

    @InjectMocks
    private StudentService studentService;

    @Test
    public void testEnrollInCourse() {
        // Define the test data
        String studentId = "S001";
        String courseId = "C001";
        Student student = new Student();
        student.setId(studentId);
        Course course = new Course();
        course.setId(courseId);
        Enrollment enrollment = new Enrollment("E001", studentId, courseId, LocalDateTime.now());

        // Mock the behavior of the repositories and service
        when(studentRepo.findById(studentId)).thenReturn(Optional.of(student));
        when(courseService.getCourseById(courseId)).thenReturn(Optional.of(course));
        when(enrollmentRepo.save(any(Enrollment.class))).thenReturn(enrollment);
        when(studentRepo.save(any(Student.class))).thenReturn(student);

        // Call the method under test
        Student result = studentService.enrollInCourse(studentId, courseId);

        // Verify the result
        assertEquals(studentId, result.getId());
        assertEquals(courseId, result.getEnrolledCourses().get(0));
    }

    @Test
    public void testGetEnrolledCoursesByStudent() {
        // Arrange
        String studentId = "123456";
        List<Enrollment> enrollments = new ArrayList<>();
        enrollments.add(new Enrollment("1", studentId, "SE3050", LocalDateTime.now()));
        enrollments.add(new Enrollment("2", studentId, "SE3060", LocalDateTime.now()));

        // Mocking behavior of enrollRepo
        when(enrollmentRepo.findByStudentId(studentId)).thenReturn(enrollments);

        // Act
        List<Enrollment> result = studentService.getEnrolledCoursesByStudent(studentId);

        // Assert
        assertNotNull(result);
        assertEquals(enrollments.size(), result.size());
    }

}

package com.web.timetable.timetablemanagement;

import static org.junit.Assert.assertEquals;
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
        Student student = new Student("S001", "John Doe");
        Course course = new Course("C001", "Math", "Mathematics course", 3);
        Enrollment enrollment = new Enrollment("E001", studentId, courseId, LocalDateTime.now());

        // Mock the behavior of the repositories and service
        when(studentRepo.findById(studentId)).thenReturn(Optional.of(student));
        when(courseService.getCourseById(courseId)).thenReturn(Optional.of(course));
        when(enrollmentRepo.findByStudentIdAndCourseId(studentId, courseId)).thenReturn(new ArrayList<>());
        when(enrollmentRepo.save(any(Enrollment.class))).thenReturn(enrollment);
        when(studentRepo.save(any(Student.class))).thenReturn(student);

        // Call the method under test
        Student result = studentService.enrollInCourse(studentId, courseId);

        // Verify the result
        assertEquals(studentId, result.getId());
        assertEquals(courseId, result.getEnrolledCourses().get(0));
    }

    @Test(expected = NoSuchElementException.class)
    public void testEnrollInCourse_StudentNotFound() {
        // Define the test data
        String studentId = "S001";
        String courseId = "C001";

        // Mock the behavior of the repositories and service
        when(studentRepo.findById(studentId)).thenReturn(Optional.empty());

        // Call the method under test
        studentService.enrollInCourse(studentId, courseId);
    }

}

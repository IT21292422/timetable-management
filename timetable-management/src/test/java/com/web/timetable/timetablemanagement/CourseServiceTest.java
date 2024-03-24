package com.web.timetable.timetablemanagement;

import com.web.timetable.timetablemanagement.model.Course;
import com.web.timetable.timetablemanagement.repository.CourseRepository;
import com.web.timetable.timetablemanagement.repository.FacultyRepository;
import com.web.timetable.timetablemanagement.service.CourseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
    @Mock
    private CourseRepository courseRepo;

    @Mock
    private FacultyRepository facultyRepo;

    @InjectMocks
    private CourseService courseService;

    @Test
    public void testCreateCourse() {
        Course course = new Course();
        when(courseRepo.save(course)).thenReturn(course);
        Course createdCourse = courseService.createCourse(course);
        assertEquals(course, createdCourse);
    }

    @Test
    public void testGetAllCourses() {
        List<Course> courses = new ArrayList<>();
        when(courseRepo.findAll()).thenReturn(courses);
        List<Course> retrievedCourses = courseService.getAllCourses();
        assertEquals(courses, retrievedCourses);
    }

    @Test
    public void testGetCourseById() {
        String courseId = "SE3050";
        Course course = new Course();
        when(courseRepo.findCourseByCode(courseId)).thenReturn(Optional.of(course));
        Optional<Course> retrievedCourse = courseService.getCourseById(courseId);
        assertTrue(retrievedCourse.isPresent());
        assertEquals(course, retrievedCourse.get());
    }

    @Test
    public void testUpdateCourse() {
//        String courseId = "C001";
//        Course updatedCourse = new Course();
//        updatedCourse.setCode("C002");
//
//        // Mock behavior for findById and save methods
//        when(courseRepo.findById(courseId)).thenReturn(Optional.of(new Course()));
//        when(courseRepo.save(any(Course.class))).thenAnswer(invocation -> {
//            Course savedCourse = invocation.getArgument(0);
//            return savedCourse;
//        });
//        Course result = courseService.updateCourse(courseId, updatedCourse);
//        assertEquals(updatedCourse.getCode(), result.getCode());
    }

    @Test
    public void testDeleteCourse() {
        String courseId = "SE3050";

        // Mock behavior for findById and deleteById methods
        when(courseRepo.findById(courseId)).thenReturn(Optional.of(new Course()));

        courseService.deleteCourse(courseId);
    }
}

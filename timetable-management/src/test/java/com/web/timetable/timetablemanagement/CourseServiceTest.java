package com.web.timetable.timetablemanagement;

import com.web.timetable.timetablemanagement.model.Course;
import com.web.timetable.timetablemanagement.repository.CourseRepository;
import com.web.timetable.timetablemanagement.repository.FacultyRepository;
import com.web.timetable.timetablemanagement.service.CourseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

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
        // Arrange -- test data is setup here
        Course course = new Course();

        // Given -- here the expected behaviour is defined (We specify that when the save method is called with the course object, it should return the same course object)
        //Instead of actually saving the data to the repository, we mock the behavior of the save method to return the same object that was passed in
        when(courseRepo.save(course)).thenReturn(course);

        // Act -- The method under test, courseService.createCourse(course), is called
        Course createdCourse = courseService.createCourse(course);

        // Assert -- we assert that the createdCourse object is equal to the original course object using the assertEquals method
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
        String courseId = "0001203";
        Course updatedCourse = new Course();
        updatedCourse.setCode("C002");

        // Mock behavior for findById and save methods
        when(courseRepo.findById(courseId)).thenReturn(Optional.of(new Course()));
        //Invocation is an object that represents the mocked method invocation. It contains information about the method call, such as the arguments passed to the method.
        when(courseRepo.save(Mockito.any(Course.class))).thenAnswer(invocation -> {
            //Retrieves the first argument (index 0) passed to the mocked save method. Since save takes a Course object as its argument, this line gets the Course object passed to the save method.
            Course savedCourse = invocation.getArgument(0);
            return savedCourse;
        });
        Course result = courseService.updateCourse(courseId, updatedCourse);
        assertEquals(updatedCourse.getCode(), result.getCode());
    }

    @Test
    public void testDeleteCourse() {
        String courseId = "0011223";

        // This line tells Mockito not to do anything when the deleteById method is called, as we don't need any specific behavior for this test case.
        doNothing().when(courseRepo).deleteById(courseId);

        courseService.deleteCourse(courseId);

        // We use the verify method from Mockito to assert that the deleteById method of the courseRepo object was called exactly once with the courseId value.
        verify(courseRepo, times(1)).deleteById(courseId);
    }
}

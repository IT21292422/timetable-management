package com.web.timetable.timetablemanagement.service;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.web.timetable.timetablemanagement.model.Course;
import com.web.timetable.timetablemanagement.model.Faculty;
import com.web.timetable.timetablemanagement.model.Session;
import com.web.timetable.timetablemanagement.model.UserEntity;
import com.web.timetable.timetablemanagement.repository.CourseRepository;
import com.web.timetable.timetablemanagement.repository.FacultyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {CourseService.class})
@RunWith(SpringJUnit4ClassRunner.class)
@DisabledInAotMode
public class CourseServiceDiffblueTest {
    @MockBean
    private CourseRepository courseRepository;

    @Autowired
    private CourseService courseService;

    @MockBean
    private FacultyRepository facultyRepository;

    /**
     * Method under test: {@link CourseService#createCourse(Course)}
     */
    @Test
    public void testCreateCourse() {
        // Arrange
        UserEntity user = new UserEntity();
        user.setId("42");
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setUsername("janedoe");

        Faculty faculty = new Faculty();
        faculty.setDepartment("Department");
        faculty.setId("42");
        faculty.setName("Name");
        faculty.setUser(user);

        Course course = new Course();
        course.setCode("Code");
        course.setCredits(1);
        course.setDescription("The characteristics of someone or something");
        course.setFaculty(faculty);
        course.setId("42");
        course.setName("Name");
        course.setSessions(new ArrayList<>());
        when(courseRepository.save(Mockito.<Course>any())).thenReturn(course);

        UserEntity user2 = new UserEntity();
        user2.setId("42");
        user2.setPassword("iloveyou");
        user2.setRoles(new ArrayList<>());
        user2.setUsername("janedoe");

        Faculty faculty2 = new Faculty();
        faculty2.setDepartment("Department");
        faculty2.setId("42");
        faculty2.setName("Name");
        faculty2.setUser(user2);

        Course course2 = new Course();
        course2.setCode("Code");
        course2.setCredits(1);
        course2.setDescription("The characteristics of someone or something");
        course2.setFaculty(faculty2);
        course2.setId("42");
        course2.setName("Name");
        course2.setSessions(new ArrayList<>());

        // Act
        Course actualCreateCourseResult = courseService.createCourse(course2);

        // Assert
        verify(courseRepository).save(isA(Course.class));
        assertSame(course, actualCreateCourseResult);
    }

    /**
     * Method under test: {@link CourseService#createCourse(Course)}
     */
    @Test
    public void testCreateCourse2() {
        // Arrange
        when(courseRepository.save(Mockito.<Course>any())).thenThrow(new NoSuchElementException("foo"));

        UserEntity user = new UserEntity();
        user.setId("42");
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setUsername("janedoe");

        Faculty faculty = new Faculty();
        faculty.setDepartment("Department");
        faculty.setId("42");
        faculty.setName("Name");
        faculty.setUser(user);

        Course course = new Course();
        course.setCode("Code");
        course.setCredits(1);
        course.setDescription("The characteristics of someone or something");
        course.setFaculty(faculty);
        course.setId("42");
        course.setName("Name");
        course.setSessions(new ArrayList<>());

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> courseService.createCourse(course));
        verify(courseRepository).save(isA(Course.class));
    }

    /**
     * Method under test: {@link CourseService#getAllCourses()}
     */
    @Test
    public void testGetAllCourses() {
        // Arrange
        ArrayList<Course> courseList = new ArrayList<>();
        when(courseRepository.findAll()).thenReturn(courseList);

        // Act
        List<Course> actualAllCourses = courseService.getAllCourses();

        // Assert
        verify(courseRepository).findAll();
        assertTrue(actualAllCourses.isEmpty());
        assertSame(courseList, actualAllCourses);
    }

    /**
     * Method under test: {@link CourseService#getAllCourses()}
     */
    @Test
    public void testGetAllCourses2() {
        // Arrange
        when(courseRepository.findAll()).thenThrow(new NoSuchElementException("foo"));

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> courseService.getAllCourses());
        verify(courseRepository).findAll();
    }

    /**
     * Method under test: {@link CourseService#getCourseById(String)}
     */
    @Test
    public void testGetCourseById() {
        // Arrange
        UserEntity user = new UserEntity();
        user.setId("42");
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setUsername("janedoe");

        Faculty faculty = new Faculty();
        faculty.setDepartment("Department");
        faculty.setId("42");
        faculty.setName("Name");
        faculty.setUser(user);

        Course course = new Course();
        course.setCode("Code");
        course.setCredits(1);
        course.setDescription("The characteristics of someone or something");
        course.setFaculty(faculty);
        course.setId("42");
        course.setName("Name");
        course.setSessions(new ArrayList<>());
        Optional<Course> ofResult = Optional.of(course);
        when(courseRepository.findCourseByCode(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        Optional<Course> actualCourseById = courseService.getCourseById("42");

        // Assert
        verify(courseRepository).findCourseByCode(eq("42"));
        assertSame(ofResult, actualCourseById);
    }

    /**
     * Method under test: {@link CourseService#getCourseById(String)}
     */
    @Test
    public void testGetCourseById2() {
        // Arrange
        when(courseRepository.findCourseByCode(Mockito.<String>any())).thenThrow(new NoSuchElementException("foo"));

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> courseService.getCourseById("42"));
        verify(courseRepository).findCourseByCode(eq("42"));
    }

    /**
     * Method under test: {@link CourseService#updateCourse(String, Course)}
     */
    @Test
    public void testUpdateCourse() {
        // Arrange
        UserEntity user = new UserEntity();
        user.setId("42");
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setUsername("janedoe");

        Faculty faculty = new Faculty();
        faculty.setDepartment("Department");
        faculty.setId("42");
        faculty.setName("Name");
        faculty.setUser(user);

        Course course = new Course();
        course.setCode("Code");
        course.setCredits(1);
        course.setDescription("The characteristics of someone or something");
        course.setFaculty(faculty);
        course.setId("42");
        course.setName("Name");
        course.setSessions(new ArrayList<>());
        Optional<Course> ofResult = Optional.of(course);

        UserEntity user2 = new UserEntity();
        user2.setId("42");
        user2.setPassword("iloveyou");
        user2.setRoles(new ArrayList<>());
        user2.setUsername("janedoe");

        Faculty faculty2 = new Faculty();
        faculty2.setDepartment("Department");
        faculty2.setId("42");
        faculty2.setName("Name");
        faculty2.setUser(user2);

        Course course2 = new Course();
        course2.setCode("Code");
        course2.setCredits(1);
        course2.setDescription("The characteristics of someone or something");
        course2.setFaculty(faculty2);
        course2.setId("42");
        course2.setName("Name");
        course2.setSessions(new ArrayList<>());
        when(courseRepository.save(Mockito.<Course>any())).thenReturn(course2);
        when(courseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        UserEntity user3 = new UserEntity();
        user3.setId("42");
        user3.setPassword("iloveyou");
        user3.setRoles(new ArrayList<>());
        user3.setUsername("janedoe");

        Faculty faculty3 = new Faculty();
        faculty3.setDepartment("Department");
        faculty3.setId("42");
        faculty3.setName("Name");
        faculty3.setUser(user3);

        Course updatedCourse = new Course();
        updatedCourse.setCode("Code");
        updatedCourse.setCredits(1);
        updatedCourse.setDescription("The characteristics of someone or something");
        updatedCourse.setFaculty(faculty3);
        updatedCourse.setId("42");
        updatedCourse.setName("Name");
        updatedCourse.setSessions(new ArrayList<>());

        // Act
        Course actualUpdateCourseResult = courseService.updateCourse("42", updatedCourse);

        // Assert
        verify(courseRepository).findById(eq("42"));
        verify(courseRepository).save(isA(Course.class));
        assertSame(course2, actualUpdateCourseResult);
    }

    /**
     * Method under test: {@link CourseService#updateCourse(String, Course)}
     */
    @Test
    public void testUpdateCourse2() {
        // Arrange
        UserEntity user = new UserEntity();
        user.setId("42");
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setUsername("janedoe");

        Faculty faculty = new Faculty();
        faculty.setDepartment("Department");
        faculty.setId("42");
        faculty.setName("Name");
        faculty.setUser(user);

        Course course = new Course();
        course.setCode("Code");
        course.setCredits(1);
        course.setDescription("The characteristics of someone or something");
        course.setFaculty(faculty);
        course.setId("42");
        course.setName("Name");
        course.setSessions(new ArrayList<>());
        Optional<Course> ofResult = Optional.of(course);
        when(courseRepository.save(Mockito.<Course>any())).thenThrow(new NoSuchElementException("foo"));
        when(courseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        UserEntity user2 = new UserEntity();
        user2.setId("42");
        user2.setPassword("iloveyou");
        user2.setRoles(new ArrayList<>());
        user2.setUsername("janedoe");

        Faculty faculty2 = new Faculty();
        faculty2.setDepartment("Department");
        faculty2.setId("42");
        faculty2.setName("Name");
        faculty2.setUser(user2);

        Course updatedCourse = new Course();
        updatedCourse.setCode("Code");
        updatedCourse.setCredits(1);
        updatedCourse.setDescription("The characteristics of someone or something");
        updatedCourse.setFaculty(faculty2);
        updatedCourse.setId("42");
        updatedCourse.setName("Name");
        updatedCourse.setSessions(new ArrayList<>());

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> courseService.updateCourse("42", updatedCourse));
        verify(courseRepository).findById(eq("42"));
        verify(courseRepository).save(isA(Course.class));
    }

    /**
     * Method under test: {@link CourseService#updateCourse(String, Course)}
     */
    @Test
    public void testUpdateCourse3() {
        // Arrange
        Optional<Course> emptyResult = Optional.empty();
        when(courseRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        UserEntity user = new UserEntity();
        user.setId("42");
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setUsername("janedoe");

        Faculty faculty = new Faculty();
        faculty.setDepartment("Department");
        faculty.setId("42");
        faculty.setName("Name");
        faculty.setUser(user);

        Course updatedCourse = new Course();
        updatedCourse.setCode("Code");
        updatedCourse.setCredits(1);
        updatedCourse.setDescription("The characteristics of someone or something");
        updatedCourse.setFaculty(faculty);
        updatedCourse.setId("42");
        updatedCourse.setName("Name");
        updatedCourse.setSessions(new ArrayList<>());

        // Act
        Course actualUpdateCourseResult = courseService.updateCourse("42", updatedCourse);

        // Assert
        verify(courseRepository).findById(eq("42"));
        assertNull(actualUpdateCourseResult);
    }

    /**
     * Method under test: {@link CourseService#deleteCourse(String)}
     */
    @Test
    public void testDeleteCourse() {
        // Arrange
        doNothing().when(courseRepository).deleteById(Mockito.<String>any());

        // Act
        courseService.deleteCourse("42");

        // Assert that nothing has changed
        verify(courseRepository).deleteById(eq("42"));
        assertTrue(courseService.getAllCourses().isEmpty());
    }

    /**
     * Method under test: {@link CourseService#deleteCourse(String)}
     */
    @Test
    public void testDeleteCourse2() {
        // Arrange
        doThrow(new NoSuchElementException("foo")).when(courseRepository).deleteById(Mockito.<String>any());

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> courseService.deleteCourse("42"));
        verify(courseRepository).deleteById(eq("42"));
    }

    /**
     * Method under test:
     * {@link CourseService#assignSessionToCourse(String, Session)}
     */
    @Test
    public void testAssignSessionToCourse() {
        // Arrange
        UserEntity user = new UserEntity();
        user.setId("42");
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setUsername("janedoe");

        Faculty faculty = new Faculty();
        faculty.setDepartment("Department");
        faculty.setId("42");
        faculty.setName("Name");
        faculty.setUser(user);

        Course course = new Course();
        course.setCode("Code");
        course.setCredits(1);
        course.setDescription("The characteristics of someone or something");
        course.setFaculty(faculty);
        course.setId("42");
        course.setName("Name");
        course.setSessions(new ArrayList<>());
        Optional<Course> ofResult = Optional.of(course);

        UserEntity user2 = new UserEntity();
        user2.setId("42");
        user2.setPassword("iloveyou");
        user2.setRoles(new ArrayList<>());
        user2.setUsername("janedoe");

        Faculty faculty2 = new Faculty();
        faculty2.setDepartment("Department");
        faculty2.setId("42");
        faculty2.setName("Name");
        faculty2.setUser(user2);

        Course course2 = new Course();
        course2.setCode("Code");
        course2.setCredits(1);
        course2.setDescription("The characteristics of someone or something");
        course2.setFaculty(faculty2);
        course2.setId("42");
        course2.setName("Name");
        course2.setSessions(new ArrayList<>());
        when(courseRepository.save(Mockito.<Course>any())).thenReturn(course2);
        when(courseRepository.findCourseByCode(Mockito.<String>any())).thenReturn(ofResult);

        Session session = new Session();
        session.setDay("Day");
        session.setEndTime("End Time");
        session.setFaculty("Faculty");
        session.setLocation("Location");
        session.setSessionId("42");
        session.setStartTime("Start Time");

        // Act
        Course actualAssignSessionToCourseResult = courseService.assignSessionToCourse("Course Code", session);

        // Assert
        verify(courseRepository).findCourseByCode(eq("COURSE CODE"));
        verify(courseRepository).save(isA(Course.class));
        assertSame(course2, actualAssignSessionToCourseResult);
    }

    /**
     * Method under test:
     * {@link CourseService#assignSessionToCourse(String, Session)}
     */
    @Test
    public void testAssignSessionToCourse2() {
        // Arrange
        UserEntity user = new UserEntity();
        user.setId("42");
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setUsername("janedoe");

        Faculty faculty = new Faculty();
        faculty.setDepartment("Department");
        faculty.setId("42");
        faculty.setName("Name");
        faculty.setUser(user);

        Course course = new Course();
        course.setCode("Code");
        course.setCredits(1);
        course.setDescription("The characteristics of someone or something");
        course.setFaculty(faculty);
        course.setId("42");
        course.setName("Name");
        course.setSessions(new ArrayList<>());
        Optional<Course> ofResult = Optional.of(course);
        when(courseRepository.save(Mockito.<Course>any())).thenThrow(new NoSuchElementException("foo"));
        when(courseRepository.findCourseByCode(Mockito.<String>any())).thenReturn(ofResult);

        Session session = new Session();
        session.setDay("Day");
        session.setEndTime("End Time");
        session.setFaculty("Faculty");
        session.setLocation("Location");
        session.setSessionId("42");
        session.setStartTime("Start Time");

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> courseService.assignSessionToCourse("Course Code", session));
        verify(courseRepository).findCourseByCode(eq("COURSE CODE"));
        verify(courseRepository).save(isA(Course.class));
    }

    /**
     * Method under test:
     * {@link CourseService#assignSessionToCourse(String, Session)}
     */
    @Test
    public void testAssignSessionToCourse3() {
        // Arrange
        Optional<Course> emptyResult = Optional.empty();
        when(courseRepository.findCourseByCode(Mockito.<String>any())).thenReturn(emptyResult);

        Session session = new Session();
        session.setDay("Day");
        session.setEndTime("End Time");
        session.setFaculty("Faculty");
        session.setLocation("Location");
        session.setSessionId("42");
        session.setStartTime("Start Time");

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> courseService.assignSessionToCourse("Course Code", session));
        verify(courseRepository).findCourseByCode(eq("COURSE CODE"));
    }

    /**
     * Method under test:
     * {@link CourseService#updateSessionInCourse(String, Session)}
     */
    @Test
    public void testUpdateSessionInCourse() {
        // Arrange
        UserEntity user = new UserEntity();
        user.setId("42");
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setUsername("janedoe");

        Faculty faculty = new Faculty();
        faculty.setDepartment("Department");
        faculty.setId("42");
        faculty.setName("Name");
        faculty.setUser(user);

        Course course = new Course();
        course.setCode("Code");
        course.setCredits(1);
        course.setDescription("The characteristics of someone or something");
        course.setFaculty(faculty);
        course.setId("42");
        course.setName("Name");
        course.setSessions(new ArrayList<>());
        Optional<Course> ofResult = Optional.of(course);

        UserEntity user2 = new UserEntity();
        user2.setId("42");
        user2.setPassword("iloveyou");
        user2.setRoles(new ArrayList<>());
        user2.setUsername("janedoe");

        Faculty faculty2 = new Faculty();
        faculty2.setDepartment("Department");
        faculty2.setId("42");
        faculty2.setName("Name");
        faculty2.setUser(user2);

        Course course2 = new Course();
        course2.setCode("Code");
        course2.setCredits(1);
        course2.setDescription("The characteristics of someone or something");
        course2.setFaculty(faculty2);
        course2.setId("42");
        course2.setName("Name");
        course2.setSessions(new ArrayList<>());
        when(courseRepository.save(Mockito.<Course>any())).thenReturn(course2);
        when(courseRepository.findCourseByCode(Mockito.<String>any())).thenReturn(ofResult);

        Session updatedSession = new Session();
        updatedSession.setDay("Day");
        updatedSession.setEndTime("End Time");
        updatedSession.setFaculty("Faculty");
        updatedSession.setLocation("Location");
        updatedSession.setSessionId("42");
        updatedSession.setStartTime("Start Time");

        // Act
        Course actualUpdateSessionInCourseResult = courseService.updateSessionInCourse("42", updatedSession);

        // Assert
        verify(courseRepository).findCourseByCode(eq("42"));
        verify(courseRepository).save(isA(Course.class));
        assertSame(course2, actualUpdateSessionInCourseResult);
    }

    /**
     * Method under test:
     * {@link CourseService#updateSessionInCourse(String, Session)}
     */
    @Test
    public void testUpdateSessionInCourse2() {
        // Arrange
        UserEntity user = new UserEntity();
        user.setId("42");
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setUsername("janedoe");

        Faculty faculty = new Faculty();
        faculty.setDepartment("Department");
        faculty.setId("42");
        faculty.setName("Name");
        faculty.setUser(user);

        Course course = new Course();
        course.setCode("Code");
        course.setCredits(1);
        course.setDescription("The characteristics of someone or something");
        course.setFaculty(faculty);
        course.setId("42");
        course.setName("Name");
        course.setSessions(new ArrayList<>());
        Optional<Course> ofResult = Optional.of(course);
        when(courseRepository.save(Mockito.<Course>any())).thenThrow(new NoSuchElementException("foo"));
        when(courseRepository.findCourseByCode(Mockito.<String>any())).thenReturn(ofResult);

        Session updatedSession = new Session();
        updatedSession.setDay("Day");
        updatedSession.setEndTime("End Time");
        updatedSession.setFaculty("Faculty");
        updatedSession.setLocation("Location");
        updatedSession.setSessionId("42");
        updatedSession.setStartTime("Start Time");

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> courseService.updateSessionInCourse("42", updatedSession));
        verify(courseRepository).findCourseByCode(eq("42"));
        verify(courseRepository).save(isA(Course.class));
    }

    /**
     * Method under test:
     * {@link CourseService#updateSessionInCourse(String, Session)}
     */
    @Test
    public void testUpdateSessionInCourse3() {
        // Arrange
        UserEntity user = new UserEntity();
        user.setId("42");
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setUsername("janedoe");

        Faculty faculty = new Faculty();
        faculty.setDepartment("Department");
        faculty.setId("42");
        faculty.setName("Name");
        faculty.setUser(user);

        Session session = new Session();
        session.setDay("Day");
        session.setEndTime("End Time");
        session.setFaculty("Faculty");
        session.setLocation("Location");
        session.setSessionId("42");
        session.setStartTime("Start Time");

        ArrayList<Session> sessions = new ArrayList<>();
        sessions.add(session);

        Course course = new Course();
        course.setCode("Code");
        course.setCredits(1);
        course.setDescription("The characteristics of someone or something");
        course.setFaculty(faculty);
        course.setId("42");
        course.setName("Name");
        course.setSessions(sessions);
        Optional<Course> ofResult = Optional.of(course);

        UserEntity user2 = new UserEntity();
        user2.setId("42");
        user2.setPassword("iloveyou");
        user2.setRoles(new ArrayList<>());
        user2.setUsername("janedoe");

        Faculty faculty2 = new Faculty();
        faculty2.setDepartment("Department");
        faculty2.setId("42");
        faculty2.setName("Name");
        faculty2.setUser(user2);

        Course course2 = new Course();
        course2.setCode("Code");
        course2.setCredits(1);
        course2.setDescription("The characteristics of someone or something");
        course2.setFaculty(faculty2);
        course2.setId("42");
        course2.setName("Name");
        course2.setSessions(new ArrayList<>());
        when(courseRepository.save(Mockito.<Course>any())).thenReturn(course2);
        when(courseRepository.findCourseByCode(Mockito.<String>any())).thenReturn(ofResult);

        Session updatedSession = new Session();
        updatedSession.setDay("Day");
        updatedSession.setEndTime("End Time");
        updatedSession.setFaculty("Faculty");
        updatedSession.setLocation("Location");
        updatedSession.setSessionId("42");
        updatedSession.setStartTime("Start Time");

        // Act
        Course actualUpdateSessionInCourseResult = courseService.updateSessionInCourse("42", updatedSession);

        // Assert
        verify(courseRepository).findCourseByCode(eq("42"));
        verify(courseRepository).save(isA(Course.class));
        assertSame(course2, actualUpdateSessionInCourseResult);
    }

    /**
     * Method under test:
     * {@link CourseService#updateSessionInCourse(String, Session)}
     */
    @Test
    public void testUpdateSessionInCourse4() {
        // Arrange
        UserEntity user = new UserEntity();
        user.setId("42");
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setUsername("janedoe");

        Faculty faculty = new Faculty();
        faculty.setDepartment("Department");
        faculty.setId("42");
        faculty.setName("Name");
        faculty.setUser(user);

        Session session = new Session();
        session.setDay("Day");
        session.setEndTime("End Time");
        session.setFaculty("Faculty");
        session.setLocation("Location");
        session.setSessionId("42");
        session.setStartTime("Start Time");

        Session session2 = new Session();
        session2.setDay("Day");
        session2.setEndTime("End Time");
        session2.setFaculty("Faculty");
        session2.setLocation("Location");
        session2.setSessionId("Session Id");
        session2.setStartTime("Start Time");

        ArrayList<Session> sessions = new ArrayList<>();
        sessions.add(session2);
        sessions.add(session);

        Course course = new Course();
        course.setCode("Code");
        course.setCredits(1);
        course.setDescription("The characteristics of someone or something");
        course.setFaculty(faculty);
        course.setId("42");
        course.setName("Name");
        course.setSessions(sessions);
        Optional<Course> ofResult = Optional.of(course);

        UserEntity user2 = new UserEntity();
        user2.setId("42");
        user2.setPassword("iloveyou");
        user2.setRoles(new ArrayList<>());
        user2.setUsername("janedoe");

        Faculty faculty2 = new Faculty();
        faculty2.setDepartment("Department");
        faculty2.setId("42");
        faculty2.setName("Name");
        faculty2.setUser(user2);

        Course course2 = new Course();
        course2.setCode("Code");
        course2.setCredits(1);
        course2.setDescription("The characteristics of someone or something");
        course2.setFaculty(faculty2);
        course2.setId("42");
        course2.setName("Name");
        course2.setSessions(new ArrayList<>());
        when(courseRepository.save(Mockito.<Course>any())).thenReturn(course2);
        when(courseRepository.findCourseByCode(Mockito.<String>any())).thenReturn(ofResult);

        Session updatedSession = new Session();
        updatedSession.setDay("Day");
        updatedSession.setEndTime("End Time");
        updatedSession.setFaculty("Faculty");
        updatedSession.setLocation("Location");
        updatedSession.setSessionId("42");
        updatedSession.setStartTime("Start Time");

        // Act
        Course actualUpdateSessionInCourseResult = courseService.updateSessionInCourse("42", updatedSession);

        // Assert
        verify(courseRepository).findCourseByCode(eq("42"));
        verify(courseRepository).save(isA(Course.class));
        assertSame(course2, actualUpdateSessionInCourseResult);
    }

    /**
     * Method under test:
     * {@link CourseService#updateSessionInCourse(String, Session)}
     */
    @Test
    public void testUpdateSessionInCourse5() {
        // Arrange
        Optional<Course> emptyResult = Optional.empty();
        when(courseRepository.findCourseByCode(Mockito.<String>any())).thenReturn(emptyResult);

        Session updatedSession = new Session();
        updatedSession.setDay("Day");
        updatedSession.setEndTime("End Time");
        updatedSession.setFaculty("Faculty");
        updatedSession.setLocation("Location");
        updatedSession.setSessionId("42");
        updatedSession.setStartTime("Start Time");

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> courseService.updateSessionInCourse("42", updatedSession));
        verify(courseRepository).findCourseByCode(eq("42"));
    }

    /**
     * Method under test:
     * {@link CourseService#removeSessionFromCourse(String, String)}
     */
    @Test
    public void testRemoveSessionFromCourse() {
        // Arrange
        UserEntity user = new UserEntity();
        user.setId("42");
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setUsername("janedoe");

        Faculty faculty = new Faculty();
        faculty.setDepartment("Department");
        faculty.setId("42");
        faculty.setName("Name");
        faculty.setUser(user);

        Course course = new Course();
        course.setCode("Code");
        course.setCredits(1);
        course.setDescription("The characteristics of someone or something");
        course.setFaculty(faculty);
        course.setId("42");
        course.setName("Name");
        course.setSessions(new ArrayList<>());
        Optional<Course> ofResult = Optional.of(course);

        UserEntity user2 = new UserEntity();
        user2.setId("42");
        user2.setPassword("iloveyou");
        user2.setRoles(new ArrayList<>());
        user2.setUsername("janedoe");

        Faculty faculty2 = new Faculty();
        faculty2.setDepartment("Department");
        faculty2.setId("42");
        faculty2.setName("Name");
        faculty2.setUser(user2);

        Course course2 = new Course();
        course2.setCode("Code");
        course2.setCredits(1);
        course2.setDescription("The characteristics of someone or something");
        course2.setFaculty(faculty2);
        course2.setId("42");
        course2.setName("Name");
        course2.setSessions(new ArrayList<>());
        when(courseRepository.save(Mockito.<Course>any())).thenReturn(course2);
        when(courseRepository.findCourseByCode(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        Course actualRemoveSessionFromCourseResult = courseService.removeSessionFromCourse("Course Code", "42");

        // Assert
        verify(courseRepository).findCourseByCode(eq("COURSE CODE"));
        verify(courseRepository).save(isA(Course.class));
        assertSame(course2, actualRemoveSessionFromCourseResult);
    }

    /**
     * Method under test:
     * {@link CourseService#removeSessionFromCourse(String, String)}
     */
    @Test
    public void testRemoveSessionFromCourse2() {
        // Arrange
        UserEntity user = new UserEntity();
        user.setId("42");
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setUsername("janedoe");

        Faculty faculty = new Faculty();
        faculty.setDepartment("Department");
        faculty.setId("42");
        faculty.setName("Name");
        faculty.setUser(user);

        Course course = new Course();
        course.setCode("Code");
        course.setCredits(1);
        course.setDescription("The characteristics of someone or something");
        course.setFaculty(faculty);
        course.setId("42");
        course.setName("Name");
        course.setSessions(new ArrayList<>());
        Optional<Course> ofResult = Optional.of(course);
        when(courseRepository.save(Mockito.<Course>any())).thenThrow(new NoSuchElementException("foo"));
        when(courseRepository.findCourseByCode(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> courseService.removeSessionFromCourse("Course Code", "42"));
        verify(courseRepository).findCourseByCode(eq("COURSE CODE"));
        verify(courseRepository).save(isA(Course.class));
    }

    /**
     * Method under test:
     * {@link CourseService#removeSessionFromCourse(String, String)}
     */
    @Test
    public void testRemoveSessionFromCourse3() {
        // Arrange
        UserEntity user = new UserEntity();
        user.setId("42");
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setUsername("janedoe");

        Faculty faculty = new Faculty();
        faculty.setDepartment("Department");
        faculty.setId("42");
        faculty.setName("Name");
        faculty.setUser(user);

        Session session = new Session();
        session.setDay("Day");
        session.setEndTime("End Time");
        session.setFaculty("Faculty");
        session.setLocation("Location");
        session.setSessionId("42");
        session.setStartTime("Start Time");

        ArrayList<Session> sessions = new ArrayList<>();
        sessions.add(session);

        Course course = new Course();
        course.setCode("Code");
        course.setCredits(1);
        course.setDescription("The characteristics of someone or something");
        course.setFaculty(faculty);
        course.setId("42");
        course.setName("Name");
        course.setSessions(sessions);
        Optional<Course> ofResult = Optional.of(course);

        UserEntity user2 = new UserEntity();
        user2.setId("42");
        user2.setPassword("iloveyou");
        user2.setRoles(new ArrayList<>());
        user2.setUsername("janedoe");

        Faculty faculty2 = new Faculty();
        faculty2.setDepartment("Department");
        faculty2.setId("42");
        faculty2.setName("Name");
        faculty2.setUser(user2);

        Course course2 = new Course();
        course2.setCode("Code");
        course2.setCredits(1);
        course2.setDescription("The characteristics of someone or something");
        course2.setFaculty(faculty2);
        course2.setId("42");
        course2.setName("Name");
        course2.setSessions(new ArrayList<>());
        when(courseRepository.save(Mockito.<Course>any())).thenReturn(course2);
        when(courseRepository.findCourseByCode(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        Course actualRemoveSessionFromCourseResult = courseService.removeSessionFromCourse("Course Code", "42");

        // Assert
        verify(courseRepository).findCourseByCode(eq("COURSE CODE"));
        verify(courseRepository).save(isA(Course.class));
        assertSame(course2, actualRemoveSessionFromCourseResult);
    }

    /**
     * Method under test:
     * {@link CourseService#removeSessionFromCourse(String, String)}
     */
    @Test
    public void testRemoveSessionFromCourse4() {
        // Arrange
        UserEntity user = new UserEntity();
        user.setId("42");
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setUsername("janedoe");

        Faculty faculty = new Faculty();
        faculty.setDepartment("Department");
        faculty.setId("42");
        faculty.setName("Name");
        faculty.setUser(user);

        Session session = new Session();
        session.setDay("Day");
        session.setEndTime("End Time");
        session.setFaculty("Faculty");
        session.setLocation("Location");
        session.setSessionId("42");
        session.setStartTime("Start Time");

        Session session2 = new Session();
        session2.setDay("Day");
        session2.setEndTime("End Time");
        session2.setFaculty("Faculty");
        session2.setLocation("Location");
        session2.setSessionId("Session Id");
        session2.setStartTime("Start Time");

        ArrayList<Session> sessions = new ArrayList<>();
        sessions.add(session2);
        sessions.add(session);

        Course course = new Course();
        course.setCode("Code");
        course.setCredits(1);
        course.setDescription("The characteristics of someone or something");
        course.setFaculty(faculty);
        course.setId("42");
        course.setName("Name");
        course.setSessions(sessions);
        Optional<Course> ofResult = Optional.of(course);

        UserEntity user2 = new UserEntity();
        user2.setId("42");
        user2.setPassword("iloveyou");
        user2.setRoles(new ArrayList<>());
        user2.setUsername("janedoe");

        Faculty faculty2 = new Faculty();
        faculty2.setDepartment("Department");
        faculty2.setId("42");
        faculty2.setName("Name");
        faculty2.setUser(user2);

        Course course2 = new Course();
        course2.setCode("Code");
        course2.setCredits(1);
        course2.setDescription("The characteristics of someone or something");
        course2.setFaculty(faculty2);
        course2.setId("42");
        course2.setName("Name");
        course2.setSessions(new ArrayList<>());
        when(courseRepository.save(Mockito.<Course>any())).thenReturn(course2);
        when(courseRepository.findCourseByCode(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        Course actualRemoveSessionFromCourseResult = courseService.removeSessionFromCourse("Course Code", "42");

        // Assert
        verify(courseRepository).findCourseByCode(eq("COURSE CODE"));
        verify(courseRepository).save(isA(Course.class));
        assertSame(course2, actualRemoveSessionFromCourseResult);
    }

    /**
     * Method under test:
     * {@link CourseService#removeSessionFromCourse(String, String)}
     */
    @Test
    public void testRemoveSessionFromCourse5() {
        // Arrange
        Optional<Course> emptyResult = Optional.empty();
        when(courseRepository.findCourseByCode(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> courseService.removeSessionFromCourse("Course Code", "42"));
        verify(courseRepository).findCourseByCode(eq("COURSE CODE"));
    }

    /**
     * Method under test:
     * {@link CourseService#assignFacultyToCourse(String, String)}
     */
    @Test
    public void testAssignFacultyToCourse() {
        // Arrange
        UserEntity user = new UserEntity();
        user.setId("42");
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setUsername("janedoe");

        Faculty faculty = new Faculty();
        faculty.setDepartment("Department");
        faculty.setId("42");
        faculty.setName("Name");
        faculty.setUser(user);

        Course course = new Course();
        course.setCode("Code");
        course.setCredits(1);
        course.setDescription("The characteristics of someone or something");
        course.setFaculty(faculty);
        course.setId("42");
        course.setName("Name");
        course.setSessions(new ArrayList<>());
        Optional<Course> ofResult = Optional.of(course);

        UserEntity user2 = new UserEntity();
        user2.setId("42");
        user2.setPassword("iloveyou");
        user2.setRoles(new ArrayList<>());
        user2.setUsername("janedoe");

        Faculty faculty2 = new Faculty();
        faculty2.setDepartment("Department");
        faculty2.setId("42");
        faculty2.setName("Name");
        faculty2.setUser(user2);

        Course course2 = new Course();
        course2.setCode("Code");
        course2.setCredits(1);
        course2.setDescription("The characteristics of someone or something");
        course2.setFaculty(faculty2);
        course2.setId("42");
        course2.setName("Name");
        course2.setSessions(new ArrayList<>());
        when(courseRepository.save(Mockito.<Course>any())).thenReturn(course2);
        when(courseRepository.findCourseByCode(Mockito.<String>any())).thenReturn(ofResult);

        UserEntity user3 = new UserEntity();
        user3.setId("42");
        user3.setPassword("iloveyou");
        user3.setRoles(new ArrayList<>());
        user3.setUsername("janedoe");

        Faculty faculty3 = new Faculty();
        faculty3.setDepartment("Department");
        faculty3.setId("42");
        faculty3.setName("Name");
        faculty3.setUser(user3);
        Optional<Faculty> ofResult2 = Optional.of(faculty3);
        when(facultyRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);

        // Act
        courseService.assignFacultyToCourse("42", "42");

        // Assert
        verify(courseRepository).findCourseByCode(eq("42"));
        verify(facultyRepository).findById(eq("42"));
        verify(courseRepository).save(isA(Course.class));
    }

    /**
     * Method under test:
     * {@link CourseService#assignFacultyToCourse(String, String)}
     */
    @Test
    public void testAssignFacultyToCourse2() {
        // Arrange
        UserEntity user = new UserEntity();
        user.setId("42");
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setUsername("janedoe");

        Faculty faculty = new Faculty();
        faculty.setDepartment("Department");
        faculty.setId("42");
        faculty.setName("Name");
        faculty.setUser(user);

        Course course = new Course();
        course.setCode("Code");
        course.setCredits(1);
        course.setDescription("The characteristics of someone or something");
        course.setFaculty(faculty);
        course.setId("42");
        course.setName("Name");
        course.setSessions(new ArrayList<>());
        Optional<Course> ofResult = Optional.of(course);
        when(courseRepository.save(Mockito.<Course>any())).thenThrow(new NoSuchElementException("foo"));
        when(courseRepository.findCourseByCode(Mockito.<String>any())).thenReturn(ofResult);

        UserEntity user2 = new UserEntity();
        user2.setId("42");
        user2.setPassword("iloveyou");
        user2.setRoles(new ArrayList<>());
        user2.setUsername("janedoe");

        Faculty faculty2 = new Faculty();
        faculty2.setDepartment("Department");
        faculty2.setId("42");
        faculty2.setName("Name");
        faculty2.setUser(user2);
        Optional<Faculty> ofResult2 = Optional.of(faculty2);
        when(facultyRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> courseService.assignFacultyToCourse("42", "42"));
        verify(courseRepository).findCourseByCode(eq("42"));
        verify(facultyRepository).findById(eq("42"));
        verify(courseRepository).save(isA(Course.class));
    }

    /**
     * Method under test:
     * {@link CourseService#assignFacultyToCourse(String, String)}
     */
    @Test
    public void testAssignFacultyToCourse3() {
        // Arrange
        Optional<Course> emptyResult = Optional.empty();
        when(courseRepository.findCourseByCode(Mockito.<String>any())).thenReturn(emptyResult);

        UserEntity user = new UserEntity();
        user.setId("42");
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setUsername("janedoe");

        Faculty faculty = new Faculty();
        faculty.setDepartment("Department");
        faculty.setId("42");
        faculty.setName("Name");
        faculty.setUser(user);
        Optional<Faculty> ofResult = Optional.of(faculty);
        when(facultyRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> courseService.assignFacultyToCourse("42", "42"));
        verify(courseRepository).findCourseByCode(eq("42"));
        verify(facultyRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link CourseService#assignFacultyToCourse(String, String)}
     */
    @Test
    public void testAssignFacultyToCourse4() {
        // Arrange
        UserEntity user = new UserEntity();
        user.setId("42");
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        user.setUsername("janedoe");

        Faculty faculty = new Faculty();
        faculty.setDepartment("Department");
        faculty.setId("42");
        faculty.setName("Name");
        faculty.setUser(user);

        Course course = new Course();
        course.setCode("Code");
        course.setCredits(1);
        course.setDescription("The characteristics of someone or something");
        course.setFaculty(faculty);
        course.setId("42");
        course.setName("Name");
        course.setSessions(new ArrayList<>());
        Optional<Course> ofResult = Optional.of(course);
        when(courseRepository.findCourseByCode(Mockito.<String>any())).thenReturn(ofResult);
        Optional<Faculty> emptyResult = Optional.empty();
        when(facultyRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> courseService.assignFacultyToCourse("42", "42"));
        verify(courseRepository).findCourseByCode(eq("42"));
        verify(facultyRepository).findById(eq("42"));
    }
}

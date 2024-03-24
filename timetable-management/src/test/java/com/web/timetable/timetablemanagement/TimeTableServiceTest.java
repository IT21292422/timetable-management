package com.web.timetable.timetablemanagement;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.web.timetable.timetablemanagement.model.Course;
import com.web.timetable.timetablemanagement.model.TimeTable;
import com.web.timetable.timetablemanagement.repository.TimeTableRepository;
import com.web.timetable.timetablemanagement.service.CourseService;
import com.web.timetable.timetablemanagement.service.TimeTableService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TimeTableServiceTest {

    @Mock
    private TimeTableRepository timeTableRepo;

    @Mock
    private CourseService courseService;

    @InjectMocks
    private TimeTableService timeTableService;

    @Test
    public void testAssignCourseToTimetable() {
        // Define the test data
        String timetableId = "T001";
        String courseCode = "C001";
        TimeTable timeTable = new TimeTable("T001", "Spring 2024");
        Course course = new Course("C001", "Math", "Mathematics course", 3);

        // Mock the behavior of the repositories and service
        when(timeTableRepo.findById(timetableId)).thenReturn(Optional.of(timeTable));
        when(courseService.getCourseById(courseCode)).thenReturn(Optional.of(course));
        when(timeTableRepo.save(any(TimeTable.class))).thenReturn(timeTable);

        // Call the method under test
        TimeTable result = timeTableService.assignCourseToTimetable(timetableId, courseCode);

        // Verify the result
        assertEquals(timetableId, result.getId());
        assertEquals(1, result.getCourses().size());
        assertEquals(courseCode, result.getCourses().get(0).getCode());
    }
}

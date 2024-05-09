package com.web.timetable.timetablemanagement;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
    private TimeTableRepository timetableRepo;

    @Mock
    private CourseService courseService;

    @InjectMocks
    private TimeTableService timetableService;

    @Test
    public void testCreateTimetable() {
        TimeTable timetable = new TimeTable();
        when(timetableRepo.save(timetable)).thenReturn(timetable);
        TimeTable createdTimetable = timetableService.createTimetable(timetable);
        assertEquals(timetable,createdTimetable);
    }

    @Test
    public void testGetAllTimetable(){
        List<TimeTable> timetableList = new ArrayList<>();
        when(timetableRepo.findAll()).thenReturn(timetableList);
        List<TimeTable> result = timetableService.getAllTimetable();
        assertEquals(timetableList.size(),result.size());
    }

    @Test
    public void testDeleteTimetable(){
        String timetableId = "1";
        doNothing().when(timetableRepo).deleteById(timetableId);
        timetableService.deleteTimetable(timetableId);
        verify(timetableRepo,times(1)).deleteById(timetableId);
    }

}

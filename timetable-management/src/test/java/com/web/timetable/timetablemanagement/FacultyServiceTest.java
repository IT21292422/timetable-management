package com.web.timetable.timetablemanagement;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.web.timetable.timetablemanagement.model.Faculty;
import com.web.timetable.timetablemanagement.repository.FacultyRepository;
import com.web.timetable.timetablemanagement.service.FacultyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FacultyServiceTest {

    @Mock
    private FacultyRepository facultyRepo;

    @InjectMocks
    private FacultyService facultyService;

    @Test
    public void testGetAllFaculties() {
        // Define the test data
        List<Faculty> facultyList = new ArrayList<>();
        facultyList.add(new Faculty("1", "FOC", "CSSE"));
        facultyList.add(new Faculty("2", "SOB", "Business"));

        // Mock the behavior of the faculty repository
        when(facultyRepo.findAll()).thenReturn(facultyList);

        // Call the method under test
        List<Faculty> result = facultyService.getAllFaculties();

        // Verify the result
        assertEquals(facultyList.size(), result.size());
    }

    @Test
    public void testUpdateFaculty() {
        // Define the test data
        String facultyId = "1";
        Faculty existingFaculty = new Faculty("1", "FOC", "CSSE");
        Faculty updatedFaculty = new Faculty("1", "SOB", "Business");

        // Mock the behavior of the faculty repository
        when(facultyRepo.findById(facultyId)).thenReturn(Optional.of(existingFaculty));
        when(facultyRepo.save(any(Faculty.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Call the method under test
        Faculty result = facultyService.updateFaculty(facultyId, updatedFaculty);

        // Verify the result
        assertEquals(updatedFaculty.getName(), result.getName());
        assertEquals(updatedFaculty.getDepartment(), result.getDepartment());
    }
}

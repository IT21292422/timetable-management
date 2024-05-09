package com.web.timetable.timetablemanagement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

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
    public void testCreateFaculty(){
        Faculty faculty = new Faculty();
        when(facultyRepo.save(faculty)).thenReturn(faculty);
        Faculty createdFaculty = facultyService.createFaculty(faculty);
        assertEquals(faculty,createdFaculty);
    }

    @Test
    public void testGetAllFaculties() {
        List<Faculty> facultyList = new ArrayList<>();

        // Mock the behavior of the faculty repository
        when(facultyRepo.findAll()).thenReturn(facultyList);

        // Call the method under test
        List<Faculty> result = facultyService.getAllFaculties();

        // Verify the result
        assertEquals(facultyList.size(), result.size());
    }

    @Test
    public void testGetFacultyById(){
        String facultyId = "11131222";
        Faculty faculty = new Faculty();
        when(facultyRepo.findById(facultyId)).thenReturn(Optional.of(faculty));
        Optional<Faculty> retrievedFaculty = facultyService.getFacultyById(facultyId);
        assertTrue(retrievedFaculty.isPresent());
        assertEquals(faculty,retrievedFaculty.get());
    }

    @Test
    public void testUpdateFaculty(){
        String facultyId = "11131222";
        Faculty existingFaculty = new Faculty();
        existingFaculty.setName("FOC");

        Faculty updatedFaculty = new Faculty();
        updatedFaculty.setName("SOB");

        when(facultyRepo.findById(facultyId)).thenReturn(Optional.of(existingFaculty));
        when(facultyRepo.save(existingFaculty)).thenReturn(existingFaculty);

        Faculty result = facultyService.updateFaculty(facultyId,updatedFaculty);

        assertNotNull(result);
        assertEquals(updatedFaculty.getName(), result.getName());

    }

    @Test
    public void testDeleteFaculty(){
        String facultyId = "01133342";
        doNothing().when(facultyRepo).deleteById(facultyId);
        facultyService.deleteFaculty(facultyId);
        verify(facultyRepo,times(1)).deleteById(facultyId);
    }

}

package com.web.timetable.timetablemanagement;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.web.timetable.timetablemanagement.model.Resource;
import com.web.timetable.timetablemanagement.repository.ResourceRepository;
import com.web.timetable.timetablemanagement.service.ResourceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ResourceServiceTest {

    @Mock
    private ResourceRepository resourceRepo;

    @InjectMocks
    private ResourceService resourceService;

    @Test
    public void testGetAllResources() {
        // Define the test data
        List<Resource> resourceList = new ArrayList<>();
        resourceList.add(new Resource("1", "Projector1"));
        resourceList.add(new Resource("2", "Projector2"));

        // Mock the behavior of the resource repository
        when(resourceRepo.findAll()).thenReturn(resourceList);

        // Call the method under test
        List<Resource> result = resourceService.getAllResources();

        // Verify the result
        assertEquals(resourceList.size(), result.size());
    }

    @Test
    public void testUpdateResource() {
        // Define the test data
        String resourceId = "1";
        Resource existingResource = new Resource("1", "Projector1");
        Resource updatedResource = new Resource("1", "Updated Resource");

        // Mock the behavior of the resource repository
        when(resourceRepo.findById(resourceId)).thenReturn(Optional.of(existingResource));
        when(resourceRepo.save(any(Resource.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Call the method under test
        Resource result = resourceService.updateResource(resourceId, updatedResource);

        // Verify the result
        assertEquals(updatedResource.getName(), result.getName());
    }
}

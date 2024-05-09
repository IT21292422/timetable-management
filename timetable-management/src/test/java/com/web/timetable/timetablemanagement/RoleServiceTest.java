package com.web.timetable.timetablemanagement;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.web.timetable.timetablemanagement.model.Role;
import com.web.timetable.timetablemanagement.repository.RoleRepository;
import com.web.timetable.timetablemanagement.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepo;

    @InjectMocks
    private RoleService roleService;

    @Test
    public void testCreateRole(){
        Role role = new Role();
        when(roleRepo.save(role)).thenReturn(role);
        Role createdRole = roleService.createRole(role);
        assertEquals(role,createdRole);
    }

    @Test
    public void testGetAllRoles() {
        // Define the test data
        List<Role> roleList = new ArrayList<>();
        roleList.add(new Role("1", "Admin"));
        roleList.add(new Role("2", "Student"));

        // Mock the behavior of the role repository
        when(roleRepo.findAll()).thenReturn(roleList);

        // Call the method under test
        List<Role> result = roleService.getAllRoles();

        // Verify the result
        assertEquals(roleList.size(), result.size());
    }

    @Test
    public void testUpdateRole() {
        // Define the test data
        String roleId = "1";
        Role existingRole = new Role("1", "Admin");
        Role updatedRole = new Role("1", "Updated Role");

        // Mock the behavior of the role repository
        when(roleRepo.findById(roleId)).thenReturn(Optional.of(existingRole));
        when(roleRepo.save(any(Role.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Call the method under test
        Role result = roleService.updateRole(roleId, updatedRole);

        // Verify the result
        assertEquals(updatedRole.getName(), result.getName());
    }

    @Test
    public void testDeleteRole(){
        String roleId = "1";
        doNothing().when(roleRepo).deleteById(roleId);
        roleService.deleteRole(roleId);
        verify(roleRepo,times(1)).deleteById(roleId);
    }
}

package com.web.timetable.timetablemanagement.controller;

import com.web.timetable.timetablemanagement.model.Role;
import com.web.timetable.timetablemanagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/createRole")
    public Role addRole(@RequestBody Role role){
        roleService.createRole(role);
        return role;
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles(){
        return new ResponseEntity<List<Role>>(roleService.getAllRoles(), HttpStatus.OK);
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<Optional<Role>> getRoleById(@PathVariable String roleId){
        return new ResponseEntity<Optional<Role>>(roleService.getRoleById(roleId), HttpStatus.OK);
    }

    @PutMapping("/updateRole/{id}")
    public Role updateRole(@PathVariable String id,@RequestBody Role updatedRole){
        roleService.updateRole(id, updatedRole);
        return updatedRole;
    }

    @DeleteMapping("/deleteRole/{id}")
    public void deleteRole(@PathVariable String id){
        roleService.deleteRole(id);
    }
}

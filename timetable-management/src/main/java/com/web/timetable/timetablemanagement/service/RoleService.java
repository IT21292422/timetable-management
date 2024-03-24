package com.web.timetable.timetablemanagement.service;

import com.web.timetable.timetablemanagement.model.Role;
import com.web.timetable.timetablemanagement.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepo;

    public Role createRole(Role role){

        return roleRepo.save(role);
    }

    public List<Role> getAllRoles(){

        return roleRepo.findAll();
    }

    public Optional<Role> getRoleById(String roleId){
        return roleRepo.findById(roleId);
    }

    public Role updateRole(String id, Role updatedRole){
        Optional<Role> optionalRole = roleRepo.findById(id);
        if(optionalRole.isPresent()){
            Role existingRole = optionalRole.get();
            existingRole.setName(updatedRole.getName());
            return roleRepo.save(existingRole);
        }else{
            return null;
        }
    }

    public void deleteRole(String id) {
        roleRepo.deleteById(id);
        System.out.println("Role with id " + id + " deleted...");
    }
}

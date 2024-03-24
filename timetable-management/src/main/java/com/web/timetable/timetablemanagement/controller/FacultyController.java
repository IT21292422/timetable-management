package com.web.timetable.timetablemanagement.controller;

import com.web.timetable.timetablemanagement.model.Faculty;
import com.web.timetable.timetablemanagement.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/faculty")
public class FacultyController {
    @Autowired
    private FacultyService facultyService;

    @PostMapping("/createFaculty")
    public Faculty addFaculty(@RequestBody Faculty faculty){
        facultyService.createFaculty(faculty);
        return faculty;
    }

    @GetMapping
    public ResponseEntity<List<Faculty>> getAllFaculty(){
        return new ResponseEntity<List<Faculty>>(facultyService.getAllFaculties(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Faculty>> getFacultyById(@PathVariable String id){
        return new ResponseEntity<Optional<Faculty>>(facultyService.getFacultyById(id), HttpStatus.OK);
    }

    @PutMapping("/updateFaculty/{id}")
    public Faculty updateFaculty(@PathVariable String id,@RequestBody Faculty updatedFaculty){
        facultyService.updateFaculty(id, updatedFaculty);
        return updatedFaculty;
    }

    @DeleteMapping("/deleteFaculty/{id}")
    public void deleteFaculty(@PathVariable String id){

        facultyService.deleteFaculty(id);
    }
}

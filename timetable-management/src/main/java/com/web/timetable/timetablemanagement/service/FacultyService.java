package com.web.timetable.timetablemanagement.service;

import com.web.timetable.timetablemanagement.model.Course;
import com.web.timetable.timetablemanagement.model.Faculty;
import com.web.timetable.timetablemanagement.repository.CourseRepository;
import com.web.timetable.timetablemanagement.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class FacultyService {
    @Autowired
    private FacultyRepository facultyRepo;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseRepository courseRepo;

    public Faculty createFaculty(Faculty faculty){

        return facultyRepo.save(faculty);
    }

    public List<Faculty> getAllFaculties(){

        return facultyRepo.findAll();
    }

    public Optional<Faculty> getFacultyById(String id){
        return facultyRepo.findById(id);
    }

    public Faculty updateFaculty(String id, Faculty updatedFaculty){
        Optional<Faculty> optionalFaculty = facultyRepo.findById(id);
        if(optionalFaculty.isPresent()){
            Faculty existingFaculty = optionalFaculty.get();
            existingFaculty.setName(updatedFaculty.getName());
            existingFaculty.setDepartment(updatedFaculty.getDepartment());
            return facultyRepo.save(existingFaculty);
        }else{
            return null;
        }
    }

    public void deleteFaculty(String id) {
        facultyRepo.deleteById(id);
        System.out.println("Faculty with id " + id + " deleted...");
    }
}

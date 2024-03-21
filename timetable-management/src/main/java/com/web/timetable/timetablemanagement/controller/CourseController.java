package com.web.timetable.timetablemanagement.controller;

import com.web.timetable.timetablemanagement.model.Course;
import com.web.timetable.timetablemanagement.service.CourseService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    private CourseService service;

    @PostMapping("/createCourse")
    public Course addCourse(@RequestBody Course course){
        service.createCourse(course);
        return course;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourse(){
        return new ResponseEntity<List<Course>>(service.getAllCourses(), HttpStatus.OK);
    }

    @GetMapping("/{courseCode}")
    public ResponseEntity<Optional<Course>> getCourseById(@PathVariable String courseCode){
        return new ResponseEntity<Optional<Course>>(service.getCourseById(courseCode), HttpStatus.OK);
    }

    @PutMapping("/updateCourse/{id}")
    public Course updateCourse(@PathVariable String id,@RequestBody Course updatedCourse){
        service.updateCourse(id, updatedCourse);
        return updatedCourse;
    }

    @DeleteMapping("/deleteCourse/{id}")
    public void deleteCourse(@PathVariable String id){
        service.deleteCourse(id);
    }
}

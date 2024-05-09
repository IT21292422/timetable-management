package com.web.timetable.timetablemanagement.controller;

import com.web.timetable.timetablemanagement.model.Enrollment;
import com.web.timetable.timetablemanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollment")
public class EnrollmentController {
    @Autowired
    private StudentService stdService;

    @GetMapping("/getStudent/{studentId}")
    public ResponseEntity<List<Enrollment>> getEnrolledCoursesByStudent(@PathVariable String studentId){
        List<Enrollment> enrollment = stdService.getEnrolledCoursesByStudent(studentId);
        return ResponseEntity.ok(enrollment);
    }

    @GetMapping("/getCourse/{courseId}")
    public ResponseEntity<List<Enrollment>> getEnrolledCoursesByCourse(@PathVariable String courseId){
        List<Enrollment> enrollment = stdService.getEnrolledCoursesByCourse(courseId);
        return ResponseEntity.ok(enrollment);
    }

    @PutMapping("/update/{id}")
    public Enrollment updateEnrollment(@PathVariable String id, @RequestBody Enrollment updatedEnrollment){
        stdService.updateEnrollment(id, updatedEnrollment);
        return updatedEnrollment;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEnrollment(@PathVariable String id){
        stdService.deleteEnrollment(id);
    }
}

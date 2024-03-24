package com.web.timetable.timetablemanagement.controller;

import com.web.timetable.timetablemanagement.model.Enrollment;
import com.web.timetable.timetablemanagement.model.Student;
import com.web.timetable.timetablemanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentService stdService;

    @PostMapping("/{studentId}/enroll/{courseId}")
    public ResponseEntity<Student> enrollInCourse(@PathVariable String studentId,@PathVariable String courseId){
        Student student = stdService.enrollInCourse(studentId,courseId);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/{studentId}/courses")
    public ResponseEntity<List<Enrollment>> getEnrolledCourses(@PathVariable String studentId){
        List<Enrollment> enrollment = stdService.getEnrolledCoursesByStudent(studentId);
        return ResponseEntity.ok(enrollment);
    }
}

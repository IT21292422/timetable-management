package com.web.timetable.timetablemanagement.service;

import com.web.timetable.timetablemanagement.model.Course;
import com.web.timetable.timetablemanagement.model.Enrollment;
import com.web.timetable.timetablemanagement.model.Student;
import com.web.timetable.timetablemanagement.repository.EnrollmentRepository;
import com.web.timetable.timetablemanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository stdRepo;
    @Autowired
    private EnrollmentRepository enrollRepo;
    @Autowired
    private CourseService courseService;

    public Enrollment updateEnrollment(String id, Enrollment updatedEnrollment){
        Optional<Enrollment> optionalEnrollment = enrollRepo.findById(id);
        if(optionalEnrollment.isPresent()){
            Enrollment existingEnrollment = optionalEnrollment.get();
            existingEnrollment.setStudentId(updatedEnrollment.getStudentId());
            existingEnrollment.setCourseId(updatedEnrollment.getCourseId());
            existingEnrollment.setEnrolledAt(updatedEnrollment.getEnrolledAt());
            return enrollRepo.save(existingEnrollment);
        }else{
            return null;
        }
    }

    public void deleteEnrollment(String id) {
        enrollRepo.deleteById(id);
        System.out.println("Enrollment with id " + id + " deleted...");
    }

    public Student enrollInCourse(String studentId, String courseId){
        Optional<Student> optionalStudent = stdRepo.findById(studentId);
        Optional<Course> optionalCourse = courseService.getCourseById(courseId);
        Student student;
        Course course;
        if(optionalStudent.isPresent()){
            student = optionalStudent.get();
        }else {
            throw new NoSuchElementException("No student found with code: " + studentId);
        }
        if(optionalCourse.isPresent()){
            course = optionalCourse.get();
        }else {
            throw new NoSuchElementException("No course found with code: " + courseId);
        }

        if(student.getEnrolledCourses().contains(courseId)){
            throw new IllegalStateException("Student is already enrolled in this course");
        }

        student.getEnrolledCourses().add(courseId);
        Enrollment enrollment = new Enrollment(null, studentId,courseId, LocalDateTime.now());
        enrollRepo.save(enrollment);
        return stdRepo.save(student);
    }

    public List<Enrollment> getEnrolledCoursesByStudent(String studentId){
        return enrollRepo.findByStudentId(studentId);
    }

    public List<Enrollment> getEnrolledCoursesByCourse(String courseId){
        return enrollRepo.findByCourseId(courseId);
    }

}

package com.web.timetable.timetablemanagement.repository;

import com.web.timetable.timetablemanagement.model.Enrollment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends MongoRepository<Enrollment, String> {
    List<Enrollment> findByStudentId(String studentId);
    List<Enrollment> findByCourseId(String courseId);
}

package com.web.timetable.timetablemanagement.repository;

import com.web.timetable.timetablemanagement.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
}

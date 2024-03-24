package com.web.timetable.timetablemanagement.repository;

import com.web.timetable.timetablemanagement.model.Faculty;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends MongoRepository<Faculty, String> {
}

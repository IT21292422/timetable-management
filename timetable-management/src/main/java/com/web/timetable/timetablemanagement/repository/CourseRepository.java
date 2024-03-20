package com.web.timetable.timetablemanagement.repository;

import com.web.timetable.timetablemanagement.model.Course;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends MongoRepository<Course, ObjectId> {
    Optional<Course> findCourseByCode(String courseCode);
}

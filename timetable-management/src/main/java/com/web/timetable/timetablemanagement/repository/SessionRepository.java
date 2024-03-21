package com.web.timetable.timetablemanagement.repository;

import com.web.timetable.timetablemanagement.model.Session;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends MongoRepository<Session, String> {
}

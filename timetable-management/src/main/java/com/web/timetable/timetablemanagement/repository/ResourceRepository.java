package com.web.timetable.timetablemanagement.repository;

import com.web.timetable.timetablemanagement.model.Resource;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends MongoRepository<Resource, String> {
}

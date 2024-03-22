package com.web.timetable.timetablemanagement.repository;

import com.web.timetable.timetablemanagement.model.Resource;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResourceRepository extends MongoRepository<Resource, String> {
}

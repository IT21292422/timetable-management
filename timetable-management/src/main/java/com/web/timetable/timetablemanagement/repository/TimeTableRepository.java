package com.web.timetable.timetablemanagement.repository;

import com.web.timetable.timetablemanagement.model.TimeTable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeTableRepository extends MongoRepository<TimeTable, String> {
}

package com.web.timetable.timetablemanagement.repository;

import com.web.timetable.timetablemanagement.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends MongoRepository<Room, String> {
}

package com.web.timetable.timetablemanagement.repository;

import com.web.timetable.timetablemanagement.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Room, String> {
}

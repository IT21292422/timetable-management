package com.web.timetable.timetablemanagement.repository;

import com.web.timetable.timetablemanagement.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends MongoRepository<Booking, String> {
    List<Booking> findByStartTimeBetweenAndRoomId(LocalDateTime startTime, LocalDateTime endTime, String roomId);
}

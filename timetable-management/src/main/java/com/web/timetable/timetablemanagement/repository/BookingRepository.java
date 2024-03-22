package com.web.timetable.timetablemanagement.repository;

import com.web.timetable.timetablemanagement.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends MongoRepository<Booking, String> {
    //It is generated based on the method name and expects to find bookings where the start time falls within the specified time range (startTime to endTime) and where the room ID matches the specified roomId.
    //This method would return a list of instances that have a startTime between start and end and a roomId that equals the provided roomId.
    List<Booking> findByStartTimeBetweenAndRoomId(LocalDateTime startTime, LocalDateTime endTime, String roomId);

    //Similarly this will filter entities where startTime falls between the range and resourceId matches a specified value
    List<Booking> findByStartTimeBetweenAndResourceId(LocalDateTime startTime, LocalDateTime endTime, String resourceId);
}

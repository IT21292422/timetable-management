package com.web.timetable.timetablemanagement.repository;

import com.web.timetable.timetablemanagement.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {

}

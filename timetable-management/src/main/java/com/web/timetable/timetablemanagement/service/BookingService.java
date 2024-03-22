package com.web.timetable.timetablemanagement.service;

import com.web.timetable.timetablemanagement.model.Booking;
import com.web.timetable.timetablemanagement.model.Course;
import com.web.timetable.timetablemanagement.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepo;

    //To check whether a room is available
    public boolean isRoomAvailable(LocalDateTime startTime, LocalDateTime endTime, String roomId){
        List<Booking> conflictBookings = bookingRepo.findByStartTimeBetweenAndRoomId(startTime, endTime, roomId);
        return conflictBookings.isEmpty();
    }

    //To check whether a resource is available
    public boolean isResourceAvailable(LocalDateTime startTime, LocalDateTime endTime, String resourceId){
        List<Booking> conflictBookings = bookingRepo.findByStartTimeBetweenAndResourceId(startTime, endTime, resourceId);
        return conflictBookings.isEmpty();
    }

    public Booking createBooking(Booking booking){

        return bookingRepo.save(booking);
    }

    public List<Booking> getAllBookings(){

        return bookingRepo.findAll();
    }

    public Optional<Booking> getBookingById(String bookingId){
        return bookingRepo.findById(bookingId);
    }

    public Booking updateBooking(String id, Booking updatedBooking){
        Optional<Booking> optionalBooking = bookingRepo.findById(id);
        if(optionalBooking.isPresent()){
            Booking existingBooking = optionalBooking.get();
            existingBooking.setDay(updatedBooking.getDay());
            existingBooking.setStartTime(updatedBooking.getStartTime());
            existingBooking.setEndTime(updatedBooking.getEndTime());
            if(updatedBooking.getResourceId()!=null){
                existingBooking.setResourceId(updatedBooking.getResourceId());
            }else if(updatedBooking.getRoomId()!=null){
                existingBooking.setRoomId(updatedBooking.getRoomId());
            }
            return bookingRepo.save(existingBooking);
        }else{
            return null;
        }
    }

    public void deleteBooking(String id) {
        bookingRepo.deleteById(id);
        System.out.println("Booking with id " + id + " deleted...");
    }
}

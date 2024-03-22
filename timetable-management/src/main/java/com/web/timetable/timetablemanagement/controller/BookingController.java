package com.web.timetable.timetablemanagement.controller;

import com.web.timetable.timetablemanagement.model.Booking;
import com.web.timetable.timetablemanagement.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/createBooking")
    public Booking addBooking(@RequestBody Booking booking){
        bookingService.createBooking(booking);
        return booking;
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBooking(){
        return new ResponseEntity<List<Booking>>(bookingService.getAllBookings(), HttpStatus.OK);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Optional<Booking>> getBookingById(@PathVariable String bookingId){
        return new ResponseEntity<Optional<Booking>>(bookingService.getBookingById(bookingId), HttpStatus.OK);
    }

    @PutMapping("/updateBooking/{id}")
    public Booking updateBooking(@PathVariable String id,@RequestBody Booking updatedBooking){
        bookingService.updateBooking(id, updatedBooking);
        return updatedBooking;
    }

    @DeleteMapping("/deleteBooking/{id}")
    public void deleteBooking(@PathVariable String id){
        bookingService.deleteBooking(id);
    }

    @PostMapping("/rooms/{roomId}")
    public ResponseEntity<String> bookRoom(@PathVariable String roomId, @RequestBody Booking booking){
        //Check room availability
        if(!bookingService.isRoomAvailable(booking.getStartTime(),booking.getEndTime(), roomId)){
            return ResponseEntity.badRequest().body("Room is not available for the specified time slot");
        }
            booking.setRoomId(roomId);
            bookingService.createBooking(booking);

            return ResponseEntity.ok("Room booked successfully");
    }

    @PostMapping("/resources/{resourceId}")
    public ResponseEntity<String> bookResource(@PathVariable String resourceId, @RequestBody Booking booking){
        if(!bookingService.isResourceAvailable(booking.getStartTime(), booking.getEndTime(), resourceId)){
            return ResponseEntity.badRequest().body("Resource is not available for the specified time slot.");
        }else{
            booking.setResourceId(resourceId);
            bookingService.createBooking(booking);
            return ResponseEntity.ok("Resource booked successfully");
        }
    }
}

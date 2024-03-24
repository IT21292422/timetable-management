package com.web.timetable.timetablemanagement;

import static com.mongodb.assertions.Assertions.assertFalse;
import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.web.timetable.timetablemanagement.model.Booking;
import com.web.timetable.timetablemanagement.repository.BookingRepository;
import com.web.timetable.timetablemanagement.service.BookingService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Query;

@RunWith(MockitoJUnitRunner.class)
public class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepo;

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private BookingService bookingService;

    @Before
    public void setUp() {
        // Set up mock behavior for any calls to mongoTemplate.find
        when(mongoTemplate.find(any(Query.class), any(Class.class))).thenReturn(new ArrayList<>());
    }

    @Test
    public void testIsRoomAvailable() {
        // Define the test data
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now().plusHours(1);
        String roomId = "roomId";

        // Call the method under test
        boolean result = bookingService.isRoomAvailable(startTime, endTime, roomId);

        // Verify the result
        assertTrue(result);
    }

    @Test
    public void testIsRoomNotAvailable() {
        // Define the test data
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now().plusHours(1);
        String roomId = "roomId";

        // Mock behavior to return a non-empty list of bookings
        List<Booking> bookings = new ArrayList<>();
        bookings.add(new Booking());
        when(mongoTemplate.find(any(Query.class), any(Class.class))).thenReturn(bookings);

        // Call the method under test
        boolean result = bookingService.isRoomAvailable(startTime, endTime, roomId);

        // Verify the result
        assertFalse(result);
    }
}


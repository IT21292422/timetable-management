package com.web.timetable.timetablemanagement;
import com.web.timetable.timetablemanagement.model.Booking;
import com.web.timetable.timetablemanagement.repository.BookingRepository;
import com.web.timetable.timetablemanagement.service.BookingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepo;

    @InjectMocks
    private BookingService bookingService;

    @Mock
    private MongoTemplate mongoTemplate;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(mongoTemplate.find(any(Query.class), any(Class.class))).thenReturn(new ArrayList<>());
    }

    @Test
    public void testIsRoomAvailable() {
        // Arrange -- given
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now().plusHours(1);
        String roomId = "roomId";

        // Act -- when
        boolean result = bookingService.isRoomAvailable(startTime, endTime, roomId);

        // Assert -- then
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


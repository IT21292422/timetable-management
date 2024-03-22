package com.web.timetable.timetablemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection="bookings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    private String bookingId;
    private String day;
    private String roomId;
    private String resourceId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}

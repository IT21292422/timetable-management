package com.web.timetable.timetablemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="resourceBookings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceBooking extends Booking{
    private String resourceId;
}

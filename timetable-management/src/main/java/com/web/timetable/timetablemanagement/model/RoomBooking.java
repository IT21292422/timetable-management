package com.web.timetable.timetablemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="roomBookings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomBooking extends Booking{
    private String roomId;
}

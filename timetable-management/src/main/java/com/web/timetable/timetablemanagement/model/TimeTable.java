package com.web.timetable.timetablemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "timetables")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeTable {
    @Id
    private String timetableId;
    private String timetableName;
    private List<Course> courses = new ArrayList<>();

}

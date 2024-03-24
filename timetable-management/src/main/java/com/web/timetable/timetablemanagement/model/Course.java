package com.web.timetable.timetablemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection="courses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    private String id;
    private String code;
    private String name;
    private String description;
    private int credits;
    private List<Session> sessions = new ArrayList<>();
    @DBRef
    private Faculty faculty;

}

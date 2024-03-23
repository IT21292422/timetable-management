package com.web.timetable.timetablemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection="roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    private String id;
    private String name; //eg: "Admin", "Faculty", "Student"
    private List<String> permissions = new ArrayList<>(); //eg: "Manage Users", "Manage_Courses", "View_Schedules"
}

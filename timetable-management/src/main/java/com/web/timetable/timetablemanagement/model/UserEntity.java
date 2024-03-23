package com.web.timetable.timetablemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    private String id;

    private String username;
    private String password;
    private List<Role> roles = new ArrayList<>();  //eg: "Admin", "Faculty", "Student"
}

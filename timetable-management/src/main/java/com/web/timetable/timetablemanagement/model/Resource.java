package com.web.timetable.timetablemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="resources")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resource {
    @Id
    private String resourceId;
    private String name;
    private int quantity;
}
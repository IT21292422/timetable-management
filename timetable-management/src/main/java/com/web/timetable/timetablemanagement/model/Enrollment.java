package com.web.timetable.timetablemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "enrollments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment {
    @Id
    private String id;
    private String studentId;
    private String courseId;
    private LocalDateTime enrolledAt;
}

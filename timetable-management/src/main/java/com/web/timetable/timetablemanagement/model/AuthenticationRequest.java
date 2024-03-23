package com.web.timetable.timetablemanagement.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthenticationRequest {
    public String username;
    public String password;
}

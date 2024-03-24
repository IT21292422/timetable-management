package com.web.timetable.timetablemanagement.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthRequestLogin {
    public String username;
    public String password;
}

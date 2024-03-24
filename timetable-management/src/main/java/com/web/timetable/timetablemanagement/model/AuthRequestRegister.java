package com.web.timetable.timetablemanagement.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthRequestRegister {
    public String username;
    public String password;
    public String role;
}

package com.web.timetable.timetablemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TimetableManagementApplication {

	public static void main(String[] args) {

		SpringApplication.run(TimetableManagementApplication.class, args);
		System.out.println("http://localhost:8080/api/course");
	}

}

package com.web.timetable.timetablemanagement.repository;

import com.web.timetable.timetablemanagement.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    UserDetails findByUsername(String username);
}

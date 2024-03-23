package com.web.timetable.timetablemanagement.repository;

import com.web.timetable.timetablemanagement.model.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);
}

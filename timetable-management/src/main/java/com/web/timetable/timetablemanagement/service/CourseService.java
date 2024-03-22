package com.web.timetable.timetablemanagement.service;

import com.web.timetable.timetablemanagement.model.Course;
import com.web.timetable.timetablemanagement.model.Session;
import com.web.timetable.timetablemanagement.repository.CourseRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepo;

    public Course createCourse(Course course){
        return courseRepo.save(course);
    }

    public List<Course> getAllCourses(){
        return courseRepo.findAll();
    }

    public Optional<Course> getCourseById(String courseId){
        String id = courseId.toUpperCase();
        return courseRepo.findCourseByCode(id);
    }

    public Course updateCourse(String id, Course updatedCourse){
        Optional<Course> optionalCourse = courseRepo.findById(id);
        if(optionalCourse.isPresent()){
            Course existingCourse = optionalCourse.get();
            existingCourse.setCode(updatedCourse.getCode());
            existingCourse.setName(updatedCourse.getName());
            existingCourse.setDescription(updatedCourse.getDescription());
            existingCourse.setCredits(updatedCourse.getCredits());
            return courseRepo.save(existingCourse);
        }else{
            return null;
        }
    }

    public void deleteCourse(String id) {
        courseRepo.deleteById(id);
        System.out.println("Course with id " + id + " deleted...");
    }

    public Course assignSessionToCourse(String courseCode, Session session){
        Optional<Course> optionalCourse = getCourseById(courseCode);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            course.getSessions().add(session);
            return courseRepo.save(course);
        } else {
            // Handle the case where the course is not found
            throw new NoSuchElementException("No course found with code: " + courseCode);
        }
    }

    public Course updateSessionInCourse(String courseId, Session updatedSession){
        Optional<Course> optionalCourse = getCourseById(courseId);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            //Find and update the session within the course
            for(Session session: course.getSessions()){
                if(session.getSessionId().equals(updatedSession.getSessionId())){
                    session.setDay(updatedSession.getDay());
                    session.setStartTime(updatedSession.getStartTime());
                    session.setEndTime(updatedSession.getEndTime());
                    session.setFaculty(updatedSession.getFaculty());
                    session.setLocation(updatedSession.getLocation());
                    break;
                }
            }
            return courseRepo.save(course);
        }else {
            // Handle the case where the course is not found
            throw new NoSuchElementException("No course found with code: " + courseId);
        }
    }

    public Course removeSessionFromCourse(String courseCode, String sessionId){
        Optional<Course> optionalCourse = getCourseById(courseCode);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            course.getSessions().removeIf(session -> session.getSessionId().equals(sessionId));
            return courseRepo.save(course);
        }else {
            // Handle the case where the course is not found
            throw new NoSuchElementException("No course found with code: " + courseCode);
        }
    }

}

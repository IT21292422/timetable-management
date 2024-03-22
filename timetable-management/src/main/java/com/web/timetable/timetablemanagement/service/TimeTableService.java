package com.web.timetable.timetablemanagement.service;

import com.web.timetable.timetablemanagement.model.Course;
import com.web.timetable.timetablemanagement.model.TimeTable;
import com.web.timetable.timetablemanagement.repository.CourseRepository;
import com.web.timetable.timetablemanagement.repository.TimeTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TimeTableService {
    @Autowired
    private TimeTableRepository timetableRepo;

    @Autowired
    private CourseService courseService;

    public TimeTable createTimetable(TimeTable timetable){

        return timetableRepo.save(timetable);
    }

    public List<TimeTable> getAllTimetable(){

        return timetableRepo.findAll();
    }

    public Optional<TimeTable> getTimetableById(String timetableId){
        return timetableRepo.findById(timetableId);
    }

    public TimeTable updateTimetable(String id, TimeTable updatedTimetable){
        Optional<TimeTable> optionalTimeTable = timetableRepo.findById(id);
        if(optionalTimeTable.isPresent()){
            TimeTable existingTimetable = optionalTimeTable.get();
            existingTimetable.setTimetableName(updatedTimetable.getTimetableName());
            existingTimetable.setCourses(updatedTimetable.getCourses());
            return timetableRepo.save(existingTimetable);
        }else{
            return null;
        }
    }

    public void deleteTimetable(String id) {
        timetableRepo.deleteById(id);
        System.out.println("Timetable with id " + id + " deleted...");
    }

    public TimeTable assignCourseToTimetable(String timetableId, String courseCode){
        Optional<TimeTable> optionalTimetable = timetableRepo.findById(timetableId);
        TimeTable timetable;
        Course course;
        if(optionalTimetable.isPresent()){
            timetable = optionalTimetable.get();
        }else {
            // Handle the case where the timetable is not found
            throw new NoSuchElementException("No Timetable found with id: " + timetableId);
        }
        Optional<Course> optionalCourse = courseService.getCourseById(courseCode);
        if(optionalCourse.isPresent()){
            course = optionalCourse.get();
        }else {
            // Handle the case where the course is not found
            throw new NoSuchElementException("No Course found with id: " + courseCode);
        }
        timetable.getCourses().add(course);
        return timetableRepo.save(timetable);
    }

    public TimeTable updateCourseInTimetable(String timetableId, Course updatedCourse){
        Optional<TimeTable> optionalTimetable = timetableRepo.findById(timetableId);
        if(optionalTimetable.isPresent()) {
            TimeTable timetable = optionalTimetable.get();
            //Find and update the course within the timetable
            for(Course course : timetable.getCourses()){
                if(course.getCode().equals(updatedCourse.getCode())){
                    course.setName(updatedCourse.getName());
                    course.setDescription(updatedCourse.getDescription());
                    course.setCredits(updatedCourse.getCredits());
                    course.setSessions(updatedCourse.getSessions());
                    break;
                }
            }
            return timetableRepo.save(timetable);
        }else {
            // Handle the case where the timetable is not found
            throw new NoSuchElementException("No Timetable found with id: " + timetableId);
        }
    }

    public TimeTable removeCourseFromTimetable(String timetableId, String courseId){
        Optional<TimeTable> optionalTimetable = timetableRepo.findById(timetableId);
        if(optionalTimetable.isPresent()) {
            TimeTable timetable = optionalTimetable.get();
            timetable.getCourses().removeIf(course -> course.getCode().equals(courseId));
            return timetableRepo.save(timetable);
        }else {
            // Handle the case where the timetable is not found
            throw new NoSuchElementException("No Timetable found with id: " + timetableId);
        }
    }
}

package com.web.timetable.timetablemanagement.service;

import com.web.timetable.timetablemanagement.model.Course;
import com.web.timetable.timetablemanagement.model.TimeTable;
import com.web.timetable.timetablemanagement.repository.CourseRepository;
import com.web.timetable.timetablemanagement.repository.TimeTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimeTableService {
    @Autowired
    private TimeTableRepository timetableRepo;

    @Autowired
    private CourseRepository courseRepo;

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
        TimeTable timetable = optionalTimetable.get();

        Optional<Course> optionalCourse = courseRepo.findById(courseCode);
        Course course = optionalCourse.get();

        timetable.getCourses().add(course);
        return timetableRepo.save(timetable);
    }
}

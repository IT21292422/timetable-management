package com.web.timetable.timetablemanagement.controller;

import com.web.timetable.timetablemanagement.model.TimeTable;
import com.web.timetable.timetablemanagement.service.TimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/timetable")
public class TimeTableController {
    @Autowired
    private TimeTableService timetableService;

    @PostMapping("/createTimetable")
    public TimeTable addTimetable(@RequestBody TimeTable timetable){
        timetableService.createTimetable(timetable);
        return timetable;
    }

    @GetMapping
    public ResponseEntity<List<TimeTable>> getAllTimetable(){
        return new ResponseEntity<List<TimeTable>>(timetableService.getAllTimetable(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<TimeTable>> getTimetableById(@PathVariable String id){
        return new ResponseEntity<Optional<TimeTable>>(timetableService.getTimetableById(id), HttpStatus.OK);
    }

    @PutMapping("/updateTimetable/{id}")
    public TimeTable updateTimetable(@PathVariable String id,@RequestBody TimeTable updatedTimetable){
        timetableService.updateTimetable(id, updatedTimetable);
        return updatedTimetable;
    }

    @DeleteMapping("/deleteTimetable/{id}")
    public void deleteTimetable(@PathVariable String id){

        timetableService.deleteTimetable(id);
    }
}

package com.web.timetable.timetablemanagement.controller;

import com.web.timetable.timetablemanagement.model.Session;
import com.web.timetable.timetablemanagement.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/session")
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @PostMapping("/createSession")
    public Session addSession(@RequestBody Session session){
        sessionService.createSession(session);
        return session;
    }

    @GetMapping
    public ResponseEntity<List<Session>> getAllSession(){
        return new ResponseEntity<List<Session>>(sessionService.getAllSession(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Session>> getSessionById(@PathVariable String id){
        return new ResponseEntity<Optional<Session>>(sessionService.getSessionById(id), HttpStatus.OK);
    }

    @PutMapping("/updateSession/{id}")
    public Session updateSession(@PathVariable String id, @RequestBody Session updatedSession){
        sessionService.updateSession(id, updatedSession);
        return updatedSession;
    }

    @DeleteMapping("/deleteSession/{id}")
    public void deleteSession(@PathVariable String id){

        sessionService.deleteSession(id);
    }
}

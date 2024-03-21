package com.web.timetable.timetablemanagement.service;

import com.web.timetable.timetablemanagement.model.Session;
import com.web.timetable.timetablemanagement.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepo;

    public Session createSession(Session session){

        return sessionRepo.save(session);
    }

    public List<Session> getAllSession(){

        return sessionRepo.findAll();
    }

    public Optional<Session> getSessionById(String sessionId){
        return sessionRepo.findById(sessionId);
    }

    public Session updateSession(String id, Session updatedSession){
        Optional<Session> optionalSession = sessionRepo.findById(id);
        if(optionalSession.isPresent()){
            Session existingSession = optionalSession.get();
            existingSession.setDay(updatedSession.getDay());
            existingSession.setStartTime(updatedSession.getStartTime());
            existingSession.setEndTime(updatedSession.getEndTime());
            existingSession.setFaculty(updatedSession.getFaculty());
            existingSession.setLocation(updatedSession.getLocation());
            return sessionRepo.save(existingSession);
        }else{
            return null;
        }
    }

    public void deleteSession(String id) {
        sessionRepo.deleteById(id);
        System.out.println("Session with id " + id + " deleted...");
    }

}

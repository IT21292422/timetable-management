package com.web.timetable.timetablemanagement;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.web.timetable.timetablemanagement.model.Session;
import com.web.timetable.timetablemanagement.repository.SessionRepository;
import com.web.timetable.timetablemanagement.service.SessionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SessionServiceTest {

    @Mock
    private SessionRepository sessionRepo;

    @InjectMocks
    private SessionService sessionService;

    @Test
    public void testGetAllSession() {
        // Define the test data
        List<Session> sessionList = new ArrayList<>();
        sessionList.add(new Session("1", "Monday", LocalDateTime.now(), LocalDateTime.now().plusHours(1), "FOC", "A501"));
        sessionList.add(new Session("2", "Tuesday", LocalDateTime.now(), LocalDateTime.now().plusHours(1), "FOC", "A502"));

        // Mock the behavior of the session repository
        when(sessionRepo.findAll()).thenReturn(sessionList);

        // Call the method under test
        List<Session> result = sessionService.getAllSession();

        // Verify the result
        assertEquals(sessionList.size(), result.size());
    }

    @Test
    public void testUpdateSession() {
        // Define the test data
        String sessionId = "1";
        Session existingSession = new Session("1", "Monday", LocalDateTime.now(), LocalDateTime.now().plusHours(1), "FOC", "A501");
        Session updatedSession = new Session("1", "Tuesday", LocalDateTime.now(), LocalDateTime.now().plusHours(2), "FOC", "A502");

        // Mock the behavior of the session repository
        when(sessionRepo.findById(sessionId)).thenReturn(Optional.of(existingSession));
        when(sessionRepo.save(any(Session.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Call the method under test
        Session result = sessionService.updateSession(sessionId, updatedSession);

        // Verify the result
        assertEquals(updatedSession.getDay(), result.getDay());
        assertEquals(updatedSession.getStartTime(), result.getStartTime());
        assertEquals(updatedSession.getEndTime(), result.getEndTime());
        assertEquals(updatedSession.getFaculty(), result.getFaculty());
        assertEquals(updatedSession.getLocation(), result.getLocation());
    }
}


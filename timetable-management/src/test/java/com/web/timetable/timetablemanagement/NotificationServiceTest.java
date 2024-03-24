package com.web.timetable.timetablemanagement;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.web.timetable.timetablemanagement.model.Notification;
import com.web.timetable.timetablemanagement.repository.NotificationRepository;
import com.web.timetable.timetablemanagement.service.NotificationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepo;

    @InjectMocks
    private NotificationService notificationService;

    @Test
    public void testGetAllNotifications() {
        // Define the test data
        List<Notification> notificationList = new ArrayList<>();
        notificationList.add(new Notification("1", "Type1", "Message1", LocalDateTime.now()));
        notificationList.add(new Notification("2", "Type2", "Message2", LocalDateTime.now()));

        // Mock the behavior of the notification repository
        when(notificationRepo.findAll()).thenReturn(notificationList);

        // Call the method under test
        List<Notification> result = notificationService.getAllNotification();

        // Verify the result
        assertEquals(notificationList.size(), result.size());
    }

    @Test
    public void testUpdateNotification() {
        // Define the test data
        String notificationId = "1";
        Notification existingNotification = new Notification("1", "Type1", "Message1", LocalDateTime.now());
        Notification updatedNotification = new Notification("1", "Type2", "Updated Message", LocalDateTime.now());

        // Mock the behavior of the notification repository
        when(notificationRepo.findById(notificationId)).thenReturn(Optional.of(existingNotification));
        when(notificationRepo.save(any(Notification.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Call the method under test
        Notification result = notificationService.updateNotification(notificationId, updatedNotification);

        // Verify the result
        assertEquals(updatedNotification.getType(), result.getType());
        assertEquals(updatedNotification.getMessage(), result.getMessage());
    }
}


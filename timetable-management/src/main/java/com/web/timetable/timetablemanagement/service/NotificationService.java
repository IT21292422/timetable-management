package com.web.timetable.timetablemanagement.service;

import com.web.timetable.timetablemanagement.model.Notification;
import com.web.timetable.timetablemanagement.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepo;

    public Notification createNotification(Notification notification){

        return notificationRepo.save(notification);
    }

    public List<Notification> getAllNotification(){

        return notificationRepo.findAll();
    }

    public Optional<Notification> getNotificationById(String notificationId){
        return notificationRepo.findById(notificationId);
    }

    public Notification updateNotification(String id, Notification updatedNotification){
        Optional<Notification> optionalNotification = notificationRepo.findById(id);
        if(optionalNotification.isPresent()){
            Notification existingNotification = optionalNotification.get();
            existingNotification.setType(updatedNotification.getType());
            existingNotification.setMessage(updatedNotification.getMessage());
            existingNotification.setCreatedAt(updatedNotification.getCreatedAt());
            return notificationRepo.save(existingNotification);
        }else{
            return null;
        }
    }

    public void deleteNotification(String id) {
        notificationRepo.deleteById(id);
        System.out.println("Notification with id " + id + " deleted...");
    }
}

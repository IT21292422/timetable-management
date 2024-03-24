package com.web.timetable.timetablemanagement.controller;

import com.web.timetable.timetablemanagement.model.Notification;
import com.web.timetable.timetablemanagement.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    @Autowired
    private NotificationService notifyService;

    @PostMapping("/create")
    public Notification addNotification(@RequestBody Notification notification){
        notifyService.createNotification(notification);
        return notification;
    }

    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotification(){
        return new ResponseEntity<List<Notification>>(notifyService.getAllNotification(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Notification>> getNotificationById(@PathVariable String id){
        return new ResponseEntity<Optional<Notification>>(notifyService.getNotificationById(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public Notification updateNotification(@PathVariable String id,@RequestBody Notification updatedNotification){
        notifyService.updateNotification(id, updatedNotification);
        return updatedNotification;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteNotification(@PathVariable String id){

        notifyService.deleteNotification(id);
    }
}

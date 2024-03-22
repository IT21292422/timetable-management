package com.web.timetable.timetablemanagement.controller;

import com.web.timetable.timetablemanagement.model.Room;
import com.web.timetable.timetablemanagement.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @PostMapping("/createRoom")
    public Room addRoom(@RequestBody Room room){
        roomService.createRoom(room);
        return room;
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAllRoom(){
        return new ResponseEntity<List<Room>>(roomService.getAllRooms(), HttpStatus.OK);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<Optional<Room>> getRoomById(@PathVariable String roomId){
        return new ResponseEntity<Optional<Room>>(roomService.getRoomById(roomId), HttpStatus.OK);
    }

    @PutMapping("/updateRoom/{id}")
    public Room updateRoom(@PathVariable String id,@RequestBody Room updatedRoom){
        roomService.updateRoom(id, updatedRoom);
        return updatedRoom;
    }

    @DeleteMapping("/deleteRoom{id}")
    public void deleteRoom(@PathVariable String id){
        roomService.deleteRoom(id);
    }
}

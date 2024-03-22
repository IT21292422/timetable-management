package com.web.timetable.timetablemanagement.service;

import com.web.timetable.timetablemanagement.model.Course;
import com.web.timetable.timetablemanagement.model.Room;
import com.web.timetable.timetablemanagement.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepo;

    public Room createRoom(Room room){

        return roomRepo.save(room);
    }

    public List<Room> getAllRooms(){

        return roomRepo.findAll();
    }

    public Optional<Room> getRoomById(String roomId){
        return roomRepo.findById(roomId);
    }

    public Room updateRoom(String id, Room updatedRoom){
        Optional<Room> optionalRoom = roomRepo.findById(id);
        if(optionalRoom.isPresent()){
            Room existingRoom = optionalRoom.get();
            existingRoom.setName(updatedRoom.getName());
            existingRoom.setCapacity(updatedRoom.getCapacity());
            return roomRepo.save(existingRoom);
        }else{
            return null;
        }
    }

    public void deleteRoom(String id) {
        roomRepo.deleteById(id);
        System.out.println("Room with id " + id + " deleted...");
    }
}

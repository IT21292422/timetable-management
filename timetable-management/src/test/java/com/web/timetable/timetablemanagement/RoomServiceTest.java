package com.web.timetable.timetablemanagement;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.web.timetable.timetablemanagement.model.Room;
import com.web.timetable.timetablemanagement.repository.RoomRepository;
import com.web.timetable.timetablemanagement.service.RoomService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RoomServiceTest {

    @Mock
    private RoomRepository roomRepo;

    @InjectMocks
    private RoomService roomService;

    @Test
    public void testCreateRoom(){
        Room room = new Room();
        when(roomRepo.save(room)).thenReturn(room);
        Room createdRoom = roomService.createRoom(room);
        assertEquals(room,createdRoom);
    }

    @Test
    public void testGetAllRooms() {
        // Define the test data
        List<Room> roomList = new ArrayList<>();
        roomList.add(new Room("1", "A501"));
        roomList.add(new Room("2", "A502"));

        // Mock the behavior of the room repository
        when(roomRepo.findAll()).thenReturn(roomList);

        // Call the method under test
        List<Room> result = roomService.getAllRooms();

        // Verify the result
        assertEquals(roomList.size(), result.size());
    }

    @Test
    public void testUpdateRoom() {
        // Define the test data
        String roomId = "1";
        Room existingRoom = new Room("1", "A501");
        Room updatedRoom = new Room("1", "Updated Room");

        // Mock the behavior of the room repository
        when(roomRepo.findById(roomId)).thenReturn(Optional.of(existingRoom));
        when(roomRepo.save(any(Room.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Call the method under test
        Room result = roomService.updateRoom(roomId, updatedRoom);

        // Verify the result
        assertEquals(updatedRoom.getName(), result.getName());
    }

    @Test
    public void testDeleteRoom(){
        String roomId = "1";
        doNothing().when(roomRepo).deleteById(roomId);
        roomService.deleteRoom(roomId);
        verify(roomRepo,times(1)).deleteById(roomId);
    }
}


package org.solvinden.bitehack2020.backend.controller;


import org.solvinden.bitehack2020.backend.dto.RoomInfo;
import org.solvinden.bitehack2020.backend.exception.RoomIdDoesntExistException;
import org.solvinden.bitehack2020.backend.model.PeopleInRoom;
import org.solvinden.bitehack2020.backend.model.Room;
import org.solvinden.bitehack2020.backend.repository.PeopleInRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/room")
public class RoomsController {

    private final PeopleInRoomRepository peopleInRoomRepository;

    @Autowired
    RoomsController(PeopleInRoomRepository peopleInRoomRepository){
        this.peopleInRoomRepository = peopleInRoomRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<RoomInfo> getRoomsInfo(){
        List<PeopleInRoom> peopleInRooms = peopleInRoomRepository.findAll();

        Map<Room, PeopleInRoom> latestPeopleInRoom = new HashMap<>();

        for(PeopleInRoom peopleInRoom: peopleInRooms){
            latestPeopleInRoom.merge(peopleInRoom.room, peopleInRoom, (oldValue, value) ->
                    value.timestamp > oldValue.timestamp ? value : oldValue);
        }

        return latestPeopleInRoom.values().stream()
                .map(this::packIntoRoomInfo)
                .collect(Collectors.toList());
    }

    @RequestMapping(path = "/{roomId}", method = RequestMethod.GET)
    public RoomInfo getRoomInfo(@PathVariable("roomId") int roomId){
        PeopleInRoom peopleInRoom = peopleInRoomRepository.getLatestForRoom(roomId);

        if (peopleInRoom == null){
            throw new RoomIdDoesntExistException(roomId);
        }

        return packIntoRoomInfo(peopleInRoom);
    }

    private RoomInfo packIntoRoomInfo(PeopleInRoom peopleInRoom){
        return new RoomInfo(
                peopleInRoom.room.name,
                peopleInRoom.room.description,
                peopleInRoom.timestamp,
                peopleInRoom.peopleCount);
    }
}

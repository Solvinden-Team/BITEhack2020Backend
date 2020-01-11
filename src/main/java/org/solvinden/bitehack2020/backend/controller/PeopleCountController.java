package org.solvinden.bitehack2020.backend.controller;

import org.solvinden.bitehack2020.backend.dto.PeopleCountMessage;
import org.solvinden.bitehack2020.backend.model.PeopleInRoom;
import org.solvinden.bitehack2020.backend.model.Room;
import org.solvinden.bitehack2020.backend.repository.PeopleInRoomRepository;
import org.solvinden.bitehack2020.backend.repository.RoomsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
public class PeopleCountController {

    private final PeopleInRoomRepository peopleRepository;
    private final RoomsRepository roomsRepository;

    public PeopleCountController(PeopleInRoomRepository peopleRepository, RoomsRepository roomsRepository) {
        this.peopleRepository = peopleRepository;
        this.roomsRepository = roomsRepository;
    }

    @PostMapping("/count/{id}")
    public void peopleInRoom(@PathVariable int id, @RequestBody PeopleCountMessage body) {
        if(roomsRepository.existsById(id)) {
            Room room = roomsRepository.getOne(id);
            PeopleInRoom pir = new PeopleInRoom(body.getTimestamp(), room, body.getPeopleCount());
            peopleRepository.save(pir);
        }
        else {
            String message = String.format("Room %d not found", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, message);
        }
    }
}

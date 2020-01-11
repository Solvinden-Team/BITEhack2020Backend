package org.solvinden.bitehack2020.backend.controller;

import org.solvinden.bitehack2020.backend.message.PeopleCountMessage;
import org.solvinden.bitehack2020.backend.model.PeopleInRoom;
import org.solvinden.bitehack2020.backend.model.Room;
import org.solvinden.bitehack2020.backend.repository.PeopleInRoomRepository;
import org.solvinden.bitehack2020.backend.repository.RoomsRepository;
import org.springframework.web.bind.annotation.*;

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
        Room room = roomsRepository.getOne(id);
        PeopleInRoom pir = new PeopleInRoom(body.getTimestamp(), room, body.getPeopleCount());
        peopleRepository.save(pir);
    }
}

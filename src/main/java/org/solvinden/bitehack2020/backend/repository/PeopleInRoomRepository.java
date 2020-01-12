package org.solvinden.bitehack2020.backend.repository;

import org.solvinden.bitehack2020.backend.model.PeopleInRoom;
import org.solvinden.bitehack2020.backend.model.Room;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PeopleInRoomRepository extends JpaRepository<PeopleInRoom, Integer> {
    @Query("SELECT p FROM PeopleInRoom p WHERE p.room.roomId = ?1 ORDER BY p.timestamp DESC")
    List<PeopleInRoom> getLatestForRoom(int roomId, Pageable pageable);
}

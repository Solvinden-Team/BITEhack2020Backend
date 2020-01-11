package org.solvinden.bitehack2020.backend.repository;

import org.solvinden.bitehack2020.backend.model.PeopleInRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PeopleInRoomRepository extends JpaRepository<PeopleInRoom, Integer> {
    @Query("SELECT p FROM PeopleInRoom p WHERE p.room.roomId = ?1 ORDER BY p.timestamp DESC")
    PeopleInRoom getLatestForRoom(int roomId);
}

package org.solvinden.bitehack2020.backend.repository;

import org.solvinden.bitehack2020.backend.model.PeopleInRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleInRoomRepository extends JpaRepository<PeopleInRoom, Integer> {
}

package org.solvinden.bitehack2020.backend.repository;

import org.solvinden.bitehack2020.backend.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomsRepository extends JpaRepository<Room, Integer> {
}

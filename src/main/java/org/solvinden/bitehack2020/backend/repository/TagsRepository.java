package org.solvinden.bitehack2020.backend.repository;

import org.solvinden.bitehack2020.backend.model.Room;
import org.solvinden.bitehack2020.backend.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagsRepository extends JpaRepository<Tag, Integer> {
}


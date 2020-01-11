package org.solvinden.bitehack2020.backend.model;

import javax.persistence.*;

@Entity
@Table(name="Rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int roomId;

    String name;
    String description;
}

package org.solvinden.bitehack2020.backend.model;

import javax.persistence.*;

@Entity
@Table(name="rooms")
public class Room {

    public Room() {

    }

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int roomId;

    @Column(unique = true, nullable = false)
    public String name;
    public String description;
}

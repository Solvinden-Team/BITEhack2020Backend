package org.solvinden.bitehack2020.backend.model;

import javax.persistence.*;

@Entity
@Table(name="rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int roomId;

    public String name;
    public String description;
}

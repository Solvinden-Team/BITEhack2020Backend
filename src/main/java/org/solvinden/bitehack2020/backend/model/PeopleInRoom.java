package org.solvinden.bitehack2020.backend.model;

import javax.persistence.*;

@Entity
@Table(name="PeopleInRooms")
public class PeopleInRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column(nullable = false)
    long timestamp;

    @ManyToOne
    Room room;

    @Column(nullable = false)
    int peopleCount;
}

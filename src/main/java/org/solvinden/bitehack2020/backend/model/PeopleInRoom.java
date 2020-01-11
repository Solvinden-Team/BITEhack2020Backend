package org.solvinden.bitehack2020.backend.model;

import javax.persistence.*;

@Entity
@Table(name="people_in_rooms")
public class PeopleInRoom {

    public PeopleInRoom() {

    }

    public PeopleInRoom(long timestamp, Room room, int peopleCount) {
        this.timestamp = timestamp;
        this.room = room;
        this.peopleCount = peopleCount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @Column(nullable = false)
    public long timestamp;

    @ManyToOne
    public Room room;

    @Column(nullable = false)
    public int peopleCount;
}

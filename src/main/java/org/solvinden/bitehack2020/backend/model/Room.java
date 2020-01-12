package org.solvinden.bitehack2020.backend.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int roomId;

    @Column(unique = true, nullable = false)
    public String name;
    public String description;

    @ManyToMany
    @JoinTable(name = "room_tags", joinColumns = { @JoinColumn(name = "room_id") },
               inverseJoinColumns = { @JoinColumn(name = "tag_id") })
    public List<Tag> tags;
}

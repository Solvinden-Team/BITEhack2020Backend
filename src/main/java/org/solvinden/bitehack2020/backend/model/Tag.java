package org.solvinden.bitehack2020.backend.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    @Column(unique = true, nullable = false)
    public String name;

    @ManyToMany(mappedBy = "tags")
    public List<Room> rooms;
}

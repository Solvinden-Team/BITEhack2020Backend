package org.solvinden.bitehack2020.backend.dto;

import java.util.List;

public class RoomInfo {
    public final String room;
    public final String roomDescription;
    public final long timestamp;
    public final int peopleCount;
    public final List<String> tags;

    public RoomInfo(String room, String roomDescription, long timestamp, int peopleCount, List<String> tags){
        this.room = room;
        this.roomDescription = roomDescription;
        this.timestamp = timestamp;
        this.peopleCount = peopleCount;
        this.tags = tags;
    }
}

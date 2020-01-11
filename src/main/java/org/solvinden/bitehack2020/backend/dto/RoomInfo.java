package org.solvinden.bitehack2020.backend.dto;

public class RoomInfo {
    public final String room;
    public final String roomDescription;
    public final long timestamp;
    public final int peopleCount;

    public RoomInfo(String room, String roomDescription, long timestamp, int peopleCount){
        this.room = room;
        this.roomDescription = roomDescription;
        this.timestamp = timestamp;
        this.peopleCount = peopleCount;
    }
}

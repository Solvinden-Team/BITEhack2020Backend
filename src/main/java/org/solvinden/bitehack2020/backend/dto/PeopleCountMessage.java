package org.solvinden.bitehack2020.backend.dto;

public class PeopleCountMessage {
    private Long timestamp;

    private int peopleCount;

    public Long getTimestamp() {
        return this.timestamp;
    }

    public int getPeopleCount() {
        return this.peopleCount;
    }
}

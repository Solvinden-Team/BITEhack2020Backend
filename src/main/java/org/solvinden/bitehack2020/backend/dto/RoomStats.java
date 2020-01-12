package org.solvinden.bitehack2020.backend.dto;

import org.solvinden.bitehack2020.backend.stats.RoomLoad;

import java.util.List;

public class RoomStats {

    public RoomStats(RoomLoad currentLoad, List<Integer> incomingLoad) {
        this.currentLoad = currentLoad;
        this.incomingLoad = incomingLoad;
    }

    public RoomLoad currentLoad;
    public List<Integer> incomingLoad;

}

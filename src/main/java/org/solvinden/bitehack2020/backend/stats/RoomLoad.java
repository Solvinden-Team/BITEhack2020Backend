package org.solvinden.bitehack2020.backend.stats;

public enum RoomLoad {
    Low, Medium, High;

    public static RoomLoad fromInt(int dp) {
        if(dp < -1) {
            return Low;
        }
        if(dp > 1) {
            return High;
        }
        return Medium;
    }
}

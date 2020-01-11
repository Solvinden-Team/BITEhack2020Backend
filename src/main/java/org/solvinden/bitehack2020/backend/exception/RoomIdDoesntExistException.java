package org.solvinden.bitehack2020.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class RoomIdDoesntExistException extends RuntimeException {
    public RoomIdDoesntExistException(int roomId){
        super("There is no room with a roomId of " + roomId + ".");
    }
}

package org.bnp.rovertest.exception;

public class InvalidStartingPosition extends RuntimeException {

    public InvalidStartingPosition(int x, int y) {
        super("Invalid starting position (" + x + "," + y + ")");
    }
}


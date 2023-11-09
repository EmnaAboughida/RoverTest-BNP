package org.bnp.rovertest.exception;

public class InvalidGridSizeException extends RuntimeException {

    public InvalidGridSizeException(int x, int y) {
        super("Grid can not be of size (" + x + "," + y + ")");
    }

}

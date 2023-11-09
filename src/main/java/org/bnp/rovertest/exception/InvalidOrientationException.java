package org.bnp.rovertest.exception;

public class InvalidOrientationException extends RuntimeException {

    public InvalidOrientationException() {
        super("invalid starting orientation !");
    }
}

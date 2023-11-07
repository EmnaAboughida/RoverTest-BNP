package org.bnp.rovertest.entity;

import static org.bnp.rovertest.utils.Constants.WEST;
import static org.bnp.rovertest.utils.Constants.SOUTH;
import static org.bnp.rovertest.utils.Constants.EAST;
import static org.bnp.rovertest.utils.Constants.NORTH;

public class Rover {

    int x;
    int y;
    char orientation;

    public Rover(int x, int y, char orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getOrientation() {
        return orientation;
    }

    public void turnToLeft() {
        orientation = switch (orientation) {
            case WEST -> SOUTH;
            case SOUTH -> EAST;
            case EAST -> NORTH;
            case NORTH -> WEST;
            default -> throw new IllegalArgumentException("Invalid orientation: " + orientation);
        };
    }

    public void turnToRight() {
        orientation = switch (orientation) {
            case WEST -> NORTH;
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            default -> throw new IllegalArgumentException("Invalid orientation: " + orientation);
        };
    }

    public void moveForward(int finalX, int finalY) {
        switch (orientation) {
            case WEST -> x = Math.max(x - 1, 0);
            case NORTH -> y = Math.min(y + 1, finalY);
            case EAST -> x = Math.min(x + 1, finalX);
            case SOUTH -> y = Math.max(y - 1, 0);
            default -> throw new IllegalArgumentException("Invalid orientation: " + orientation);
        };
    }

    @Override
    public String toString() {
        return x + " " + y + " " + orientation;
    }
}

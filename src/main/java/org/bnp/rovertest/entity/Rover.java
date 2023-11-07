package org.bnp.rovertest.entity;

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
            case 'W' -> 'S';
            case 'S' -> 'E';
            case 'E' -> 'N';
            case 'N' -> 'W';
            default -> throw new IllegalArgumentException("Invalid orientation: " + orientation);
        };
    }

    public void turnToRight() {
        orientation = switch (orientation) {
            case 'W' -> 'N';
            case 'N' -> 'E';
            case 'E' -> 'S';
            case 'S' -> 'W';
            default -> throw new IllegalArgumentException("Invalid orientation: " + orientation);
        };
    }

    public void moveForward(int finalX, int finalY) {
        switch (orientation) {
            case 'W' -> x = Math.max(x - 1, 0);
            case 'N' -> y = Math.min(y + 1, finalY);
            case 'E' -> x = Math.min(x + 1, finalX);
            case 'S' -> y = Math.max(y - 1, 0);
            default -> throw new IllegalArgumentException("Invalid orientation: " + orientation);
        };
    }

    @Override
    public String toString() {
        return x + " " + y + " " + orientation;
    }
}

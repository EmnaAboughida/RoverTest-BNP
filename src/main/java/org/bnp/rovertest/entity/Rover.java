package org.bnp.rovertest.entity;

import java.util.logging.Logger;

import static org.bnp.rovertest.utils.Constants.*;

public class Rover {

    public static final Logger LOGGER = Logger.getLogger(Rover.class.getName());
    Position position;
    char orientation;

    public Rover(Position position, char orientation) {
        this.position = position;
        this.orientation = orientation;
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    public Position getPosition() {
        return position;
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
            default -> NONE;
        };
    }

    public void turnToRight() {
        orientation = switch (orientation) {
            case WEST -> NORTH;
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            default -> NONE;
        };
    }

    public void moveForward(Grid grid) {

        if (grid.isOutOfGrid(position, orientation)) {
            LOGGER.warning("Rover is out of grid, it is moved back inside !");
        } else {

            switch (orientation) {
                case WEST -> position.moveX(-1);
                case NORTH -> position.moveY(1);
                case EAST -> position.moveX(1);
                case SOUTH -> position.moveY(-1);
            }

            if (grid.isPositionTaken(position)) {
                LOGGER.info("Rovers collided at position " + position);
            }
        }
    }

    @Override
    public String toString() {
        int x = getX();
        int y = getY();
        return x + " " + y + " " + orientation;
    }
}

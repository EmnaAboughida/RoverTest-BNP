package org.bnp.rovertest.entity;

import java.util.ArrayList;

import static org.bnp.rovertest.utils.Constants.*;
import static org.bnp.rovertest.utils.Constants.NORTH;

public class Grid {

    private int maxX;
    private int maxY;
    ArrayList<Position> takenPositions = new ArrayList<>();

    public Grid(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public boolean isOutOfGrid(Position position, char orientation) {
        return (position.getX() - 1 < 0 && orientation == WEST) || (position.getX() + 1 > maxX && orientation == EAST) ||
                (position.getY() - 1 < 0 && orientation == SOUTH) || (position.getY() + 1 > maxY && orientation == NORTH);
    }

    public boolean isPositionTaken(Position position) {
        return takenPositions.contains(position);
    }

    public void addTakenPosition(Position position) {
        takenPositions.add(position);
    }
}

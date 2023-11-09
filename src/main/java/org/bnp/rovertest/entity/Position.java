package org.bnp.rovertest.entity;

import java.util.Objects;

public class Position {

    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveX(int move) {
        x += move;
    }

    public void moveY(int move) {
        y += move;
    }

    public boolean isValidPosition(Grid grid) {
        return x >= 0 && x <= grid.getMaxX() && y >= 0 && y <= grid.getMaxY();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ')';
    }
}

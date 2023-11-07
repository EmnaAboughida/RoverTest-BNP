import org.bnp.rovertest.entity.Rover;
import org.junit.jupiter.api.Test;

import static org.bnp.rovertest.utils.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoverMovementsTest {

    @Test
    void turnToLeftTest() {
        Rover rover = new Rover(1, 2, WEST);
        rover.turnToLeft();
        assertEquals(SOUTH, rover.getOrientation());
    }

    @Test
    void turnToLeftWithInvalidOrientationTest() {
        Rover rover = new Rover(1, 2, 'G');
        assertThrows(IllegalArgumentException.class, rover::turnToLeft);
    }

    @Test
    void turnToRightTest() {
        Rover rover = new Rover(1, 2, NORTH);
        rover.turnToRight();
        assertEquals(EAST, rover.getOrientation());
    }

    @Test
    void turnToRightWithInvalidOrientationTest() {
        Rover rover = new Rover(1, 2, 'K');
        assertThrows(IllegalArgumentException.class, rover::turnToRight);
    }

    @Test
    void moveForwardTest() {
        Rover rover = new Rover(2, 3, EAST);
        rover.moveForward(5, 5);
        assertEquals(3, rover.getX());
        assertEquals(3, rover.getY());
    }

    @Test
    void moveForwardWithInvalidXTest() {
        Rover rover1 = new Rover(6, 3, EAST);
        rover1.moveForward(5, 5);
        assertEquals(5, rover1.getX());
        assertEquals(3, rover1.getY());

        Rover rover2 = new Rover(5, 5, EAST);
        rover2.moveForward(5, 5);
        assertEquals(5, rover2.getX());
        assertEquals(5, rover2.getY());

        Rover rover3 = new Rover(0, 4, WEST);
        rover3.moveForward(5, 4);
        assertEquals(0, rover3.getX());
        assertEquals(4, rover3.getY());

        Rover rover4 = new Rover(-1, 4, WEST);
        rover4.moveForward(5, 4);
        assertEquals(0, rover4.getX());
        assertEquals(4, rover4.getY());
    }

    @Test
    void moveForwardWithInvalidYTest() {
        Rover rover1 = new Rover(3, 6, NORTH);
        rover1.moveForward(5, 5);
        assertEquals(3, rover1.getX());
        assertEquals(5, rover1.getY());

        Rover rover2 = new Rover(5, 5, NORTH);
        rover2.moveForward(5, 5);
        assertEquals(5, rover2.getX());
        assertEquals(5, rover2.getY());

        Rover rover3 = new Rover(4, 0, SOUTH);
        rover3.moveForward(5, 4);
        assertEquals(4, rover3.getX());
        assertEquals(0, rover3.getY());

        Rover rover4 = new Rover(4, -1, SOUTH);
        rover4.moveForward(5, 4);
        assertEquals(4, rover4.getX());
        assertEquals(0, rover4.getY());
    }


}

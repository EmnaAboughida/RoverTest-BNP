import org.bnp.rovertest.entity.Rover;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoverMovementsTest {

    @Test
    void turnToLeftTest() {
        Rover rover = new Rover(1, 2, 'W');
        rover.turnToLeft();
        assertEquals('S', rover.getOrientation());
    }

    @Test
    void turnToLeftWithInvalidOrientationTest() {
        Rover rover = new Rover(1, 2, 'G');
        assertThrows(IllegalArgumentException.class, rover::turnToLeft);
    }

    @Test
    void turnToRightTest() {
        Rover rover = new Rover(1, 2, 'N');
        rover.turnToRight();
        assertEquals('E', rover.getOrientation());
    }

    @Test
    void turnToRightWithInvalidOrientationTest() {
        Rover rover = new Rover(1, 2, 'K');
        assertThrows(IllegalArgumentException.class, rover::turnToRight);
    }

    @Test
    void moveForwardTest() {
        Rover rover = new Rover(2, 3, 'E');
        rover.moveForward(5, 5);
        assertEquals(3, rover.getX());
        assertEquals(3, rover.getY());
    }

    @Test
    void moveForwardWithInvalidXTest() {
        Rover rover1 = new Rover(6, 3, 'E');
        rover1.moveForward(5, 5);
        assertEquals(5, rover1.getX());
        assertEquals(3, rover1.getY());

        Rover rover2 = new Rover(5, 5, 'E');
        rover2.moveForward(5, 5);
        assertEquals(5, rover2.getX());
        assertEquals(5, rover2.getY());

        Rover rover3 = new Rover(0, 4, 'W');
        rover3.moveForward(5, 4);
        assertEquals(0, rover3.getX());
        assertEquals(4, rover3.getY());

        Rover rover4 = new Rover(-1, 4, 'W');
        rover4.moveForward(5, 4);
        assertEquals(0, rover4.getX());
        assertEquals(4, rover4.getY());
    }

    @Test
    void moveForwardWithInvalidYTest() {
        Rover rover1 = new Rover(3, 6, 'N');
        rover1.moveForward(5, 5);
        assertEquals(3, rover1.getX());
        assertEquals(5, rover1.getY());

        Rover rover2 = new Rover(5, 5, 'N');
        rover2.moveForward(5, 5);
        assertEquals(5, rover2.getX());
        assertEquals(5, rover2.getY());

        Rover rover3 = new Rover(4, 0, 'S');
        rover3.moveForward(5, 4);
        assertEquals(4, rover3.getX());
        assertEquals(0, rover3.getY());

        Rover rover4 = new Rover(4, -1, 'S');
        rover4.moveForward(5, 4);
        assertEquals(4, rover4.getX());
        assertEquals(0, rover4.getY());
    }


}

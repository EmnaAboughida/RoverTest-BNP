import org.bnp.rovertest.entity.Grid;
import org.bnp.rovertest.entity.Position;
import org.bnp.rovertest.entity.Rover;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;

import static org.bnp.rovertest.utils.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoverTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream out = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outputStreamCaptor));
        System.setErr(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void restore() {
        System.setOut(out);
    }

    @Test
    void turnToLeftTest() {
        Position position = new Position(1, 2);
        Rover rover = new Rover(position, WEST);
        rover.turnToLeft();
        assertEquals(SOUTH, rover.getOrientation());
    }

    @Test
    void turnToLeftWithInvalidOrientationTest() {
        Position position = new Position(1, 2);
        Rover rover = new Rover(position, 'G');
        rover.turnToLeft();
        assertEquals(NONE, rover.getOrientation());
    }

    @Test
    void turnToRightTest() {
        Position position = new Position(1, 2);
        Rover rover = new Rover(position, NORTH);
        rover.turnToRight();
        assertEquals(EAST, rover.getOrientation());
    }

    @Test
    void turnToRightWithInvalidOrientationTest() {
        Position position = new Position(1, 2);
        Rover rover = new Rover(position, 'K');
        rover.turnToRight();
        assertEquals(NONE, rover.getOrientation());
    }

    @Test
    void moveForwardTest() {
        Position position = new Position(2, 3);
        Grid grid = new Grid(5, 5);
        Rover rover = new Rover(position, EAST);
        HashMap<Integer, List<Integer>> takenPositions = new HashMap<>();
        takenPositions.put(1, List.of(2, 4));

        rover.moveForward(grid);
        assertEquals(3, rover.getX());
        assertEquals(3, rover.getY());
    }

    @Test
    void moveForwardWithXOutOfGridTest() {
        Grid grid = new Grid(5, 5);

        Position position1 = new Position(5, 3);
        Rover rover1 = new Rover(position1, EAST);
        rover1.moveForward(grid);
        assertEquals(5, rover1.getX());

        Position position2 = new Position(0, 4);
        Rover rover2 = new Rover(position2, WEST);
        rover2.moveForward(grid);
        assertEquals(0, rover2.getX());
    }

    @Test
    void moveForwardWithYOutOfGridTest() {
        Grid grid = new Grid(5, 5);

        Position position1 = new Position(3, 5);
        Rover rover1 = new Rover(position1, NORTH);
        rover1.moveForward(grid);
        assertEquals(5, rover1.getY());

        Position position2 = new Position(4, 0);
        Rover rover2 = new Rover(position2, SOUTH);
        rover2.moveForward(grid);
        assertEquals(0, rover2.getY());

        String errOutput = outputStreamCaptor.toString();
        assertTrue(errOutput.contains("Rover is out of grid, it is moved back inside !"));
    }

    @Test
    void moveForwardWithCollisionTest() {

        Position takenPosition = new Position(3, 3);
        Grid grid = new Grid(5, 5);
        grid.addTakenPosition(takenPosition);
        Position position = new Position(3, 2);

        Rover rover = new Rover(position, NORTH);
        rover.moveForward(grid);

        assertEquals(3, rover.getX());
        assertEquals(3, rover.getY());
    }


}

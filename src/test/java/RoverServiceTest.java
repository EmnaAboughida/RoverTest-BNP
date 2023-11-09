import org.bnp.rovertest.entity.Grid;
import org.bnp.rovertest.entity.Position;
import org.bnp.rovertest.entity.Rover;
import org.bnp.rovertest.exception.InvalidGridSizeException;
import org.bnp.rovertest.exception.InvalidOrientationException;
import org.bnp.rovertest.exception.InvalidStartingPosition;
import org.bnp.rovertest.service.RoverService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.bnp.rovertest.utils.Constants.NORTH;
import static org.junit.jupiter.api.Assertions.*;

public class RoverServiceTest {

    private Scanner validScanner;
    private Scanner invalidGridScanner;
    private Scanner invalidPositionScanner;
    private Scanner invalidOrientationScanner;
    private Scanner emptyCommandsScanner;
    private Scanner invalidCommandsScanner;

    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUp() {
        System.setErr(new PrintStream(errContent));

        String validInput = "5 5\n1 2 N\nLMLMLMLMM";
        validScanner = new Scanner(validInput);

        String invalidGridsize = "-1 5";
        invalidGridScanner = new Scanner(invalidGridsize);

        String invalidStartingPosition = "5 5\n6 6 N\nLMLMLMLMM";
        invalidPositionScanner = new Scanner(invalidStartingPosition);

        String invalidOrientation = "5 5\n1 2 A\nLMLMLMLMM";
        invalidOrientationScanner = new Scanner(invalidOrientation);

        String emptyCommands = "5 5\n1 2 N";
        emptyCommandsScanner = new Scanner(emptyCommands);

        String invalidCommands = "5 5\n1 2 N\nLXLMLMLMM";
        invalidCommandsScanner = new Scanner(invalidCommands);
    }

    @AfterEach
    public void restoreStreams() {
        System.setErr(originalErr);
    }

    @Test
    public void readFileTest() {
        RoverService.readFile(validScanner);
        String expectedOutput = "1 3 N";
        assertEquals(expectedOutput, errContent.toString().trim());
    }

    @Test
    public void readFileWithInvalidGridSizeTest() {
        assertThrows(InvalidGridSizeException.class, () -> RoverService.readFile(invalidGridScanner));
    }

    @Test
    public void readFileWithInvalidStartingPositionTest() {
        assertThrows(InvalidStartingPosition.class, () -> RoverService.readFile(invalidPositionScanner));
    }

    @Test
    public void readFileWithInvalidOrientationTest() {
        assertThrows(InvalidOrientationException.class, () -> RoverService.readFile(invalidOrientationScanner));
    }

    @Test
    public void readFileWithEmptyCommandsTest() {
        RoverService.readFile(emptyCommandsScanner);
        assertTrue(errContent.toString().contains("Empty command sequence !"));
    }

    @Test
    public void readFileWithInvalidCommandsTest() {
        RoverService.readFile(invalidCommandsScanner);
        assertTrue(errContent.toString().contains("X is not a valid instruction !"));
    }

}

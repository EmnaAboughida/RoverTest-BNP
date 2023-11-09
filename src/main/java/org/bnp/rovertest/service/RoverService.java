package org.bnp.rovertest.service;

import org.bnp.rovertest.entity.Grid;
import org.bnp.rovertest.entity.Position;
import org.bnp.rovertest.entity.Rover;
import org.bnp.rovertest.exception.InvalidGridSizeException;
import org.bnp.rovertest.exception.InvalidOrientationException;
import org.bnp.rovertest.exception.InvalidStartingPosition;
import org.bnp.rovertest.utils.Command;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

import static org.bnp.rovertest.utils.Constants.*;

public class RoverService {

    private static final Logger LOGGER = Logger.getLogger(RoverService.class.getName());

    public static void readFile(Scanner fileScanner) {

        //Create the Grid
        Grid grid = CreateGrid(fileScanner);

        //Parse Rovers
        while (fileScanner.hasNext()) {
            //Create Rover
            Rover rover = createRover(fileScanner, grid);

            if (fileScanner.hasNextLine()) {
                fileScanner.nextLine();
            }
            //Move Rover
            String instructions = fileScanner.hasNextLine() ? fileScanner.nextLine() : "";
            if (instructions.isEmpty()) {
                LOGGER.warning("Empty command sequence !");
            } else {
                moveRover(instructions, rover, grid);
            }
            System.out.println(rover);
        }
    }

    private static Grid CreateGrid(Scanner fileScanner) {
        int maxX = getIntArgument(fileScanner);
        int maxY = getIntArgument(fileScanner);

        if (isValidGrid(maxX, maxY)) {
            throw new InvalidGridSizeException(maxX, maxY);
        }
        return new Grid(maxX, maxY);
    }

    private static Rover createRover(Scanner fileScanner, Grid grid) {
        int x = getIntArgument(fileScanner);
        int y = getIntArgument(fileScanner);
        Position initialPosition = new Position(x, y);

        if (!initialPosition.isValidPosition(grid)) {
            throw new InvalidStartingPosition(x, y);
        }

        char orientation = fileScanner.next().charAt(0);

        if (!isValidDirection(orientation)) {
            throw new InvalidOrientationException();
        }

        return new Rover(initialPosition, orientation);
    }

    private static void moveRover(String instructions, Rover rover, Grid grid) {
        String[] instructionsArray = instructions.split("");

        for (String instruction : instructionsArray) {
            switch (instruction) {
                case LEFT -> {
                    Command turnLeftCommand = Rover::turnToLeft;
                    turnLeftCommand.executeCommand(rover);
                }
                case RIGHT -> {
                    Command turnRightCommand = Rover::turnToRight;
                    turnRightCommand.executeCommand(rover);
                }
                case MOVE -> {
                    Command moveForwardCommand = r -> r.moveForward(grid);
                    moveForwardCommand.executeCommand(rover);
                }
                default -> LOGGER.warning(instruction + " is not a valid instruction !");
            }
        }
        grid.addTakenPosition(rover.getPosition());
    }

    private static boolean isValidDirection(char orientation) {
        return orientation == WEST || orientation == NORTH || orientation == EAST || orientation == SOUTH;
    }

    private static boolean isValidGrid(int maxX, int maxY) {
        return (maxX < 0 || maxY < 0) || (maxX + maxY == 0);
    }

    private static int getIntArgument(Scanner fileScanner) {
        if (fileScanner.hasNextInt()) {
            return fileScanner.nextInt();
        } else {
            throw new InputMismatchException("The passed argument is not an integer !");
        }
    }

}

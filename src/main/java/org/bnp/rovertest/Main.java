package org.bnp.rovertest;

import org.bnp.rovertest.entity.Rover;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static org.bnp.rovertest.utils.Constants.LEFT;
import static org.bnp.rovertest.utils.Constants.RIGHT;
import static org.bnp.rovertest.utils.Constants.MOVE;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File(args[0]));

        int finalX = getIntArgument(fileScanner);
        int finalY = getIntArgument(fileScanner);
        fileScanner.nextLine();

        while (fileScanner.hasNext()) {

            int x = getIntArgument(fileScanner);
            int y = getIntArgument(fileScanner);;
            char orientation = fileScanner.next().charAt(0);
            Rover rover = new Rover(x, y, orientation);
            fileScanner.nextLine();

            String instructions = fileScanner.nextLine();
            moveRover(instructions, rover, finalX, finalY);
            System.out.println(rover);
        }
        fileScanner.close();
    }

    public static void moveRover(String instructions, Rover rover, int finalX, int finalY) {
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
                    Command moveForwardCommand = r -> r.moveForward(finalX, finalY);
                    moveForwardCommand.executeCommand(rover);
                }
            }
        }
    }

    public static int getIntArgument(Scanner fileScanner) {
        if (fileScanner.hasNextInt()) {
            return fileScanner.nextInt();
        } else {
            throw new InputMismatchException("The passed argument is not an integer !");
        }
    }
}
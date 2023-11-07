package org.bnp.rovertest;

import org.bnp.rovertest.entity.Rover;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File(args[0]));

        int finalX;
        if (fileScanner.hasNextInt()) {
            finalX = fileScanner.nextInt();
        } else {
            throw new InputMismatchException("The grid x is not an integer");
        }

        int finalY;
        if (fileScanner.hasNextInt()) {
            finalY = fileScanner.nextInt();
        } else {
            throw new InputMismatchException("The grid y is not an integer");
        }

        fileScanner.nextLine();

        while (fileScanner.hasNext()) {

            int x;
            if (fileScanner.hasNextInt()) {
                x = fileScanner.nextInt();
            } else {
                throw new InputMismatchException("The rover x position is not an integer");
            }

            int y;
            if (fileScanner.hasNextInt()) {
                y = fileScanner.nextInt();
            } else {
                throw new InputMismatchException("The rover y position is not an integer");
            }

            char orientation = fileScanner.next().charAt(0);

            Rover rover = new Rover(x, y, orientation);

            fileScanner.nextLine();

            String instructions = fileScanner.nextLine();
            String[] instructionsArray = instructions.split("");

            for (String instruction : instructionsArray) {
                switch (instruction) {
                    case "L" -> {
                        Command turnLeftCommand = Rover::turnToLeft;
                        turnLeftCommand.executeCommand(rover);
                    }
                    case "R" -> {
                        Command turnRightCommand = Rover::turnToRight;
                        turnRightCommand.executeCommand(rover);
                    }
                    case "M" -> {
                        Command moveForwardCommand = r -> r.moveForward(finalX, finalY);
                        moveForwardCommand.executeCommand(rover);
                    }
                }
            }
            System.out.println(rover);
        }
        fileScanner.close();
    }
}
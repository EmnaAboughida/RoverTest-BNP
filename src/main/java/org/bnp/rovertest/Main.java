package org.bnp.rovertest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.bnp.rovertest.service.RoverService.readFile;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File(args[0]));
        readFile(fileScanner);
        fileScanner.close();
    }


}
package org.bnp.rovertest.utils;

import org.bnp.rovertest.entity.Rover;

@FunctionalInterface
public interface Command {
    void executeCommand(Rover rover);
}

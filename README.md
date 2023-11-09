# Mars Rover Mission
## Project Overview
This project simulates the deployment of a squad of robotic rovers on a rectangular plateau on Mars. The rovers navigate the grid and report their positions before headings back to Earth. The mission is to explore Mars and collect data.
## Assumptions and Rules
- File Structure: The input provided to the system must adhere to the expected file structure, where the first line indicates the top-right coordinates of the grid, and the subsequent lines provide the initial positions, initial direction and commands for each rover.
- Rover Collision: If rovers' paths intersect, a warning message will be logged, indicating a collision. The rover that has encountered another rover will continue executing subsequent commands.
- Out-of-Grid Movement: When a rover attempt to move outside the grid, a warning will be logged. The rover will be repositioned within the boundaries of the grid before continuing its command sequence.
- Initial Position Validation: If a rover is initialized with a position outside the grid, the system will throw an exception, indicating an invalid initial position.
- Grid Size Validation: The system expects a non-zero, positive grid size. Any attempt to initialize the grid with zero or negative dimensions will result in an exception.
- Orientation Validation: A rover's initial orientation must be a valid cardinal point (N, E, S, W). An invalid initial orientation will result in an exception.
- Command Sequence Validation: An empty initial command sequence for a rover will result in a warning message, and the rover will not move.
- Invalid Instructions: If commands contains invalid instructions (any character other than 'L', 'R', or 'M'), a warning message will be logged for each invalid instruction, and the instruction will be ignored.
- Position Input Validation: Rover positions must be valid integers. Any non-integer input will cause the system to throw an exception.
## Input/Output Format
- Input is provided through a text file, where the first line specifies the plateau's upper-right coordinates, followed by rover deployment information.
- Each rover's deployment information consists of two lines: the first for the initial position and the direction and the second for the command sequence.
- The output will be the final coordinates and heading for each rover after executing its command sequence.

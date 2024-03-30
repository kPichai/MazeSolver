/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam & Kieran P. (CS2 G Block)
 * @version 03/10/2023
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // Creates all the variables necessary to get the solution
        ArrayList<MazeCell> solution = new ArrayList<MazeCell>();
        Stack<MazeCell> path = new Stack<MazeCell>();
        MazeCell curCell = maze.getEndCell();

        // Loops through the parents and adds them to the stack until you reach the beginning cell
        while (!curCell.equals(maze.getStartCell())) {
            path.push(curCell);
            curCell = curCell.getParent();
        }

        // Adds the beginning cell because it the while loop quit right as we reached it
        path.push(maze.getStartCell());

        // Reverses the solution using the stack
        while (!path.empty()) {
            solution.add(path.pop());
        }

        // Returns our final, correctly ordered path to the answer
        return solution;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // Creates all necessary variables to keep track of current position and cells
        Stack<MazeCell> cellsToExplore = new Stack<MazeCell>();
        MazeCell currentCell = maze.getStartCell();
        int row, col;

        // While loop that continues going through the maze until it reaches the end
        while (!currentCell.equals(maze.getEndCell())) {
            // Row and col so that we dont have to recall the getRow and getCol methods every time
            row = currentCell.getRow();
            col = currentCell.getCol();

            // Checks the north cell to see if its valid
            if (maze.isValidCell(row - 1, col)) {
                // If the north cell is valid, updates its information
                cellsToExplore.push(maze.getCell(row - 1, col));
                updateCellInfo(row - 1, col, currentCell, maze);
            }

            // Checks the east cell to see if its valid
            if (maze.isValidCell(row, col + 1)) {
                // If the east cell is valid, updates its information
                cellsToExplore.push(maze.getCell(row, col + 1));
                updateCellInfo(row, col + 1, currentCell, maze);
            }

            // Checks the south cell to see if its valid
            if (maze.isValidCell(row + 1, col)) {
                // If the south cell is valid, updates its information
                cellsToExplore.push(maze.getCell(row + 1, col));
                updateCellInfo(row + 1, col, currentCell, maze);
            }

            // Checks the west cell to see if its valid
            if (maze.isValidCell(row, col - 1)) {
                // If the west cell is valid, updates its information
                cellsToExplore.push(maze.getCell(row, col - 1));
                updateCellInfo(row, col - 1, currentCell, maze);
            }

            // Updates the location of the current cell so that the code moves along the maze
            currentCell = cellsToExplore.pop();
        }

        // Returns the final solution for this specific maze
        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // Creates all necessary variables to keep track of current position and cells
        Queue<MazeCell> cellsToExplore = new LinkedList<MazeCell>();
        MazeCell currentCell = maze.getStartCell();
        int row, col;

        // While loop that continues going through the maze until it reaches the end
        while (!currentCell.equals(maze.getEndCell())) {
            // Row and col so that we dont have to recall the getRow and getCol methods every time
            row = currentCell.getRow();
            col = currentCell.getCol();

            // Checks the north cell to see if its valid
            if (maze.isValidCell(row - 1, col)) {
                // If the north cell is valid, updates its information
                cellsToExplore.add(maze.getCell(row - 1, col));
                updateCellInfo(row - 1, col, currentCell, maze);
            }

            // Checks the east cell to see if its valid
            if (maze.isValidCell(row, col + 1)) {
                // If the east cell is valid, updates its information
                cellsToExplore.add(maze.getCell(row, col + 1));
                updateCellInfo(row, col + 1, currentCell, maze);
            }

            // Checks the south cell to see if its valid
            if (maze.isValidCell(row + 1, col)) {
                // If the south cell is valid, updates its information
                cellsToExplore.add(maze.getCell(row + 1, col));
                updateCellInfo(row + 1, col, currentCell, maze);
            }

            // Checks the west cell to see if its valid
            if (maze.isValidCell(row, col - 1)) {
                // If the west cell is valid, updates its information
                cellsToExplore.add(maze.getCell(row, col - 1));
                updateCellInfo(row, col - 1, currentCell, maze);
            }

            // Updates the location of the current cell to next in line
            currentCell = cellsToExplore.remove();
        }

        // Returns the final solution for this specific maze
        return getSolution();
    }

    // updateCellInfo method updates cell information to keep track of current and past cells
    public static void updateCellInfo(int row, int col, MazeCell cur, Maze m) {
        // Sets cell at row and col to true for explored so we don't repeat that cell
        m.getCell(row, col).setExplored(true);
        // Sets the same cell to have a parent of the MazeCell passed in so you can backtrack to find a solution
        m.getCell(row, col).setParent(cur);
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();
        System.out.println("\n");

        // Solve the maze using BFS and print the solution
        ArrayList<MazeCell> sol1 = ms.solveMazeBFS();
        maze.printSolution(sol1);
    }
}

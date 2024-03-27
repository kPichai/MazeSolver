/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */
// Kieran Pichai - G Block CS2
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
        ArrayList<MazeCell> solution = new ArrayList<MazeCell>();
        Stack<MazeCell> path = new Stack<MazeCell>();
        MazeCell curCell = maze.getEndCell();
        while (!curCell.equals(maze.getStartCell())) {
            path.push(curCell);
            curCell = curCell.getParent();
        }
        path.push(maze.getStartCell());
        while (!path.empty()) {
            solution.add(path.pop());
        }
        return solution;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        return null;
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        Queue<MazeCell> cellsToExplore = new LinkedList<MazeCell>();
        MazeCell currentCell = maze.getStartCell();
        while (!currentCell.equals(maze.getEndCell())) {
            if (maze.isValidCell(currentCell.getRow() - 1, currentCell.getCol())) {
                cellsToExplore.add(maze.getCell(currentCell.getRow() - 1, currentCell.getCol()));
            } else if (maze.isValidCell(currentCell.getRow(), currentCell.getCol() + 1)) {
                cellsToExplore.add(maze.getCell(currentCell.getRow(), currentCell.getCol() + 1));
            } else if (maze.isValidCell(currentCell.getRow() + 1, currentCell.getCol())) {
                cellsToExplore.add(maze.getCell(currentCell.getRow() + 1, currentCell.getCol()));
            } else if (maze.isValidCell(currentCell.getRow(), currentCell.getCol() - 1)) {
                cellsToExplore.add(maze.getCell(currentCell.getRow(), currentCell.getCol() - 1));
            }
            cellsToExplore.peek().setParent(currentCell);
            cellsToExplore.peek().setExplored(true);
            currentCell = cellsToExplore.remove();
        }
        return getSolution();
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
//        ArrayList<MazeCell> sol = ms.solveMazeDFS();
//        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        ArrayList<MazeCell> sol1 = ms.solveMazeBFS();
        maze.printSolution(sol1);
    }
}

/**
 * An implementation of the Knights Tour problem.
 * Copyright (c) 2016 Saminda Peramuna.
 *
 * Distributed under GNU GPL. You're free to use the code as
 * long as you mention the previous authors.
 *
 */
package knightsjourney;

import java.util.Random;

public class KnightsJourney {

    private static final Random RANDOM = new Random();
    private static final int GRID_SIZE = 8;
    private static final int MAX_TRIES = 10000;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int totalRuns = 0;
        int maxMoves = 0;
        int moves = 0;
        int tries = 0;
        int[][] grid = new int[GRID_SIZE][GRID_SIZE];

        // Randomize the starting coordinates.
        int row_y = getNewCoordinate();
        int col_x = getNewCoordinate();

        // Put the initial coords in the grid.
        grid[row_y][col_x] = 1;

        // Printing the initial grid.
        System.out.println("Knights current position : (" + col_x + "," + row_y + ")");
        System.out.println("\nInitial grid. Moves : " + moves + "\n");
        printCurrentGridState(grid);

        // Let the journey begin.
        while (!isJouneyComplete(grid)) {
            int row = getNewCoordinate();
            int col = getNewCoordinate();

            // System.out.println("Generated coordinates : (" + col + "," + row + ")");
            // Make a move if it's valid.
            while (isValidMove(grid, row_y, col_x, row, col)) {
                row_y = row;
                col_x = col;

                grid[row_y][col_x] = 1;

                ++moves;
            }

            ++tries;
            if (maxMoves < moves) {
                maxMoves = moves;
            }

            // Reset board if exceeded max tries.
            if (tries == MAX_TRIES) {
                System.out.println("\nResetting Board.");
                System.out.println("\nCurrent grid. Moves : " + moves + "\n");
                printCurrentGridState(grid);

                grid = new int[GRID_SIZE][GRID_SIZE];
                totalRuns++;
                moves = 0;
                tries = 0;
                row_y = getNewCoordinate();
                col_x = getNewCoordinate();
            }
        }

        // Printing the final grid.
        System.out.println("\nFinal grid. Moves : " + moves + "\n");
        System.out.println("Max moves : " + maxMoves + "\n");
        System.out.println("Times failed : " + (totalRuns - 1) + "\n");
        printCurrentGridState(grid);
    }

    /**
     * Get the randomized coordinate.
     *
     * @return
     */
    private static int getNewCoordinate() {
        return RANDOM.nextInt(GRID_SIZE);
    }

    /**
     * Get whether the journey is complete or not.
     *
     * @param grid
     * @return
     */
    private static boolean isJouneyComplete(int[][] grid) {
        for (int[] gridColumn : grid) {
            for (int col = 0; col < gridColumn.length; col++) {
                if (gridColumn[col] == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Evaluate whether the current move is valid.
     *
     * @param currentRow
     * @param currentCol
     * @param newRow
     * @param newCol
     * @return
     */
    private static boolean isValidMove(int[][] grid, int currentRow, int currentCol, int newRow, int newCol) {
        // Reject if already visited;
        if (grid[newRow][newCol] == 1) {
            return false;
        }

        // Check for out of board row values.
        if (newRow < currentRow) {
            if (currentRow - newRow < 0) {
                return false;
            }
        } else if (newRow - currentRow < 0) {
            return false;
        }

        // Check for out of board col values.
        if (newCol < currentCol) {
            if (currentCol - newCol < 0) {
                return false;
            }
        } else if (newCol - currentCol < 0) {
            return false;
        }

        // Check for a valid movement.
        if ((newRow - currentRow == 2) || (newRow - currentRow == -2)) {
            if ((newCol - currentCol == 1) || (newCol - currentCol == -1)) {
                return true;
            }
        } else if ((newRow - currentRow == 1) || (newRow - currentRow == -1)) {
            if ((newCol - currentCol == 2) || (newCol - currentCol == -2)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Print the current grid state.
     *
     * @param grid
     */
    private static void printCurrentGridState(int[][] grid) {
        for (int[] gridColumn : grid) {
            for (int col = 0; col < gridColumn.length; col++) {
                System.out.print(gridColumn[col] + "  ");
            }
            System.out.println();
        }
    }
}

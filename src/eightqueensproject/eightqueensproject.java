//Dustin Wallace
//ITCS 3153 Intro to AI
//Program Assignment #1

package eightqueensproject;

import java.util.*;

public class eightqueensproject {

	public static final int boardSize = 8; // indicates the size of the board
	public static int board[][]; // boolean array to determine which spots are taken by queens
	public static int h = 1; // "h" is the heuristic value for the board, or how many conflicts are on the
								// board
	public static int counter = 0; // this int counts the number of queens in a single row
	public static boolean unsolved = true;
	public static int restarts = 0;
	public static int stateChanges = 0;

	/**
	 * This program places 8 queens along an 8x8 board where none of the queens are
	 * in conflict with each other. Solution implemented by using the Hill-Climbing
	 * algorithm with random restarts.
	 */
	public static void main(String[] args) {

		// Begin by assuming the first board is solvable. Restart will take place at the
		// end if board is unsolvable.
		while (h != 0) {

			// Create the chess board according to board size
			Random rand = new Random();
			board = new int[boardSize][boardSize];
			h = 0;
			System.out.println("Current h: " + h);
			System.out.println("Current State");

			// Populate the chess board with a single queen in each row.
			// Queens are denoted by 1's
			for (int i = 0; i < 8; i++) {
				board[rand.nextInt(8)][i] = 1;
			}
			// this for loop iterates through the board and prints each element
			for (int row = 0; row < board.length; row++) {
				for (int column = 0; column < board[row].length; column++) {
					System.out.print(board[row][column] + ", ");
				}
				System.out.println();
			}

			// --- ROW CHECKING ---//
			// this for loop iterates through each row and checks the number of queens in
			// conflict
			for (int row = 0; row < board.length; row++) {
				for (int column = 0; column < board[row].length; column++) {
					if (board[row][column] == 1) {
						counter++;
						if (counter >= 2) {
							h += (counter - 1);
						}
					}
				}
				counter = 0;
			}
			// --- END ROW CHECKING ---//

			// --- DIAGONAL CHECKING ---//
			// these two loops iterate through the downward diagonals and check the number
			// of conflicts
			for (int row = 7; row >= 0; row--) {
				for (int column = 0; column < 8 - row; column++) {
					if (board[row + column][column] == 1) {
						counter++;
					}
				}
				if (counter >= 2) {
					h += (counter - 1);
				}
				counter = 0;
			}

			for (int row = 7; row > 0; row--) {
				for (int column = 0; column < 8 - row; column++) {
					if (board[column][row + column] == 1) {
						counter++;
					}
				}
				if (counter >= 2) {
					h += (counter - 1);
				}
				counter = 0;
			}

			// upward diagonal checking
			// Holy Moly this part is difficult
			//
			// for (int row = 0; row < 7; row++) {
			// for (int column = 8; column > row; column--) {
			// if (board[row + 1][column - 1] == 1) {
			// counter++;
			// }
			// }
			// }
			// if (counter >= 2) {
			// h += (counter - 1);
			// }
			// counter = 0;

			// Print out the heuristic value and tell the user the system is restarting
			System.out.println("The heuristic value is " + h);
			System.out.println("");
			// increment restarts for the final result
			restarts++;
			System.out.println("Setting new current state");
		}
		// Print off the final results
		System.out.println("Solution found!");
		System.out.println("Restarts: " + restarts);
	}
}

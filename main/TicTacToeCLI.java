package main;

import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToeCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board(); // Assume your `Board` class has a default constructor initializing an empty board
        CPUPlayer cpuPlayer = new CPUPlayer(Mark.X); // AI plays as 'O'
        boolean isPlayerTurn = true; // Player starts first
        Mark playerMark = Mark.O; // Player is 'X'

        System.out.println("Welcome to Tic-Tac-Toe! You are 'O'.");
        board.printBoard(); // Add a method in your Board class to display the board

        while (board.isMovesLeft()) {
            if (isPlayerTurn) {
                System.out.println("Your turn. Enter your move (row and column, 0-2):");
                int row = scanner.nextInt();
                int col = scanner.nextInt();

                if (board.isValidMove(row, col)) { // Ensure the move is valid
                    board.play(new Move(row, col), playerMark);
                    isPlayerTurn = false;
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            } else {
                System.out.println("AI is making its move...");
                ArrayList<Move> aiMoves = cpuPlayer.getNextMoveMinMax(board, true, Mark.X, CPUPlayer.MAX_DEPTH);
                System.out.println(aiMoves);
                Move bestMove = aiMoves.get(0); // Take the first move (guaranteed winning or optimal)
                board.play(bestMove, Mark.X);
                System.out.println("AI played at (" + bestMove.getRow() + ", " + bestMove.getCol() + ").");
                isPlayerTurn = true;
            }

            board.printBoard(); // Display the board after each move

            // Check for a winner
            int score = board.evaluate(playerMark);
            if (score == 100) {
                System.out.println("Congratulations, you win!");
                break;
            } else if (score == -100) {
                System.out.println("AI wins! Better luck next time.");
                break;
            }
        }

        if (!board.isMovesLeft() && board.evaluate(playerMark) == 0) {
            System.out.println("It's a draw!");
        }

        scanner.close();
    }
}

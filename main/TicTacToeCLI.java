package main;

import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToeCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        CPUPlayer cpuPlayer = new CPUPlayer(Mark.X);
        boolean isPlayerTurn = true;
        Mark playerMark = Mark.O;

        // Ask user to choose algorithm
        System.out.println("Welcome to Tic-Tac-Toe! You are 'O'.");
        System.out.println("Choose AI algorithm:");
        System.out.println("1. MinMax");
        System.out.println("2. Alpha-Beta Pruning");

        int algorithmChoice;
        do {
            System.out.print("Enter your choice (1 or 2): ");
            algorithmChoice = scanner.nextInt();
        } while (algorithmChoice != 1 && algorithmChoice != 2);

        board.printBoard();

        while (board.isMovesLeft()) {
            if (isPlayerTurn) {
                System.out.println("Your turn. Enter your move (row and column, 0-2):");
                int row = scanner.nextInt();
                int col = scanner.nextInt();

                if (board.isValidMove(row, col)) {
                    board.play(new Move(row, col), playerMark);
                    isPlayerTurn = false;
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            } else {
                System.out.println("AI is making its move...");
                ArrayList<Move> aiMoves;

                // Use chosen algorithm
                if (algorithmChoice == 1) {
                    aiMoves = cpuPlayer.getNextMoveMinMax(board);
                    System.out.println("Using MinMax algorithm");
                } else {
                    aiMoves = cpuPlayer.getNextMoveAB(board);
                    System.out.println("Using Alpha-Beta Pruning algorithm");
                }

                System.out.println(aiMoves);
                Move bestMove = aiMoves.get(0);
                board.play(bestMove, Mark.X);
                System.out.println("AI played at (" + bestMove.getRow() + ", " + bestMove.getCol() + ")");
                System.out.println("Number of nodes explored: " + cpuPlayer.getNumExploredNodes());
                isPlayerTurn = true;
            }

            board.printBoard();

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
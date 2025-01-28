package main;

import java.util.ArrayList;
import java.util.List;

// IMPORTANT: Il ne faut pas changer la signature des méthodes
// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait 
// être le cas)
public class CPUPlayer
{

    // Contient le nombre de noeuds visités (le nombre
    // d'appel à la fonction MinMax ou Alpha Beta)
    // Normalement, la variable devrait être incrémentée
    // au début de votre MinMax ou Alpha Beta.
    private int numExploredNodes;
    public static final int MAX_DEPTH = 9; // You can adjust this value
    private Mark cpu; // Store the CPU's mark (X or O)

    public int getNumExploredNodes() {
        return numExploredNodes;
    }

    // Le constructeur reçoit en paramètre le
    // joueur MAX (X ou O)
    public CPUPlayer(Mark cpu){
        this.cpu = cpu;
    }


    private ArrayList<Move> getNextMoveMinMaxHelper(Board board, boolean isMax, Mark originalPlayer, int depth) {
        ArrayList<Move> bestMoves = new ArrayList<>();
        this.numExploredNodes++;

        // Terminal condition or max depth
        if (depth == 0 || !board.isMovesLeft()) {
            int score = board.evaluate(originalPlayer);
            return new ArrayList<>(List.of(new Move(-1, -1, score)));
        }

        Mark[][] boardArray = board.getBoardArray();
        int bestScore = isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board.isValidMove(row, col)) {
                    Board newBoard = new Board(boardArray);
                    newBoard.play(new Move(row, col), isMax ? Mark.X : Mark.O);

                    // Check for immediate win/loss after this move
                    int immediateScore = newBoard.evaluate(originalPlayer);
                    int currentScore;

                    if (immediateScore == 100 || immediateScore == -100) {
                        currentScore = immediateScore;
                    } else {
                        // If no immediate win/loss, continue searching
                        currentScore = getNextMoveMinMaxHelper(newBoard, !isMax, originalPlayer, depth - 1)
                                .get(0)
                                .getScore();
                    }

                    // Update best moves based on whether we're maximizing or minimizing
                    if (isMax) {
                        if (currentScore > bestScore) {
                            bestScore = currentScore;
                            bestMoves.clear();
                            bestMoves.add(new Move(row, col, bestScore));
                        } else if (currentScore == bestScore) {
                            bestMoves.add(new Move(row, col, bestScore));
                        }
                    } else {
                        if (currentScore < bestScore) {
                            bestScore = currentScore;
                            bestMoves.clear();
                            bestMoves.add(new Move(row, col, bestScore));
                        } else if (currentScore == bestScore) {
                            bestMoves.add(new Move(row, col, bestScore));
                        }
                    }
                }
            }
        }
        return bestMoves;
    }

    // Ne pas changer cette méthode
    public ArrayList<Move> getNextMoveMinMax(Board board) {
        numExploredNodes = 0;
        // Call the helper function with all necessary parameters
        return getNextMoveMinMaxHelper(board, true, cpu, MAX_DEPTH);
    }

    private ArrayList<Move> getNextMoveABHelper(Board board, boolean isMax, Mark originalPlayer, int depth, int alpha, int beta) {
        ArrayList<Move> bestMoves = new ArrayList<>();
        this.numExploredNodes++;

        // Terminal condition or max depth
        if (depth == 0 || !board.isMovesLeft()) {
            int score = board.evaluate(originalPlayer);
            return new ArrayList<>(List.of(new Move(-1, -1, score)));
        }

        Mark[][] boardArray = board.getBoardArray();
        int bestScore = isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board.isValidMove(row, col)) {
                    Board newBoard = new Board(boardArray);
                    newBoard.play(new Move(row, col), isMax ? Mark.X : Mark.O);

                    // Check for immediate win/loss after this move
                    int immediateScore = newBoard.evaluate(originalPlayer);
                    int currentScore;

                    if (immediateScore == 100 || immediateScore == -100) {
                        currentScore = immediateScore;
                    } else {
                        // If no immediate win/loss, continue searching
                        currentScore = getNextMoveABHelper(newBoard, !isMax, originalPlayer, depth - 1, alpha, beta)
                                .get(0)
                                .getScore();
                    }

                    // Update best moves based on whether we're maximizing or minimizing
                    if (isMax) {
                        if (currentScore > bestScore) {
                            bestScore = currentScore;
                            bestMoves.clear();
                            bestMoves.add(new Move(row, col, bestScore));
                        } else if (currentScore == bestScore) {
                            bestMoves.add(new Move(row, col, bestScore));
                        }
                        alpha = Math.max(alpha, bestScore);
                    } else {
                        if (currentScore < bestScore) {
                            bestScore = currentScore;
                            bestMoves.clear();
                            bestMoves.add(new Move(row, col, bestScore));
                        } else if (currentScore == bestScore) {
                            bestMoves.add(new Move(row, col, bestScore));
                        }
                        beta = Math.min(beta, bestScore);
                    }

                    // Alpha-beta pruning
                    if (beta <= alpha) {
                        break;
                    }
                }
            }
        }
        return bestMoves;
    }



    // Retourne la liste des coups possibles.  Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveAB(Board board){
        //increment everytime
        numExploredNodes = 0;

        return getNextMoveABHelper(board, true, cpu, MAX_DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE);

    }

}

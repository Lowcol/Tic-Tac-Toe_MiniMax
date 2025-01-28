package test;

import main.Board;
import main.CPUPlayer;
import main.Move;
import main.Mark;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CPUPlayerTest {

    @Test
    public void testGetNextMoveMinMax() {
        Board board = new Board();
        CPUPlayer cpuPlayer = new CPUPlayer(Mark.X);
        ArrayList<Move> moves = cpuPlayer.getNextMoveMinMax(board);
        System.out.println(moves);
        assertNotNull(moves);
    }



    @Test
    public void testBlockOpponentWinningMove() {
        Mark[][] initialBoard = {
                {Mark.EMPTY, Mark.O, Mark.X},
                {Mark.X, Mark.O, Mark.EMPTY},
                {Mark.EMPTY, Mark.EMPTY, Mark.EMPTY}
        };
        Board board = new Board(initialBoard);
        CPUPlayer cpuPlayer = new CPUPlayer(Mark.X);

        ArrayList<Move> moves = cpuPlayer.getNextMoveMinMax(board);
        assertNotNull(moves);
        assertTrue(moves.size() > 0);

        // AI should block the winning move at (1, 2)
        System.out.println("Blocking Move: " + moves);
        assertTrue(moves.stream().anyMatch(move -> move.getRow() == 2 && move.getCol() == 1));

    }

    @Test
    public void testWinningMove() {
        Mark[][] initialBoard = {
                {Mark.EMPTY, Mark.EMPTY, Mark.EMPTY},
                {Mark.O, Mark.O, Mark.EMPTY},
                {Mark.X, Mark.X, Mark.EMPTY}
        };
        Board board = new Board(initialBoard);
        CPUPlayer cpuPlayer = new CPUPlayer(Mark.X);

        ArrayList<Move> moves = cpuPlayer.getNextMoveMinMax(board);
        assertNotNull(moves);
        assertTrue(moves.size() > 0);

        // AI should win with the move at (0, 2)
        System.out.println("Winning Move: " + moves);
        assertTrue(moves.stream().anyMatch(move -> move.getRow() == 2 && move.getCol() == 2));

    }

    @Test
    public void testComplexPosition() {
        Mark[][] initialBoard = {
                {Mark.X, Mark.O, Mark.EMPTY},
                {Mark.EMPTY, Mark.X, Mark.EMPTY},
                {Mark.O, Mark.EMPTY, Mark.EMPTY}
        };
        Board board = new Board(initialBoard);
        CPUPlayer cpuPlayer = new CPUPlayer(Mark.X);
        ArrayList<Move> moves = cpuPlayer.getNextMoveMinMax(board);
        assertNotNull(moves);
        System.out.println(moves);
        assertTrue(moves.size() > 0);
        // Ensure the best move is returned
        assertEquals(3, moves.size());
    }



    @Test
    public void testFullBoard() {
        Mark[][] initialBoard = {
                {Mark.X, Mark.O, Mark.X},
                {Mark.O, Mark.X, Mark.O},
                {Mark.O, Mark.X, Mark.O}
        };
        Board board = new Board(initialBoard);
        CPUPlayer cpuPlayer = new CPUPlayer(Mark.X);

        ArrayList<Move> moves = cpuPlayer.getNextMoveMinMax(board);
        assertNotNull(moves);
        assertEquals(1, moves.size());
        assertEquals(0, moves.get(0).getScore()); // Score should indicate a draw
        System.out.println("Full Board Moves: " + moves);
    }

    @Test
    public void testEarlyGameStrategy() {
        Mark[][] initialBoard = {
                {Mark.X, Mark.X, Mark.O},
                {Mark.O, Mark.O, Mark.X},
                {Mark.X, Mark.O, Mark.X}
        };
        Board board = new Board(initialBoard);
        CPUPlayer cpuPlayer = new CPUPlayer(Mark.X);

        ArrayList<Move> moves = cpuPlayer.getNextMoveMinMax(board);
        System.out.println(cpuPlayer.getNumExploredNodes());
        assertNotNull(moves);
        System.out.println("Early Game Moves: " + moves);
        assertTrue(moves.size() > 0);

    }

//    @Test
//    public void testGetNextMoveAB() {
//        Board board = new Board();
//        CPUPlayer cpuPlayer = new CPUPlayer(Mark.X);
//        ArrayList<Move> moves = cpuPlayer.getNextMoveAB(board, 1, true);
//        assertNotNull(moves);
//    }
}
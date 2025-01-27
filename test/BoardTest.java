package test;

import main.Board;
import main.Move;
import main.Mark;

import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void testPlay() {
        Board board = new Board();
        Move move = new Move(0, 0);
        board.play(move, Mark.X);
        assertEquals(Mark.X, board.getMarkAt(0, 0));
    }

    @Test
    public void testPlayValidMove() {
        Board board = new Board();
        Move move = new Move(0, 0);
        board.play(move, Mark.X);
        assertEquals(Mark.X, board.getMarkAt(0, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlayInvalidMove() {
        Board board = new Board();
        Move move = new Move(0, 0);
        board.play(move, Mark.X);
        // Attempt to play on the same spot again
        board.play(move, Mark.O);
    }

    @Test
    public void testPlayMethod() {
        Board board = new Board();
        Move move1 = new Move(1, 1);
        board.play(move1, Mark.O);
        assertEquals(Mark.O, board.getMarkAt(1, 1));

        Move move2 = new Move(2, 2);
        board.play(move2, Mark.X);
        assertEquals(Mark.X, board.getMarkAt(2, 2));
    }

    @Test
    public void testEvaluate() {
        Board board = new Board();
        // Set up a winning condition for X
        board.play(new Move(0, 0), Mark.X);
        board.play(new Move(0, 1), Mark.X);
        board.play(new Move(0, 2), Mark.X);
        assertEquals(100, board.evaluate(Mark.X));
    }
}
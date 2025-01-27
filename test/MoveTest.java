package test;

import main.Move;
import org.junit.Test;
import static org.junit.Assert.*;

public class MoveTest {

    @Test
    public void testMoveConstructor() {
        Move move = new Move(1, 2);
        assertEquals(1, move.getRow());
        assertEquals(2, move.getCol());
    }

    @Test
    public void testSetRow() {
        Move move = new Move();
        move.setRow(1);
        assertEquals(1, move.getRow());
    }

    @Test
    public void testSetCol() {
        Move move = new Move();
        move.setCol(2);
        assertEquals(2, move.getCol());
    }
}
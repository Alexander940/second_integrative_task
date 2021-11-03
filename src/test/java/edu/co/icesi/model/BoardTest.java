package edu.co.icesi.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    private Board board;

    @Before
    public void setUpStage1(){
        board = new Board(5,4, 2, 1);
    }

    @Test
    public void when_create_a_board() {
        assertEquals(20, board.getTail().getPosition());
    }

    @Test
    public void get_a_box() {
        assertEquals(10, board.get(10, 1, board.getHead()).getPosition());
    }
}
package com.stapna.minesweeper;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CellTest {

    @Test
    public void itShouldCreateCell_WithDot_AndPosition(){
        //given
        Cell plainCell = Cell.dot(2,3);

        // when

        //then
        assertTrue(plainCell.isDot());
        assertEquals(".",plainCell.val());
        assertEquals(2,plainCell.x());
        assertEquals(3,plainCell.y());
    }

    @Test
    public void itShouldCreateCell_WithMine_AndPosition(){
        //given
        Cell mine = Cell.mine(2,3);

        // when

        //then
        assertTrue(mine.isMine());
        assertEquals("*",mine.val());
        assertEquals(2,mine.x());
        assertEquals(3,mine.y());
    }

    @Test
    public void itShouldCreateCell_WithNum_AndPosition(){
        //given
        Cell num = Cell.num(2,3,2);

        // when

        //then
        assertEquals("2",num.val());
        assertEquals(2,num.x());
        assertEquals(3,num.y());
    }



}

package com.stapna.minesweeper;

import org.junit.Test;

import static com.stapna.minesweeper.testutil.FieldAssert.assertAllMine;
import static com.stapna.minesweeper.testutil.FieldAssert.assertEvaluatedMineAllDot;
import static com.stapna.minesweeper.testutil.FieldGenerator.*;
import static org.junit.Assert.assertEquals;

public class MineFieldEvaluatorTests {

    private final int row = 4;
    private final int col = 4;

    @Test
    public void itShouldTakeAnInitializedField_AllDots_CreateFieldWith_AdjMineCells() {
        //given
        MineField initField = MineField.init(row,col);

        generateDot(initField);
        //when
        MineField evaluateField =  MineField.eval(initField);

        //then
        assertEvaluatedMineAllDot(evaluateField);
    }

    @Test
    public void itShouldTakeAnInitializedField_AllMine_CreateFieldWith_AdjMineCells() {
        //given
        MineField initField = MineField.init(row,col);
        generateMine(initField);

        //when
        MineField evaluateField =  MineField.eval(initField);

        //then
        assertAllMine(evaluateField);
    }

    @Test
    public void itShouldTakeAnInitializedField_MineAtCorner_0_0_CreateFieldWith_AdjMineCells() {
        //given
        MineField initField = MineField.init(4,4);
        generateMineOnCondition((x,y)->((x == 0 && y == 0)),initField);

        //when
        MineField evaluateField =  MineField.eval(initField);

        //then
        assertEquals("*",evaluateField.val(0,0).get().val());
        assertEquals("1",evaluateField.val(0,1).get().val());
        assertEquals("1",evaluateField.val(1,1).get().val());
        assertEquals("1",evaluateField.val(1,0).get().val());
    }

    @Test
    public void itShouldTakeAnInitializedField_MineOnBorder_0_2_CreateFieldWith_AdjMineCells() {
        //given
        MineField initField = MineField.init(5,5);
        generateMineOnCondition((x,y) -> ((x == 0 && y == 2)),initField);

        //when
        MineField evaluateField =  MineField.eval(initField);

        //then
        assertEquals("*",evaluateField.val(0,2).get().val());
        assertEquals("1",evaluateField.val(0,1).get().val());
        assertEquals("1",evaluateField.val(1,1).get().val());
        assertEquals("1",evaluateField.val(1,2).get().val());
        assertEquals("1",evaluateField.val(0,3).get().val());
        assertEquals("1",evaluateField.val(0,3).get().val());
        assertEquals("1",evaluateField.val(1,3).get().val());
    }

    @Test
    public void itShouldTakeAnInitializedField_MineInMid_2_3_CreateFieldWith_AdjMineCells() {
        //given
        MineField initField = MineField.init(5,5);
        generateMineOnCondition((x,y) -> ((x == 0 && y == 2)),initField);

        //when
        MineField evaluateField =  MineField.eval(initField);

        //then
        assertEquals("*",evaluateField.val(0,2).get().val());
        assertEquals("1",evaluateField.val(0,1).get().val());
        assertEquals("1",evaluateField.val(1,1).get().val());
        assertEquals("1",evaluateField.val(1,2).get().val());
        assertEquals("1",evaluateField.val(0,3).get().val());
        assertEquals("1",evaluateField.val(0,3).get().val());
        assertEquals("1",evaluateField.val(1,3).get().val());

    }
}

package com.stapna.minesweeper;

import org.junit.Test;

import static java.util.stream.IntStream.range;
import static org.junit.Assert.assertEquals;

public class MineFieldEvaluatorTests {
    @Test
    public void itShouldTakeAnInitializedField_AllDots_CreateFieldWith_AdjMineCells() {
        //given
        MineField initField = MineField.init(4,4);
        range(0,4)
                .forEach( y -> range(0,4)
                        .forEach(x -> initField.dot(x,y)));
        //when
        MineField evaluateField =  MineField.eval(initField);

        //then
        range(0,4)
                .forEach( y -> range(0,4)
                        .forEach(x -> assertEquals("0",evaluateField.val(x,y).get().val())));
    }

    @Test
    public void itShouldTakeAnInitializedField_AllMine_CreateFieldWith_AdjMineCells() {
        //given
        MineField initField = MineField.init(4,4);
        range(0,4)
                .forEach( y -> range(0,4)
                        .forEach(x -> initField.mine(x,y)));
        //when
        MineField evaluateField =  MineField.eval(initField);

        //then
        range(0,4)
                .forEach( y -> range(0,4)
                        .forEach(x -> assertEquals("*",evaluateField.val(x,y).get().val())));
    }

    @Test
    public void itShouldTakeAnInitializedField_MineAtCorner_0_0_CreateFieldWith_AdjMineCells() {
        //given
        MineField initField = MineField.init(4,4);
        range(0,4)
                .forEach( y -> range(0,4)
                        .forEach(x -> {
                            if((x == 0 && y == 0))
                                initField.mine(x,y);
                            else
                                initField.dot(x,y);}));
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
        range(0,5)
                .forEach( y -> range(0,5)
                        .forEach(x -> {
                            if((x == 0 && y == 2))
                                initField.mine(x,y);
                            else
                                initField.dot(x,y);}));
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
        range(0,5)
                .forEach( y -> range(0,5)
                        .forEach(x -> {
                            if((x == 0 && y == 2))
                                initField.mine(x,y);
                            else
                                initField.dot(x,y);}));
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

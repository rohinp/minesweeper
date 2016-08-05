package com.stapna.minesweeper;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class MineFieldEvaluatorTests {
    @Test
    public void itShouldTakeAnInitializedField_AllDots_CreateFieldWith_AdjMineCells() {
        //given
        MineField initField = MineField.init(4,4);
        IntStream.range(0,4)
                .forEach( y -> IntStream.range(0,4)
                        .forEach(x -> initField.dot(x,y)));
        //when
        MineField evaluateField =  MineField.eval(initField);

        //then
        IntStream.range(0,4)
                .forEach( y -> IntStream.range(0,4)
                        .forEach(x -> assertEquals("0",evaluateField.val(x,y).get().val())));
    }

    @Test
    public void itShouldTakeAnInitializedField_AllMine_CreateFieldWith_AdjMineCells() {
        //given
        MineField initField = MineField.init(4,4);
        IntStream.range(0,4)
                .forEach( y -> IntStream.range(0,4)
                        .forEach(x -> initField.mine(x,y)));
        //when
        MineField evaluateField =  MineField.eval(initField);

        //then
        IntStream.range(0,4)
                .forEach( y -> IntStream.range(0,4)
                        .forEach(x -> assertEquals("*",evaluateField.val(x,y).get().val())));
    }

    @Test
    public void itShouldTakeAnInitializedField_MineAt0_0_CreateFieldWith_AdjMineCells() {
        //given
        MineField initField = MineField.init(4,4);
        IntStream.range(0,4)
                .forEach( y -> IntStream.range(0,4)
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
}

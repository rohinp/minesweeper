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
        MineField evalutedField =  MineField.eval(initField);

        //then
        IntStream.range(0,4)
                .forEach( y -> IntStream.range(0,4)
                        .forEach(x -> assertEquals("0",evalutedField.val(x,y).get().val())));
    }
}

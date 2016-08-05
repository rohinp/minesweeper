package com.stapna.minesweeper;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class InitMineFieldTests {
    @Test
    public void itShouldCreateFieldWithAllDot(){
        //given
        MineField mineField = MineField.init(4,4);

        //when
        IntStream.range(0,4)
                .forEach( y -> IntStream.range(0,4)
                        .forEach(x -> mineField.dot(x,y)));
        //then
        IntStream.range(0,4)
                .forEach( y -> IntStream.range(0,4)
                        .forEach(x -> assertEquals(".",mineField.val(x,y).get().val())));
    }

    @Test
    public void itShouldCreateFieldWithAllMine(){
        //given
        MineField mineField = MineField.init(4,4);

        //when
        IntStream.range(0,4)
                .forEach( y -> IntStream.range(0,4)
                        .forEach(x -> mineField.mine(x,y)));
        //then
        IntStream.range(0,4)
                .forEach( y -> IntStream.range(0,4)
                        .forEach(x -> assertEquals("*",mineField.val(x,y).get().val())));
    }

    @Test
    public void itShouldCreateFieldWithDotAndMines(){
        //given
        MineField mineField = MineField.init(4,4);

        //when

        IntStream.range(0,4)
                .forEach( y -> IntStream.range(0,4)
                        .forEach(x -> {
                            if((x == 0 && y == 0) || (x == 2 && y == 3))
                                mineField.mine(x,y);
                            else
                                mineField.dot(x,y);}));

        //then
        IntStream.range(0,4)
                .forEach( y -> IntStream.range(0,4)
                        .forEach(x -> {
                            if((x == 0 && y == 0) || (x == 2 && y == 3))
                                assertEquals("*",mineField.val(x,y).get().val());
                            else
                                assertEquals(".",mineField.val(x,y).get().val());}));
    }

}

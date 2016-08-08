package com.stapna.minesweeper;

import org.junit.Test;

import static com.stapna.minesweeper.testutil.FieldAssert.*;
import static com.stapna.minesweeper.testutil.FieldGenerator.*;

public class InitMineFieldTests {

    private final int row = 4;
    private final int col = 4;

    @Test
    public void itShouldCreateFieldWithAllDot(){
        //given
        MineField mineField = MineField.init(row,col);

        //when
        generateDot(mineField);

        //then
        assertAllDot(mineField);
    }

    @Test
    public void itShouldCreateFieldWithAllMine(){
        //given
        MineField mineField = MineField.init(row,col);

        //when
        generateMine(mineField);

        //then
        assertAllMine(mineField);
    }

    @Test
    public void itShouldCreateFieldWithDotAndMines(){
        //given
        MineField mineField = MineField.init(row,col);

        //when
        generateMineOnCondition((x,y) ->((x == 0 && y == 0) || (x == 2 && y == 3)) ,mineField);

        //then
        assertMineOnCondition((x,y) -> ((x == 0 && y == 0) || (x == 2 && y == 3)),mineField);

    }

}

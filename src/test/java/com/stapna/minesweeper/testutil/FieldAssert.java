package com.stapna.minesweeper.testutil;

import com.stapna.minesweeper.MineField;

import static org.junit.Assert.assertEquals;

public class FieldAssert {

    public static void assertAllDot(MineField initField){
        assertMineOnCondition((x,y) -> false,initField);
    }

    public static void assertAllMine(MineField initField){
        assertMineOnCondition((x,y) -> true,initField);
    }

    public static void assertMineOnCondition(Condition condition,MineField initField){
        Generator.mineGenerator(condition,initField,(x, y, m) -> assertEquals("*",m.val(x,y).get().val()),(x, y, m) -> assertEquals(".",m.val(x,y).get().val()));
    }

    public static void assertEvaluatedMineAllDot(MineField initField){
        Generator.mineGenerator((x, y)-> true,initField,(x, y, m) -> assertEquals("0",m.val(x,y).get().val()),(x, y, m) -> assertEquals("0",m.val(x,y).get().val()));
    }
}

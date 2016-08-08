package com.stapna.minesweeper.testutil;

import com.stapna.minesweeper.MineField;

import static com.stapna.minesweeper.testutil.Generator.*;

public class FieldGenerator {
    public static void generateDot(MineField initField){
        generateMineOnCondition((x,y) -> false,initField);
    }

    public static void generateMine(MineField initField){
        generateMineOnCondition((x,y) -> true,initField);
    }

    public static void generateMineOnCondition(Condition condition,MineField initField){
        mineGenerator(condition,initField,(x, y, m) -> m.mine(x,y),(x, y, m) -> m.dot(x,y));
    }
}


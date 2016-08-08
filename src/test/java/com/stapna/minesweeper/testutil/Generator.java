package com.stapna.minesweeper.testutil;

import com.stapna.minesweeper.MineField;

import static java.util.stream.IntStream.range;

@FunctionalInterface
interface CustomConsumer {
    void accept(int xco, int yco, MineField mineField);
}

@FunctionalInterface
interface Generator {
    void generator(Condition condition, MineField mineField, CustomConsumer consumerOnTrue, CustomConsumer consumerOnFalse);

    static void genericGenerator(Condition condition, MineField mineField, CustomConsumer consumerOnTrue, CustomConsumer consumerOnFalse){
        ((Generator) (con,field,ts,fs) -> range(0,field.col())
                .forEach( y -> range(0,field.row())
                        .forEach(x -> {
                            if(con.ifThenElse(x,y))
                                consumerOnTrue.accept(x,y,mineField);
                            else
                                consumerOnFalse.accept(x,y,mineField);
                        }))).generator(condition,mineField,consumerOnTrue,consumerOnFalse);
    }
}

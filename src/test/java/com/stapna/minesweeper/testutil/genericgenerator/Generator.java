package com.stapna.minesweeper.testutil.genericgenerator;

import com.stapna.minesweeper.MineField;

import static java.util.stream.IntStream.range;

@FunctionalInterface
public interface Generator<T> {
    void generator(Condition condition, T t, CustomConsumer<T> consumerOnTrue, CustomConsumer<T> consumerOnFalse);

    static<T extends MineField> void mineGenerator(Condition condition, T mineField, CustomConsumer<MineField> consumerOnTrue, CustomConsumer<MineField> consumerOnFalse){
        ((Generator<MineField>) (con,field,ts,fs) -> range(0,field.col())
                .forEach( y -> range(0,field.row())
                        .forEach(x -> {
                            if(con.ifThenElse(x,y))
                                consumerOnTrue.accept(x,y,mineField);
                            else
                                consumerOnFalse.accept(x,y,mineField);
                        }))).generator(condition,mineField,consumerOnTrue,consumerOnFalse);
    }
}

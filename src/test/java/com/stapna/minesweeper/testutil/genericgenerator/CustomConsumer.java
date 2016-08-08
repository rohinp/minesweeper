package com.stapna.minesweeper.testutil.genericgenerator;

@FunctionalInterface
public interface CustomConsumer<T> {
    void accept(int xco, int yco, T t);
}

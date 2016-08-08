package com.stapna.minesweeper.testutil;

@FunctionalInterface
public interface Condition {
    boolean ifThenElse(int xco, int yco);
}

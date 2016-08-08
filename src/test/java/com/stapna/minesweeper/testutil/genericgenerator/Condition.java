package com.stapna.minesweeper.testutil.genericgenerator;

@FunctionalInterface
public interface Condition {
    boolean ifThenElse(int xco, int yco);
}

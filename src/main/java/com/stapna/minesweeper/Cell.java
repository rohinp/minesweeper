package com.stapna.minesweeper;

public interface Cell {

    static Cell mine(int xco, int yco) {
        return new Cell() {

            @Override
            public boolean isMine() {
                return true;
            }

            @Override
            public boolean isDot() {
                return false;
            }

            @Override
            public String val() {
                return "*";
            }

            @Override
            public int x() {
                return xco;
            }

            @Override
            public int y() {
                return yco;
            }
        };
    }

    static Cell dot(int xco, int yco) {
        return new Cell() {
            @Override
            public boolean isMine() {
                return false;
            }

            @Override
            public boolean isDot() {
                return true;
            }

            @Override
            public String val() {
                return ".";
            }

            @Override
            public int x() {
                return xco;
            }

            @Override
            public int y() {
                return yco;
            }
        };
    }

    static Cell num(int xco, int yco,int adjMines) {

        return new Cell() {
            @Override
            public boolean isMine() {
                return false;
            }

            @Override
            public boolean isDot() {
                return false;
            }

            @Override
            public String val() {
                return adjMines+"";
            }

            @Override
            public int x() {
                return xco;
            }

            @Override
            public int y() {
                return yco;
            }
        };
    }

    boolean isMine();
    boolean isDot();
    String val();
    int x();
    int y();
}

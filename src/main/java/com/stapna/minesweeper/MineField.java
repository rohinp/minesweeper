package com.stapna.minesweeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.IntStream.*;

public interface MineField {
    void dot(int x, int y);
    void mine(int x, int y);
    int row();
    int col();
    List<Cell> field();

    default Optional<Cell> val(int x, int y) {
        return field().stream().filter(e -> e.x() == x && e.y() == y).findFirst();
    }

    static MineField init(int row, int col) {
        return new InitField(row,col);
    }

    static MineField eval(MineField initField) {
        return new FieldEval(initField).eval();
    }

    class InvalidCellModification extends RuntimeException{}
}

class InitField implements MineField {
    private final List<Cell> field;
    private final int row;
    private final int col;

    InitField(int row, int col) {
        this.row = row;
        this.col = col;
        field = new ArrayList<>(row * col);
    }

    @Override
    public void dot(int x, int y) {
        field.add(Cell.dot(x,y));
    }

    @Override
    public void mine(int x, int y) {
        field.add(Cell.mine(x,y));
    }

    @Override
    public int row() {
        return this.row;
    }

    @Override
    public int col() {
        return this.col;
    }

    @Override
    public List<Cell> field() {
        return field;
    }

}

class FieldEval implements MineField {
    private final List<Cell> field;
    private final MineField initField;
    private Cell mutableCell;

    public FieldEval(MineField field) {
        this.field = new ArrayList<>();
        this.initField = field;
    }

    private FieldEval(List<Cell> field, MineField mineField) {
        this.field = field;
        this.initField = mineField;
    }

    FieldEval eval() {
        return new FieldEval(initField.field().stream().map(this::createCell).collect(Collectors.toList()), initField);
    }

    private Cell createCell(Cell c) {
        if(c.isMine()) return c;
        mutableCell = Cell.num(c.x(),c.y(),0);
        range(-1,2).forEach( y -> range(-1,2).forEach(x -> modifyCellNum(c,x,y)));
        return mutableCell;
    }

    private void modifyCellNum(Cell c, int x, int y) {
        if(isValidAndMine(c,x,y))
            mutableCell = Cell.num(mutableCell.x(), mutableCell.y(),Integer.parseInt(mutableCell.val()) + 1);
    }

    private boolean isValidAndMine(Cell c, int x, int y) {
        return  !(x == 0 && y == 0) && !((c.x() + x) < 0 || (c.y() + y) < 0) &&
                ((c.x() + x) < row() && (c.y() + y) < col()) &&
                initField.val(c.x() + x,c.y() + y).get().isMine();
    }

    @Override
    public void dot(int x, int y) {
        throw new InvalidCellModification();
    }

    @Override
    public void mine(int x, int y) {
        throw new InvalidCellModification();
    }

    @Override
    public int row() {
        return initField.row();
    }

    @Override
    public int col() {
        return initField.col();
    }

    @Override
    public List<Cell> field() {
        return field;
    }

    @Override
    public String toString() {
        return field.stream().map(this::formString).reduce("", String::concat);
    }

    private String formString(Cell c) {
        return ((c.x() + 1) % row()) == 0 ?   c.val() + "\n": c.val();
    }
}



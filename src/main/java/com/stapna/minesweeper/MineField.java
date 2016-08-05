package com.stapna.minesweeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface MineField {
    void dot(int x, int y);
    Optional<Cell> val(int x, int y);
    void mine(int x, int y);

    List<Cell> field();

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

    InitField(int row, int col) {
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
    public List<Cell> field() {
        return field;
    }

    @Override
    public Optional<Cell> val(int x, int y) {
        return field.stream().filter(e -> e.x() == x && e.y() == y).findFirst();
    }

}

class FieldEval implements MineField {
    private final List<Cell> field;
    private final MineField initField;

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
        if(c.isMine())
            return c;
        return Cell.num(c.x(),c.y(),0);
    }

    @Override
    public void dot(int x, int y) {
        throw new InvalidCellModification();
    }

    @Override
    public Optional<Cell> val(int x, int y) {
        return field.stream().filter(e -> e.x() == x && e.y() == y).findFirst();
    }

    @Override
    public void mine(int x, int y) {
        throw new InvalidCellModification();
    }

    @Override
    public List<Cell> field() {
        return field;
    }
}



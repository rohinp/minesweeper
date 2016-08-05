package com.stapna.minesweeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface MineField {
    void dot(int x, int y);
    Optional<Cell> val(int x, int y);
    void mine(int x, int y);
    void num(int x,int y, int num);
    int row();
    int col();
    List<Cell> field();

    static MineField init(int row, int col) {
        return new InitField(row,col);
    }

    static MineField eval(MineField initField) {
        return new FieldEvaluator(initField).eval();
    }

    class InvalidCellModification extends RuntimeException{
        public InvalidCellModification(String msg) {
            super(msg);
        }
    }
}

class InitField implements MineField {
    private final int row;
    private final int col;
    final List<Cell> field;

    InitField(int row, int col) {
        this.row = row;
        this.col = col;
        field = new ArrayList<>(this.row * this.col);
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
    public void num(int x, int y, int num) {
        throw new InvalidCellModification("Must be Dot or Mine");
    }

    @Override
    public int row() {
        return row;
    }

    @Override
    public int col() {
        return col;
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

class FieldEvaluator implements MineField {
    private final List<Cell> field;
    private final MineField initField;

    public FieldEvaluator(MineField field) {
        this.field = new ArrayList<>();
        this.initField = field;
    }

    private FieldEvaluator(List<Cell> field,MineField mfield) {
        System.out.println(field);
        this.field = field;
        this.initField = mfield;
    }

    FieldEvaluator eval() {
        return new FieldEvaluator(initField.field().stream().map(c -> Cell.num(c.x(),c.y(),0)).collect(Collectors.toList()), initField);
    }

    @Override
    public void dot(int x, int y) {
        throw new InvalidCellModification("Must be a num");
    }

    @Override
    public Optional<Cell> val(int x, int y) {
        return field.stream().filter(e -> e.x() == x && e.y() == y).findFirst();
    }

    @Override
    public void mine(int x, int y) {
        throw new InvalidCellModification("Must be a num");
    }

    @Override
    public void num(int x, int y, int num) {

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
}



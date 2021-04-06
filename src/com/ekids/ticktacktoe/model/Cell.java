package com.ekids.ticktacktoe.model;

import java.util.Objects;

public class Cell {

    private int row;

    private int column;

    private ActionType status;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public ActionType getStatus() {
        return status;
    }

    public void setStatus(ActionType status) {
        this.status = status;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Cell cell = (Cell) o;

        return row == cell.row && column == cell.column && status == cell.status;
    }

    @Override public int hashCode() {
        return Objects.hash(row, column, status);
    }

    @Override public String toString() {
        return "Cell{" +
                "row=" + row +
                ", column=" + column +
                ", status=" + status +
                '}';
    }
}

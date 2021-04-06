package com.ekids.ticktacktoe.model;

public class Desk {
    private Cell[][] cellArray;

    public Desk(Cell[][] cellArray) {
        this.cellArray = cellArray;
    }

    public Cell[][] getCellArray() {
        return cellArray;
    }

    public void setCellArray(Cell[][] cellArray) {
        this.cellArray = cellArray;
    }
}

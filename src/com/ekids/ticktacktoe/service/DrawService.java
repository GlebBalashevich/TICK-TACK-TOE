package com.ekids.ticktacktoe.service;

import com.ekids.ticktacktoe.model.ActionType;
import com.ekids.ticktacktoe.model.Cell;
import com.ekids.ticktacktoe.model.Desk;

public class DrawService {
    private static final String CROSS = "X";
    private static final String ZERO = "O";
    private static final String EMPTY = "_";
    private static final String BOARD = "|";

    public void drawDesk(Desk desk){
        Cell[][] cells = desk.getCellArray();

        for (int i = 0; i < cells.length; i++) {
            String row = "";

            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].getStatus() == null) {
                    row = row + EMPTY;
                } else if (cells[i][j].getStatus() == ActionType.CROSS) {
                    row = row + CROSS;
                } else {
                    row = row + ZERO;
                }
                if (j + 1 < cells.length) {
                    row = row + BOARD;
                }
            }
            System.out.println(row);
        }
    }
}

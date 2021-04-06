package com.ekids.ticktacktoe.service;

import com.ekids.ticktacktoe.model.ActionType;
import com.ekids.ticktacktoe.model.Cell;
import com.ekids.ticktacktoe.model.Desk;
import com.ekids.ticktacktoe.model.Player;

public class GameService {

    public boolean isGameOver(Desk desk) {
        Cell[][] cells = desk.getCellArray();

        for (Cell[] cellRows : cells) {
            for (Cell cell : cellRows) {
                if (cell.getStatus() == null) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean takeStep(Desk desk, Player player, int rowIndex, int columnIndex){
        boolean isStepAccepted = false;
        Cell[][] cells = desk.getCellArray();

        if (cells[rowIndex][columnIndex].getStatus() == null){
            cells[rowIndex][columnIndex].setStatus(player.getType());
            isStepAccepted = true;
        }

        return isStepAccepted;
    }

    public ActionType pickWinner(Desk desk) {
        Cell[][] cells = desk.getCellArray();
        ActionType winner = null;

        for (int i = 0; i < cells.length; i++) {
            winner = checkRow(cells, i);
            if (winner != null){
                break;
            }
        }
        if (winner == null) {
            for (int i = 0; i < cells[0].length; i++) {
                winner = checkColumn(cells, i);
                if (winner != null){
                    break;
                }
            }
            if (winner == null){
                winner = checkDiagonal(cells);
            }
        }

        return winner;
    }

    private ActionType checkRow(Cell[][] cells, int rowIndex) {
        int countCross = 0;
        int countZero = 0;

        for (Cell cell : cells[rowIndex]) {
            if (cell.getStatus() == ActionType.CROSS) {
                countCross++;
            }
            if (cell.getStatus() == ActionType.ZERO) {
                countZero++;
            }
        }

        return checkResult(countCross, countZero);
    }

    private ActionType checkColumn(Cell[][] cells, int columnIndex) {
        int countCross = 0;
        int countZero = 0;

        for (Cell[] cell : cells) {
            if (cell[columnIndex].getStatus() == ActionType.CROSS) {
                countCross++;
            }
            if (cell[columnIndex].getStatus() == ActionType.ZERO) {
                countZero++;
            }
        }

        return checkResult(countCross, countZero);
    }

    private ActionType checkDiagonal(Cell[][] cells) {
        ActionType winner = null;
        int countCross = 0;
        int countZero = 0;

        for (int i = 0; i < cells.length; i++) {
            if (cells[i][i].getStatus() == ActionType.CROSS) {
                countCross++;
            }
            if (cells[i][i].getStatus() == ActionType.ZERO) {
                countZero++;
            }
        }
        winner = checkResult(countCross, countZero);

        if (winner == null){
            countCross = 0;
            countZero = 0;

            for (int i = 0; i < cells.length; i++) {
                if (cells[i][cells[i].length - 1 - i].getStatus() == ActionType.CROSS) {
                    countCross++;
                }
                if (cells[i][cells[i].length - 1 - i].getStatus() == ActionType.ZERO) {
                    countZero++;
                }
            }
            winner = checkResult(countCross, countZero);
        }


        return winner;
    }

    private ActionType checkResult(int countCross, int countZero){
        ActionType winner = null;

        if (countCross == 3) {
            winner = ActionType.CROSS;
        }
        if (countZero == 3) {
            winner = ActionType.ZERO;
        }

        return winner;
    }
}

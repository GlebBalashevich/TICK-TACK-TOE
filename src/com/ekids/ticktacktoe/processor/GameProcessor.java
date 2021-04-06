package com.ekids.ticktacktoe.processor;

import com.ekids.ticktacktoe.handler.ConsoleHandler;
import com.ekids.ticktacktoe.model.ActionType;
import com.ekids.ticktacktoe.model.Cell;
import com.ekids.ticktacktoe.model.Desk;
import com.ekids.ticktacktoe.model.Player;
import com.ekids.ticktacktoe.service.DrawService;
import com.ekids.ticktacktoe.service.GameService;
import com.ekids.ticktacktoe.util.Message;
import com.ekids.ticktacktoe.validator.InputDataValidator;

public class GameProcessor implements Runnable{
    private final InputDataValidator dataValidator;
    private final ConsoleHandler consoleHandler;
    private final DrawService drawService;
    private final GameService gameService;
    private Player crossPlayer;
    private Player zeroPlayer;
    private Desk desk;

    public GameProcessor(){
        this.dataValidator = new InputDataValidator();
        this.consoleHandler = new ConsoleHandler();
        this.drawService = new DrawService();
        this.gameService = new GameService();
    }

    @Override
    public void run() {
        ActionType winner = null;
        init();

        while(!Thread.currentThread().isInterrupted()){
            consoleHandler.writeMessage(Message.PLAYER_TURN + crossPlayer.getName());
            stepProcess(crossPlayer);
            winner = gameService.pickWinner(desk);
            if (gameService.isGameOver(desk) || winner != null){
                Thread.currentThread().interrupt();
                continue;
            }
            consoleHandler.writeMessage(Message.PLAYER_TURN + zeroPlayer.getName());
            stepProcess(zeroPlayer);
            winner = gameService.pickWinner(desk);
            if (gameService.isGameOver(desk) || winner != null){
                Thread.currentThread().interrupt();
            }
        }

        if (winner == ActionType.CROSS){
            consoleHandler.writeMessage(Message.GAME_OVER_WINNER_IS + crossPlayer.getName());
        } else if (winner == ActionType.ZERO){
            consoleHandler.writeMessage(Message.GAME_OVER_WINNER_IS + zeroPlayer.getName());
        } else{
            consoleHandler.writeMessage(Message.GAME_OVER_NO_WINNER);
        }
        consoleHandler.close();
    }

    private void init(){
        consoleHandler.writeMessage(Message.REGISTER_CROSS_PLAYER);
        crossPlayer = new Player(consoleHandler.readMessage(), ActionType.CROSS);
        consoleHandler.writeMessage(Message.REGISTER_ZERO_PLAYER);
        zeroPlayer = new Player(consoleHandler.readMessage(), ActionType.ZERO);
        Cell[][] cells = new Cell[3][3];

        for (int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[i].length; j++){
                cells[i][j] = new Cell(i, j);
            }
        }

        desk = new Desk(cells);
        drawService.drawDesk(desk);
        consoleHandler.writeMessage(Message.START_GAME_MESSAGE);
    }

    private void stepProcess(Player player){
        while (true){
            consoleHandler.writeMessage(Message.STEP_ROW_INSTRUCTION);
            String row = consoleHandler.readMessage();
            consoleHandler.writeMessage(Message.STEP_COLUMN_INSTRUCTION);
            String column = consoleHandler.readMessage();
            if (dataValidator.validateIndexInput(row) && dataValidator.validateIndexInput(column)){
                int rowIndex = Integer.parseInt(row) - 1;
                int columnIndex = Integer.parseInt(column) - 1;
                if (gameService.takeStep(desk, player, rowIndex, columnIndex)){
                    drawService.drawDesk(desk);
                    break;
                }
            }
            consoleHandler.writeMessage(Message.INCORRECT_INPUT);
        }
    }
}

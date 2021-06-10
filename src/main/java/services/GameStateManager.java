package services;

import models.GridCoord;
import models.GridValue;
import models.GameState;
import models.Input;

import static constants.Message.*;

public class GameStateManager {

    private static final boolean PLAYER = true;
    private static final boolean COMPUTER = false;


    private GameState gameState;
    private AIService aiService;
    private boolean init;


    public GameStateManager() {
        aiService = new AIService();
        gameState = new GameState();
        init = true;
    }

    public GameState updateGameState(Input input) {
        clearMessage();

        if (init) {
            init = false;
            addMessage(WELCOME_1);
            addMessage(WELCOME_2);
            addMessage(WELCOME_3);
            addMessage(WELCOME_4);
            addMessage(WELCOME_5);
            addMessage(ENTER_INPUT);
            return gameState;
        }

        if (inputIsEmpty(input)) {
            addMessage(INPUT_INVALID);
            addMessage(ENTER_INPUT);
            return gameState;
        }

        boolean success = updateGridAsPlayer(input);
        if (success && !isGameOver()) {
            updateGridAsComputer();
        }

        GridValue[][] grid = gameState.getGrid();
        if (aiService.hasWinningLine(grid, GridValue.PLAYER)) {
            addMessage(YOU_WIN);
        } else if(aiService.hasWinningLine(grid, GridValue.COMPUTER)) {
            addMessage(YOU_LOSE);
        } else if(aiService.isFilled(grid)) {
            addMessage(DRAW_GAME);
        } else {
            addMessage(ENTER_INPUT);
        }
        return gameState;
    }

    public boolean isGameOver() {
        GridValue[][] grid = gameState.getGrid();

        return aiService.hasWinningLine(grid, GridValue.COMPUTER) ||
                aiService.hasWinningLine(grid, GridValue.PLAYER) ||
                aiService.isFilled(grid);
    }

    private void clearMessage() {
        gameState.setMessage("");
    }

    private void addMessage(String newMsg) {
        String currMsg = gameState.getMessage();
        gameState.setMessage(currMsg + "\n" + newMsg);
    }

    private boolean updateGridAsPlayer(Input input) {
        GridCoord gridCoord = input.getGridCoord();
        int row = gridCoord.getRow();
        int col = gridCoord.getCol();

        if (gameState.getGrid()[row][col] != GridValue.EMPTY) {
            addMessage(ALREADY_FILLED);
            return false;
        }
        gameState.getGrid()[row][col] = GridValue.PLAYER;
        return true;
    }

    private boolean updateGridAsComputer() {
        GridValue[][] currGrid = gameState.getGrid();
        GridCoord gridCoord = aiService.makeMove(currGrid);
        int row = gridCoord.getRow();
        int col = gridCoord.getCol();

        gameState.getGrid()[row][col] = GridValue.COMPUTER;
        addMessage(String.format(COMPUTER_MOVED, row, col));
        return true;
    }

    private boolean inputIsEmpty(Input input) {
        return input == null || input.getGridCoord() == null;
    }
}

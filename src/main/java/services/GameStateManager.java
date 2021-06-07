package services;

import models.GridCoord;
import enums.GridValue;
import models.GameState;
import models.Input;

import static messages.Message.*;

public class GameStateManager {

    private static final boolean PLAYER = true;
    private static final boolean COMPUTER = false;


    private GameState gameState;
    private InputService inputService;
    private boolean init;


    public GameStateManager() {
        inputService = new InputService();
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

        if (!inputService.isInputValid(input)) {
            addMessage(INPUT_INVALID);
            addMessage(ENTER_INPUT);
            return gameState;
        }

        boolean success = updateGridAsPlayer(input);
        if (success) {
            updateGridAsComputer();
        }

        addMessage(ENTER_INPUT);
        return gameState;
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
        updateGrid(input, PLAYER);
        return true;
    }

    private boolean updateGridAsComputer() {
        return true;
    }

    private void updateGrid(Input input, boolean isPlayer) {
        GridCoord gridCoord = input.getGridCoord();
        GridValue gridValue = isPlayer == PLAYER ? GridValue.PLAYER : GridValue.COMPUTER;

        int row = gridCoord.getRow();
        int col = gridCoord.getCol();

        gameState.getGrid()[row][col] = gridValue;
    }
}

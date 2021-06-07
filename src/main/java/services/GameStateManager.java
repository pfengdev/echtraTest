package services;

import models.GridCoord;
import enums.GridValue;
import models.GameState;
import models.Input;

import static messages.Message.*;

public class GameStateManager {

    private GameState gameState;
    private InputService inputService;

    private static final boolean PLAYER = true;
    private static final boolean COMPUTER = false;

    public GameStateManager() {
        inputService = new InputService();
        gameState = new GameState();
    }

    public GameState updateGameState(Input input) {
        clearMessage();
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

package services;

import models.GameState;
import models.Input;

import static messages.Message.ENTER_INPUT;
import static messages.Message.INPUT_INVALID;

public class GameStateManager {

    private GameState gameState;
    private InputService inputService;

    public GameStateManager() {
        inputService = new InputService();
        gameState = new GameState();
    }

    public GameState updateGameState(Input input) {
        clearMessage();
        if (!inputService.isInputValid(input)) {
            addToMessage(INPUT_INVALID);
        }
        addToMessage(ENTER_INPUT);
        return gameState;
    }

    public void clearMessage() {
        gameState.setMessage("");
    }

    public void addToMessage(String newMsg) {
        String currMsg = gameState.getMessage();
        gameState.setMessage(currMsg + "\n" + newMsg);
    }
}

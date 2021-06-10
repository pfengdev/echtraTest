import models.GameState;
import models.Input;
import renderer.Renderer;
import services.GameStateManager;
import services.InputService;

public class Main {

    private GameStateManager gameStateManager;
    private InputService inputService;
    private Renderer renderer;

    public Main() {
        gameStateManager = new GameStateManager();
        inputService = new InputService();
        renderer = new Renderer();
    }

    public static void main(String[] args) {
        Main main = new Main();
        Input input = new Input();
        while (!main.gameOver()) {
            GameState gameState = main.updateGameState(input);
            main.render(gameState);
            if (!main.gameOver()) {
                input = main.getInput();
            }
            if (main.isInputQuit(input)) {
                break;
            }
        }
    }

    private Input getInput() {
        return inputService.getInput();
    }

    private GameState updateGameState(Input input) {
        return gameStateManager.updateGameState(input);
    }

    private void render(GameState gameState) {
        renderer.render(gameState);
    }

    private boolean gameOver() {
        return gameStateManager.isGameOver();
    }

    private boolean isInputValid(Input input) {
        return inputService.isInputValid(input);
    }

    private boolean isInputQuit(Input input) {
        return inputService.isInputQuit(input);
    }
}

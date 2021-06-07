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
    }

    public static void main(String[] args) {
        Main main = new Main();
        while (!main.gameOver()) {
            System.out.print("Please enter a command: ");
            Input input = main.getInput();
            if (!main.isInputValid(input)) {
                System.out.println("Input is invalid. Please try again.");
                continue;
            }
            if (main.isInputQuit(input)) {
                break;
            }
            GameState gameState = main.updateGameState(input);
            main.render(gameState);
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
        return false;
    }

    private boolean isInputValid(Input input) {
        return inputService.isInputValid(input);
    }

    private boolean isInputQuit(Input input) {
        return inputService.isInputQuit(input);
    }
}

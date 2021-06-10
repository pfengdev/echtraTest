import models.GameState;
import models.Input;
import services.RendererService;
import services.GameStateManager;
import services.InputService;

public class Main {

    private GameStateManager gameStateManager;
    private InputService inputService;
    private RendererService renderer;

    public Main() {
        gameStateManager = new GameStateManager();
        inputService = new InputService();
        renderer = new RendererService();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run() {
        Input input = new Input();
        while (!gameStateManager.isGameOver()) {
            GameState gameState = gameStateManager.updateGameState(input);
            renderer.render(gameState);
            if (!gameStateManager.isGameOver()) {
                input = inputService.getInput();
            }
            if (inputService.isInputQuit(input)) {
                break;
            }
        }
    }
}

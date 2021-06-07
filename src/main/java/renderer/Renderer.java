package renderer;

import models.GameState;

public class Renderer {

    public void render(GameState gameState) {
        System.out.println(gameState.getMessage());
    }
}

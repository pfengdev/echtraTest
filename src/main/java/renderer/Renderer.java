package renderer;

import enums.GridValue;
import models.GameState;

public class Renderer {

    public void render(GameState gameState) {
        System.out.println(gameState.getMessage());
        printGrid(gameState);
    }

    private void printGrid(GameState gameState) {
        GridValue[][] grid = gameState.getGrid();
        for (int i = 0; i < GameState.GRID_SIZE; i++) {
            System.out.print("|");
            for (int j = 0; j < GameState.GRID_SIZE; j++) {
                System.out.print(" ");
                switch(grid[i][j]) {
                    case EMPTY: System.out.print(" "); break;
                    case PLAYER: System.out.print("O"); break;
                    case COMPUTER: System.out.print("X"); break;
                    default: break;
                }
                System.out.print(" |");
            }
            System.out.println();
        }
    }
}

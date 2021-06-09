package models;

import lombok.Data;

@Data
public class GameState {
    private GridValue[][] grid;
    private String message;

    public static final int GRID_SIZE = 3;

    public GameState() {
        grid = new GridValue[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = GridValue.EMPTY;
            }
        }
    }
}

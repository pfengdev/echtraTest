package models;

import enums.GridValue;
import lombok.Data;

@Data
public class GameState {
    private GridValue[][] grid;
    private String message;

    private static final int SIZE = 3;

    public GameState() {
        grid = new GridValue[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = GridValue.EMPTY;
            }
        }
    }
}

package models;

import lombok.Data;

@Data
public class GameState {
    private boolean[][] grid;
    private String message;
}

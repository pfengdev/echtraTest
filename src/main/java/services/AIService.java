package services;

import models.GridCoord;
import models.GridValue;

public class AIService {

    private static final int COMPUTER_WIN_SCORE = 10;

    private static final int PLAYER_WIN_SCORE = -10;

    private static final int DRAW_SCORE = 0;

    public GridCoord makeMove(GridValue[][] grid) {
        int maxScore = Integer.MIN_VALUE;
        int moveRow = -1;
        int moveCol = -1;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == GridValue.EMPTY) {
                    grid[i][j] = GridValue.COMPUTER;
                    int score = getMinMaxScore(grid, 0, true);
                    if (score > maxScore) {
                        maxScore = score;
                        moveRow = i;
                        moveCol = j;
                    }
                    grid[i][j] = GridValue.EMPTY;
                }
            }
        }

        return new GridCoord(moveRow, moveCol);
    }

    private int getMinMaxScore(GridValue[][] grid, int depth, boolean isComputer) {

        int score = getScore(grid);

        if (score == COMPUTER_WIN_SCORE) {
            return score - depth;
        }

        if (score == PLAYER_WIN_SCORE) {
            return score + depth;
        }

        if (isFilled(grid)) {
            return DRAW_SCORE;
        }

        if (isComputer) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == GridValue.EMPTY) {
                        grid[i][j] = GridValue.COMPUTER;
                        int minMax = getMinMaxScore(grid, depth + 1, !isComputer);
                        max = Math.max(max, minMax);
                        grid[i][j] = GridValue.EMPTY;
                    }
                }
            }
            return max;
        } else {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == GridValue.EMPTY) {
                        grid[i][j] = GridValue.PLAYER;
                        int minMax = getMinMaxScore(grid, depth + 1, !isComputer);
                        min = Math.min(min, minMax);
                        grid[i][j] = GridValue.EMPTY;
                    }
                }
            }
            return min;
        }
    }

    private int getScore(GridValue[][] grid) {

        if (hasWinningLine(grid, GridValue.COMPUTER)) {
            return COMPUTER_WIN_SCORE;
        }

        if (hasWinningLine(grid, GridValue.PLAYER)) {
            return PLAYER_WIN_SCORE;
        }

        return DRAW_SCORE;
    }

    public boolean isFilled(GridValue[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == GridValue.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean hasWinningLine(GridValue[][] grid, GridValue gridValue) {
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][0] == gridValue && grid[i][1] == gridValue &&
                grid[i][2] == gridValue) {
                return true;
            }
        }

        for (int j = 0; j < grid.length; j++) {
            if (grid[0][j] == gridValue && grid[1][j] == gridValue &&
                    grid[2][j] == gridValue) {
                return true;
            }
        }

        if (grid[0][0] == gridValue && grid[1][1] == gridValue &&
                grid[2][2] == gridValue) {
            return true;
        }

        if (grid[2][0] == gridValue && grid[1][1] == gridValue &&
                grid[0][2] == gridValue) {
            return true;
        }

        return false;
    }
}

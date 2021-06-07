package models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Input {
    private GridCoord gridCoord;
    private char command;

    public Input(char command) {
        this.command = command;
    }
}
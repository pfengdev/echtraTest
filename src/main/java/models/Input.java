package models;

import enums.GridValue;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Input {
    private GridValue gridValue;
    private char command;

    public Input(char command) {
        this.command = command;
    }
}
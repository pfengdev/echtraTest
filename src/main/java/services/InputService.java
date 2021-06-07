package services;

import enums.GridValue;
import models.Input;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class InputService {

    private static Set<Character> VALID_INPUTS;

    static {
        initValidInputs();
    }

    public InputService() {
    }

    public Input getInput() {
        Scanner in = new Scanner(System.in);

        String s = in.nextLine();
        char command = s.charAt(0);

        Input input = parseInput(command);
        return input;
    }

    public boolean isInputValid(Input input) {
        if (input == null) {
            return false;
        }
        char command = input.getCommand();
        return VALID_INPUTS.contains(command) || command == 0;
    }

    public boolean isInputQuit(Input input) {
        if (input == null) {
            return false;
        }
        char command = input.getCommand();
        return command == 'q';
    }

    private Input parseInput(char command) {
        Input input = new Input(command);
        switch (command) {
            case 't': input.setGridValue(GridValue.TOP_LEFT); break;
            case 'y': input.setGridValue(GridValue.TOP_CTR); break;
            case 'u': input.setGridValue(GridValue.TOP_RIGHT); break;
            case 'g': input.setGridValue(GridValue.MID_LEFT); break;
            case 'h': input.setGridValue(GridValue.MID_CTR); break;
            case 'j': input.setGridValue(GridValue.MID_RIGHT); break;
            case 'b': input.setGridValue(GridValue.BOT_LEFT); break;
            case 'n': input.setGridValue(GridValue.BOT_CTR); break;
            case 'm': input.setGridValue(GridValue.BOT_RIGHT); break;
            default: break;
        }
        return input;
    }

    private static void initValidInputs() {
        if (VALID_INPUTS == null) {
            VALID_INPUTS = new HashSet();
            VALID_INPUTS.add('q');
            VALID_INPUTS.add('t');
            VALID_INPUTS.add('y');
            VALID_INPUTS.add('u');
            VALID_INPUTS.add('g');
            VALID_INPUTS.add('h');
            VALID_INPUTS.add('j');
            VALID_INPUTS.add('b');
            VALID_INPUTS.add('n');
            VALID_INPUTS.add('m');
        }
    }

}

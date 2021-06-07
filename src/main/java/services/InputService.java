package services;

import models.GridCoord;
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
        return VALID_INPUTS.contains(command);
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
        GridCoord gridCoord = null;
        switch (command) {
            case 't': gridCoord = new GridCoord(0, 0); break;
            case 'y': gridCoord = new GridCoord(0, 1); break;
            case 'u': gridCoord = new GridCoord(0, 2); break;
            case 'g': gridCoord = new GridCoord(1, 0); break;
            case 'h': gridCoord = new GridCoord(1, 1); break;
            case 'j': gridCoord = new GridCoord(1, 2); break;
            case 'b': gridCoord = new GridCoord(2, 0); break;
            case 'n': gridCoord = new GridCoord(2, 1); break;
            case 'm': gridCoord = new GridCoord(2, 2); break;
            default: break;
        }
        input.setGridCoord(gridCoord);
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

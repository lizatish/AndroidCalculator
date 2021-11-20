package com.tishkovets.lab3;

import com.tishkovets.lab3.commands.Action;
import com.tishkovets.lab3.commands.CommandType;

import java.util.Deque;
import java.util.LinkedList;

public class IntegerCalculator {
    private final Deque<CommandType> calculations;
    private final boolean islastDigit = false;
    private final String lastDigit = "";

    public IntegerCalculator() {
        this.calculations = new LinkedList<>();
    }

    //    public void set_operation(String value) {
//        if (value.matches("[-+]?\\d+")) {
//            this.islastDigit = true;
//            this.lastDigit += value;
//        } else if (this.islastDigit) {
//            this.calculations.addLast(lastDigit);
//            this.calculations.addLast(value);
//
//            this.lastDigit = "";
//            this.islastDigit = false;
//        }
//        this.calculations.addLast(value);
//    }
//
    public void add_command(CommandType command) {
        if (command instanceof Action && this.calculations.size() != 0
                && this.calculations.getLast() instanceof Action) {
            this.calculations.removeLast();
            this.calculations.addLast(command);
        } else if (!(command instanceof Action && this.calculations.size() == 0)) {
            this.calculations.addLast(command);
        }
    }

    public void remove_last_command() {
        if (this.calculations.size() != 0) {
            this.calculations.removeLast();
        }
    }

    public void clear_all() {
        this.calculations.clear();
    }

    public void change_sign() {

    }

    public void calculate() {

    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (CommandType elem : this.calculations) {
            result.append(elem.toString());
        }
        return result.toString();
    }
}


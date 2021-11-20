package com.tishkovets.lab3;

import com.tishkovets.lab3.commands.Action;
import com.tishkovets.lab3.commands.CommandType;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class IntegerCalculator {
    private final Deque<CommandType> calculations;

    public IntegerCalculator() {
        this.calculations = new LinkedList<>();
    }

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

    public int calculate() {
        List<String> result = this.get_char_array();
        int numerOfMults = 0;
        for (String elem : result) {
            if (elem.equals("*") || elem.equals("/")) {
                numerOfMults += 1;
            }
        }

        int x = 0;
        while (numerOfMults != 0) {
            if (result.get(x).equals("*")) {
                Integer prev = Integer.valueOf(result.get(x - 1));
                Integer past = Integer.valueOf(result.get(x + 1));
                result.remove(x + 1);
                result.remove(x);
                result.remove(x - 1);

                result.add(x - 1, Integer.toString(prev * past));
                numerOfMults -= 1;
                x -= 1;
            } else if (result.get(x).equals("/")) {
                Integer prev = Integer.valueOf(result.get(x - 1));
                Integer past = Integer.valueOf(result.get(x + 1));
                result.remove(x + 1);
                result.remove(x);
                result.remove(x - 1);
                result.add(x - 1, Integer.toString(prev / past));
                numerOfMults -= 1;
                x -= 1;
            } else {
                x += 1;
            }
        }

        x = 0;
        while (result.size() != 1) {
            if (result.get(x).equals("+")) {
                Integer prev = Integer.valueOf(result.get(x - 1));
                Integer past = Integer.valueOf(result.get(x + 1));
                result.remove(x + 1);
                result.remove(x);
                result.remove(x - 1);

                result.add(x - 1, Integer.toString(prev + past));
                numerOfMults -= 1;
                x -= 1;
            } else if (result.get(x).equals("-")) {
                Integer prev = Integer.valueOf(result.get(x - 1));
                Integer past = Integer.valueOf(result.get(x + 1));
                result.remove(x + 1);
                result.remove(x);
                result.remove(x - 1);

                result.add(x - 1, Integer.toString(prev - past));
                numerOfMults -= 1;
                x -= 1;
            } else {
                x += 1;
            }
        }
        return Integer.parseInt(result.get(0));
    }

    public LinkedList<String> get_char_array() {
        LinkedList<String> result = new LinkedList<>();
        StringBuilder current_number = new StringBuilder();

        for (CommandType elem : this.calculations) {
            if (elem.getWeight() == 0) {
                current_number.append(elem.toString());
            } else {
                if (!current_number.toString().equals("")) {
                    result.add(current_number.toString());
                    current_number = new StringBuilder();
                }
                result.add(elem.toString());
            }
        }
        if (!current_number.toString().equals("")) {
            result.add(current_number.toString());
        }
        return result;
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


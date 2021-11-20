package com.tishkovets.lab3;

import com.tishkovets.lab3.commands.Action;
import com.tishkovets.lab3.commands.CommandType;

import java.util.Arrays;
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

    private int getNumberOfMultiplyAndDivision(List<String> arr) {
        int number = 0;
        for (String elem : arr) {
            if (elem.equals(Action.MULTIPLY.toString()) || elem.equals(Action.DIVISION.toString())) {
                number += 1;
            }
        }
        return number;
    }

    private List<Integer> getPreviousAndPastElements(int index, List<String> arr) {
        Integer prev = Integer.valueOf(arr.get(index - 1));
        Integer past = Integer.valueOf(arr.get(index + 1));
        return Arrays.asList(prev, past);
    }

    private void removePrevPastCurrentElements(int index, List<String> arr) {
        arr.remove(index + 1);
        arr.remove(index);
        arr.remove(index - 1);
    }

    private boolean equalCalculationMultiplyOrDivision(int index, List<String> arr) {
        boolean result = false;
        if (arr.get(index).equals(Action.MULTIPLY.toString())) {
            List<Integer> prevPastValues = getPreviousAndPastElements(index, arr);
            removePrevPastCurrentElements(index, arr);
            arr.add(index - 1, Integer.toString(prevPastValues.get(0) * prevPastValues.get(1)));
            result = true;
        } else if (arr.get(index).equals(Action.DIVISION.toString())) {
            List<Integer> prevPastValues = getPreviousAndPastElements(index, arr);
            removePrevPastCurrentElements(index, arr);
            arr.add(index - 1, Integer.toString(prevPastValues.get(0) / prevPastValues.get(1)));
            result = true;
        }
        return result;
    }

    private boolean equalCalculationSubtractAndAddition(int index, List<String> arr) {
        boolean result = false;
        if (arr.get(index).equals(Action.SUBTRACT.toString())) {
            List<Integer> prevPastValues = getPreviousAndPastElements(index, arr);
            removePrevPastCurrentElements(index, arr);
            arr.add(index - 1, Integer.toString(prevPastValues.get(0) - prevPastValues.get(1)));
            result = true;
        } else if (arr.get(index).equals(Action.ADDITION.toString())) {
            List<Integer> prevPastValues = getPreviousAndPastElements(index, arr);
            removePrevPastCurrentElements(index, arr);
            arr.add(index - 1, Integer.toString(prevPastValues.get(0) + prevPastValues.get(1)));
            result = true;
        }
        return result;
    }

    public int calculate() {

        if (calculations.getLast() instanceof Action) {
            calculations.removeLast();
        }
        List<String> result = this.convertDigitCommandsToFullDigits();

        int numberOfMultiplyOrDivision = getNumberOfMultiplyAndDivision(result);

        int x = 0;
        while (numberOfMultiplyOrDivision != 0) {
            if (equalCalculationMultiplyOrDivision(x, result)) {
                numberOfMultiplyOrDivision -= 1;
                x -= 1;
            } else {
                x += 1;
            }
        }

        x = 0;
        while (result.size() != 1) {
            if (equalCalculationSubtractAndAddition(x, result)) {
                x -= 1;
            } else {
                x += 1;
            }
        }
        return Integer.parseInt(result.get(0));
    }

    private LinkedList<String> convertDigitCommandsToFullDigits() {
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


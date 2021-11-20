package com.tishkovets.lab3;

import androidx.annotation.NonNull;

import com.tishkovets.lab3.commands.Action;
import com.tishkovets.lab3.commands.CommandType;
import com.tishkovets.lab3.commands.Digit;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

// todo добавить обработку деления на ноль
// todo добавить смену знака

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

    public void calculate() {

        if (calculations.getLast() instanceof Action) {
            calculations.removeLast();
        }
        List<String> tempList = this.convertDigitCommandsToFullDigits();
        int numberOfMultiplyOrDivision = getNumberOfMultiplyAndDivision(tempList);

        int index = 0;
        while (numberOfMultiplyOrDivision != 0) {
            if (equalCalculationMultiplyOrDivision(index, tempList)) {
                numberOfMultiplyOrDivision -= 1;
                index -= 1;
            } else {
                index += 1;
            }
        }

        index = 0;
        while (tempList.size() != 1) {
            if (equalCalculationSubtractAndAddition(index, tempList)) {
                index -= 1;
            } else {
                index += 1;
            }
        }

        this.updateStack(tempList.get(0));
    }

    private void updateStack(String answer) {
        String[] stringResult = answer.split("");
        this.clear_all();
        for (String str : stringResult) {
            for (Digit digit : Digit.values()) {
                if (digit.toString().equals(str)) {
                    this.calculations.addLast(digit);
                    break;
                }
            }
        }
    }

    private LinkedList<String> convertDigitCommandsToFullDigits() {
        LinkedList<String> result = new LinkedList<>();
        StringBuilder current_number = new StringBuilder();

        for (CommandType elem : this.calculations) {
            if (elem instanceof Digit) {
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

    @NonNull
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (CommandType elem : this.calculations) {
            result.append(elem.toString());
        }
        return result.toString();
    }
}


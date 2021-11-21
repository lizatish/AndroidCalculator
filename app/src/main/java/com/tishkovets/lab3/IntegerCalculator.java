package com.tishkovets.lab3;

import androidx.annotation.NonNull;

import com.tishkovets.lab3.commands.Action;
import com.tishkovets.lab3.commands.CommandType;
import com.tishkovets.lab3.commands.Digit;
import com.tishkovets.lab3.commands.FullDigit;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

// todo добавить обработку деления на ноль
// todo добавить смену знака
// todo добавить начало с минуса

public class IntegerCalculator {
    private final Deque<CommandType> calculations;

    public IntegerCalculator() {
        this.calculations = new LinkedList<>();
    }

    public void add_command(CommandType command) {
        //TODO вынести в метод
        if (command instanceof Action && this.calculations.size() != 0
                && this.calculations.getLast() instanceof Action) {
            this.calculations.removeLast();
            this.calculations.addLast(command);
        } else if (Action.SUBTRACT.equals(command) && this.calculations.size() == 0) {
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
        for (int i = this.calculations.size() - 1; i >= 0; i--) {
        }

    }

    private void removePrevPastCurrentElements(int index, List<CommandType> arr) {
        arr.remove(index + 1);
        arr.remove(index);
        arr.remove(index - 1);
    }

    private boolean executeActionsWithWeight(List<CommandType> arr, int index, int weight) {

        boolean result = false;
        CommandType element = arr.get(index);
        if (element.getWeight() == weight && element instanceof Action) {
            int prev = Integer.parseInt(arr.get(index - 1).toString());
            int past = Integer.parseInt(arr.get(index + 1).toString());
            removePrevPastCurrentElements(index, arr);
            arr.add(index - 1, new FullDigit(((Action) element).calculate(prev, past)));
            result = true;
        }
        return result;
    }

    private int getMaxWeight(List<CommandType> arr) {
        int maxWeight = 0;
        for (CommandType elem : arr) {
            int currentWeight = elem.getWeight();
            if (currentWeight > maxWeight) {
                maxWeight = currentWeight;
            }
        }
        return maxWeight;
    }

    public void calculate() {
        //todo вынести в метод removeLastAction
        if (calculations.getLast() instanceof Action) {
            calculations.removeLast();
        }
        List<CommandType> tempList = this.convertDigitCommandsToFullDigits();

        int weight = this.getMaxWeight(tempList);
        while (weight != 0) {
            int index = 0;
            while (index <= tempList.size() - 1) {
                if (this.executeActionsWithWeight(tempList, index, weight)) {
                    index -= 1;
                } else {
                    index += 1;
                }
                weight = this.getMaxWeight(tempList);
            }
        }
        this.updateStack(tempList.get(0).toString());
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

    private LinkedList<CommandType> convertDigitCommandsToFullDigits() {
        LinkedList<CommandType> result = new LinkedList<>();

        StringBuilder current_number = new StringBuilder();

        for (CommandType elem : this.calculations) {
            if (elem instanceof Digit) {
                current_number.append(elem.toString());
            } else {
                if (!current_number.toString().equals("")) {
                    result.add(new FullDigit(Integer.parseInt(current_number.toString())));
                    current_number = new StringBuilder();
                }
                result.add(elem);
            }
        }
        if (!current_number.toString().equals("")) {
            result.add(new FullDigit(Integer.parseInt(current_number.toString())));
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


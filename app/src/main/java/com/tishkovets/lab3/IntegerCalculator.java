package com.tishkovets.lab3;

import androidx.annotation.NonNull;

import com.tishkovets.lab3.commands.CommandType;
import com.tishkovets.lab3.commands.FullOperand;
import com.tishkovets.lab3.commands.Operator;
import com.tishkovets.lab3.commands.SimpleOperand;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class IntegerCalculator {
    private final Deque<CommandType> calculations;

    public IntegerCalculator() {
        this.calculations = new LinkedList<>();
    }

    public void addCommand(CommandType command) {
        if (isLastOperator(command)) {
            this.calculations.removeLast();
            this.calculations.addLast(command);
        } else if (isFirstNegativeNumber(command)) {
            this.calculations.addLast(command);
        } else if (isOperandOrNotFirstOperator(command)) {
            this.calculations.addLast(command);
        }
    }

    public void removeLastCommand() {
        if (this.calculations.size() != 0) {
            this.calculations.removeLast();
        }
    }

    public void clearAll() {
        this.calculations.clear();
    }

    public void changeSign() {
        Deque<CommandType> lastSimpleOperands = new LinkedList<>();
        int index = this.calculations.size() - 1;
        while (index >= 0) {
            CommandType currentCommand = this.calculations.getLast();
            if (currentCommand instanceof SimpleOperand) {
                lastSimpleOperands.addFirst(currentCommand);
                this.calculations.removeLast();
            } else if (Operator.ADDITION.equals(currentCommand)) {
                this.calculations.removeLast();
                this.calculations.addLast(Operator.SUBTRACT);
                this.addCalculationsFromList(lastSimpleOperands);
                break;
            } else if (Operator.SUBTRACT.equals(currentCommand)) {
                this.calculations.removeLast();
                if (index != 0 && !(this.calculations.getLast() instanceof Operator)) {
                    this.calculations.addLast(Operator.ADDITION);
                }
                this.addCalculationsFromList(lastSimpleOperands);
                break;
            } else {
                this.calculations.addLast(Operator.SUBTRACT);
                this.addCalculationsFromList(lastSimpleOperands);
                break;
            }
            index -= 1;
        }

        if (this.calculations.size() == 0) {
            this.calculations.addLast(Operator.SUBTRACT);
            this.addCalculationsFromList(lastSimpleOperands);
        }
    }

    public void calculate() {
        this.removeLastOperatorIfExists();

        List<CommandType> tempList = this.convertSimpleOperandsToFullOperands();

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
        this.updateStack(tempList.get(0));
    }

    private void updateStack(CommandType answer) {
        this.clearAll();

        String stringAnalog = answer.toString();
        int integerAnalog = Integer.parseInt(stringAnalog);

        if (integerAnalog < 0) {
            this.calculations.addLast(Operator.SUBTRACT);
            stringAnalog = String.valueOf(Math.abs(integerAnalog));
        }

        String[] stringResult = stringAnalog.split("");
        for (String str : stringResult) {
            for (SimpleOperand digit : SimpleOperand.values()) {
                if (digit.toString().equals(str)) {
                    this.calculations.addLast(digit);
                    break;
                }
            }
        }
    }

    private void removeLastOperatorIfExists() {
        if (calculations.getLast() instanceof Operator) {
            calculations.removeLast();
        }
    }

    private LinkedList<CommandType> convertSimpleOperandsToFullOperands() {
        LinkedList<CommandType> result = new LinkedList<>();

        StringBuilder current_number = new StringBuilder();

        for (CommandType elem : this.calculations) {
            if (elem instanceof SimpleOperand || (Operator.SUBTRACT.equals(elem) && current_number.length() == 0)) {
                current_number.append(elem.toString());
            } else {
                if (!current_number.toString().equals("")) {
                    result.add(new FullOperand(Integer.parseInt(current_number.toString())));
                    current_number = new StringBuilder();
                }
                result.add(elem);
            }
        }
        if (!current_number.toString().equals("")) {
            result.add(new FullOperand(Integer.parseInt(current_number.toString())));
        }
        return result;
    }


    private void removePrevPastCurrentElements(int index, List<CommandType> arr) {
        arr.remove(index + 1);
        arr.remove(index);
        arr.remove(index - 1);
    }

    private boolean executeActionsWithWeight(List<CommandType> arr, int index, int weight) {
        boolean result = false;
        CommandType element = arr.get(index);
        if (element.getWeight() == weight && element instanceof Operator) {
            int prev = Integer.parseInt(arr.get(index - 1).toString());
            int past = Integer.parseInt(arr.get(index + 1).toString());
            removePrevPastCurrentElements(index, arr);
            arr.add(index - 1, new FullOperand(((Operator) element).calculate(prev, past)));
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

    private boolean isOperandOrNotFirstOperator(CommandType command) {
        return !(command instanceof Operator && this.calculations.size() == 0);
    }

    private boolean isFirstNegativeNumber(CommandType command) {
        return Operator.SUBTRACT.equals(command) && this.calculations.size() == 0;
    }

    private boolean isLastOperator(CommandType command) {
        return command instanceof Operator && this.calculations.size() != 0
                && this.calculations.getLast() instanceof Operator;
    }

    private void addCalculationsFromList(Deque<CommandType> lastSimpleOperands) {
        for (CommandType elem : lastSimpleOperands) {
            this.calculations.addLast(elem);
        }
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


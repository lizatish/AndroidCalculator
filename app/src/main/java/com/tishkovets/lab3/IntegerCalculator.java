package com.tishkovets.lab3;

import com.tishkovets.lab3.commands.Action;
import com.tishkovets.lab3.commands.CommandType;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class IntegerCalculator {
    private final Deque<CommandType> calculations;


    private static final String operators = "-+/*";
    private static final String operands = "0123456789";

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

    public void calculate() {
        LinkedList<String> resrult = this.get_char_array();
        int result = evaluatePostfix(convert2Postfix());
        int l = 0;
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

    public String convert2Postfix() {
        char[] chars = this.toString().toCharArray();


        Stack<Character> stack = new Stack<>();
        StringBuilder out = new StringBuilder(this.calculations.size());

        for (char c : chars) {
            if (isOperator(c)) {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    if (operatorGreaterOrEqual(stack.peek(), c)) {
                        out.append(stack.pop());
                    } else {
                        break;
                    }
                }
                stack.push(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    out.append(stack.pop());
                }
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (isOperand(c)) {
                out.append(c);
            }
        }
        while (!stack.empty()) {
            out.append(stack.pop());
        }
        return out.toString();
    }


    private int getPrecedence(char operator) {
        int ret = 0;
        if (operator == '-' || operator == '+') {
            ret = 1;
        } else if (operator == '*' || operator == '/') {
            ret = 2;
        }
        return ret;
    }

    private boolean operatorGreaterOrEqual(char op1, char op2) {
        return getPrecedence(op1) >= getPrecedence(op2);
    }

    private boolean isOperator(char val) {
        return operators.indexOf(val) >= 0;
    }

    private boolean isOperand(char val) {
        return operands.indexOf(val) >= 0;
    }

    public int evaluatePostfix(String postfixExpr) {
        char[] chars = postfixExpr.toCharArray();
        Stack<Integer> stack = new Stack<Integer>();
        for (char c : chars) {
            if (isOperand(c)) {
                stack.push(c - '0'); // convert char to int val
            } else if (isOperator(c)) {
                int op1 = stack.pop();
                int op2 = stack.pop();
                int result;
                switch (c) {
                    case '*':
                        result = op1 * op2;
                        stack.push(result);
                        break;
                    case '/':
                        result = op2 / op1;
                        stack.push(result);
                        break;
                    case '+':
                        result = op1 + op2;
                        stack.push(result);
                        break;
                    case '-':
                        result = op2 - op1;
                        stack.push(result);
                        break;
                }
            }
        }
        return stack.pop();
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


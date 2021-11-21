package com.tishkovets.lab3.commands;


import androidx.annotation.NonNull;

public enum Operator implements CommandType {
    DIVISION("/", 2) {
        @Override
        public int calculate(int left, int right) {
            if (right == 0) {
                throw new IllegalArgumentException("На ноль делить нельзя");
            }
            return left / right;
        }
    },
    MULTIPLY("*", 2) {
        @Override
        public int calculate(int left, int right) {
            return left * right;
        }
    },
    SUBTRACT("-", 1) {
        @Override
        public int calculate(int left, int right) {
            return left - right;
        }
    },
    ADDITION("+", 1) {
        @Override
        public int calculate(int left, int right) {
            return left + right;
        }
    };

    private final String symbol;
    private final int weight;

    Operator(String symbol, int weight) {
        this.symbol = symbol;
        this.weight = weight;
    }

    public abstract int calculate(int left, int right);

    @Override
    public int getWeight() {
        return this.weight;
    }

    @NonNull
    @Override
    public String toString() {
        return String.valueOf(this.symbol);
    }

}
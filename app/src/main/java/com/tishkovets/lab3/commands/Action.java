package com.tishkovets.lab3.commands;


public enum Action implements CommandType {
    DIVISION('/', 2),
    MULTIPLY('*', 2),
    SUBTRACT('-', 1),
    ADDITION('+', 1);

    private final char symbol;
    private final int weight;


    Action(char symbol, int weight) {
        this.symbol = symbol;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.valueOf(this.symbol);
    }

    public int getWeight() {
        return weight;
    }
}
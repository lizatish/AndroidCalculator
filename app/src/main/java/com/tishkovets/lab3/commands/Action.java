package com.tishkovets.lab3.commands;


public enum Action implements CommandType {
    DIVISION('/'),
    MULTIPLY('*'),
    SUBTRACT('-'),
    ADDITION('+');

    private final char symbol;

    Action(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return String.valueOf(this.symbol);
    }
}
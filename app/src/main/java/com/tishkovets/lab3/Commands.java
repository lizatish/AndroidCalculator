package com.tishkovets.lab3;


public enum Commands {
    DIVIDION('/'),
    MULTIPLY('*'),
    SUBSTRACT('-'),
    ADDITION('+');

    private final char symbol;

    Commands(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return String.valueOf(this.symbol);
    }
}
package com.tishkovets.lab3.commands;

public enum Digit implements CommandType {

    ZERO('0', 0),
    ONE('1', 0),
    TWO('2', 0),
    THREE('3', 0),
    FOUR('4', 0),
    FIVE('5', 0),
    SIX('6', 0),
    SEVEN('7', 0),
    EIGHT('8', 0),
    NINE('9', 0);

    private final char symbol;
    private final int weight;


    Digit(char symbol, int weight) {
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
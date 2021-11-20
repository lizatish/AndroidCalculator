package com.tishkovets.lab3;


public enum Command {
    DIVISION('/'),
    MULTIPLY('*'),
    SUBTRACT('-'),
    ADDITION('+'),
    ZERO('0'),
    ONE('1'),
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    NINE('9');

    private final char symbol;

    Command(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return String.valueOf(this.symbol);
    }
}
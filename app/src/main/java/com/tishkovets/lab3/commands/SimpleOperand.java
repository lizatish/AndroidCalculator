package com.tishkovets.lab3.commands;

import androidx.annotation.NonNull;

public enum SimpleOperand implements CommandType {

    ZERO("0", 0),
    ONE("1", 0),
    TWO("2", 0),
    THREE("3", 0),
    FOUR("4", 0),
    FIVE("5", 0),
    SIX("6", 0),
    SEVEN("7", 0),
    EIGHT("8", 0),
    NINE("9", 0);

    private final String symbol;
    private final int weight;

    SimpleOperand(String symbol, int weight) {
        this.symbol = symbol;
        this.weight = weight;
    }

    @NonNull
    @Override
    public String toString() {
        return this.symbol;
    }

    @Override
    public int getWeight() {
        return this.weight;
    }
}
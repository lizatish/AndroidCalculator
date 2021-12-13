package com.tishkovets.lab3.commands;

import androidx.annotation.NonNull;

public enum HandleOperator implements CommandType {

    CLEAR("CLR", 0),
    DELETE("DEL", 0),
    CHANGE_SIGN("+-", 0),
    EQUALS("=", 0);

    private final String symbol;
    private final int weight;

    HandleOperator(String symbol, int weight) {
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

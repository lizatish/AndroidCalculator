package com.tishkovets.lab3.commands;

import androidx.annotation.NonNull;

public class FullOperand implements CommandType {

    private final int value;
    private final int weight = 0;

    public FullOperand(int value) {
        this.value = value;
    }

    @Override
    public int getWeight() {
        return this.weight;
    }

    @NonNull
    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}

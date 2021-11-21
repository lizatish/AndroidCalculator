package com.tishkovets.lab3.commands;

import androidx.annotation.NonNull;

public class FullDigit implements CommandType {

    private int value;
    private final int weight = 0;

    public FullDigit(int value) {
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

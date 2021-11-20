package com.tishkovets.lab3;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final IntegerCalculator calculator;
    private TextView outputText;

    public MainActivity() {
        this.calculator = new IntegerCalculator();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.outputText = findViewById(R.id.resultText);
    }

    public void buttonClear_OnClick(View view) {
        calculator.clear_all();
        this.outputText.setText(calculator.toString());
    }

    public void buttonDelete_OnClick(View view) {
        calculator.remove_last_command();
        this.outputText.setText(calculator.toString());
    }

    public void buttonSum_OnClick(View view) {
        calculator.add_command(Command.ADDITION);
        this.outputText.setText(calculator.toString());
    }

    public void buttonChangeSign_OnClick(View view) {
        calculator.change_sign();
        this.outputText.setText(calculator.toString());
    }

    public void buttonDivide_OnClick(View view) {
        calculator.add_command(Command.DIVISION);
        this.outputText.setText(calculator.toString());
    }

    public void buttonMultiply_OnClick(View view) {
        calculator.add_command(Command.MULTIPLY);
        this.outputText.setText(calculator.toString());
    }

    public void buttonSubstract_OnClick(View view) {
        calculator.add_command(Command.SUBTRACT);
        this.outputText.setText(calculator.toString());
    }

    public void buttonEquals_OnClick(View view) {
        calculator.calculate();
        this.outputText.setText(calculator.toString());
    }

    public void buttonNine_OnClick(View view) {
        calculator.add_command(Command.NINE);
        this.outputText.setText(calculator.toString());
    }

    public void buttonEight_OnClick(View view) {
        calculator.add_command(Command.EIGHT);
        this.outputText.setText(calculator.toString());
    }

    public void buttonSeven_OnClick(View view) {
        calculator.add_command(Command.SEVEN);
        this.outputText.setText(calculator.toString());
    }

    public void buttonSix_OnClick(View view) {
        calculator.add_command(Command.SIX);
        this.outputText.setText(calculator.toString());
    }

    public void buttonFive_OnClick(View view) {
        calculator.add_command(Command.FIVE);
        this.outputText.setText(calculator.toString());
    }

    public void buttonFour_OnClick(View view) {
        calculator.add_command(Command.FOUR);
        this.outputText.setText(calculator.toString());
    }

    public void buttonThree_OnClick(View view) {
        calculator.add_command(Command.THREE);
        this.outputText.setText(calculator.toString());
    }

    public void buttonTwo_OnClick(View view) {
        calculator.add_command(Command.TWO);
        this.outputText.setText(calculator.toString());
    }

    public void buttonOne_OnClick(View view) {
        calculator.add_command(Command.ONE);
        this.outputText.setText(calculator.toString());
    }

    public void buttonZero_OnClick(View view) {
        calculator.add_command(Command.ZERO);
        this.outputText.setText(calculator.toString());
    }
}
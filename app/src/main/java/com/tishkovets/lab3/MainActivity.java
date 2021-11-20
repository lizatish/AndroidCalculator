package com.tishkovets.lab3;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tishkovets.lab3.commands.Action;
import com.tishkovets.lab3.commands.Digit;

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
        calculator.add_command(Action.ADDITION);
        this.outputText.setText(calculator.toString());
    }

    public void buttonChangeSign_OnClick(View view) {
        calculator.change_sign();
        this.outputText.setText(calculator.toString());
    }

    public void buttonDivide_OnClick(View view) {
        calculator.add_command(Action.DIVISION);
        this.outputText.setText(calculator.toString());
    }

    public void buttonMultiply_OnClick(View view) {
        calculator.add_command(Action.MULTIPLY);
        this.outputText.setText(calculator.toString());
    }

    public void buttonSubstract_OnClick(View view) {
        calculator.add_command(Action.SUBTRACT);
        this.outputText.setText(calculator.toString());
    }

    public void buttonEquals_OnClick(View view) {
        int result = calculator.calculate();
        this.outputText.setText(String.valueOf(result));
    }

    public void buttonNine_OnClick(View view) {
        calculator.add_command(Digit.NINE);
        this.outputText.setText(calculator.toString());
    }

    public void buttonEight_OnClick(View view) {
        calculator.add_command(Digit.EIGHT);
        this.outputText.setText(calculator.toString());
    }

    public void buttonSeven_OnClick(View view) {
        calculator.add_command(Digit.SEVEN);
        this.outputText.setText(calculator.toString());
    }

    public void buttonSix_OnClick(View view) {
        calculator.add_command(Digit.SIX);
        this.outputText.setText(calculator.toString());
    }

    public void buttonFive_OnClick(View view) {
        calculator.add_command(Digit.FIVE);
        this.outputText.setText(calculator.toString());
    }

    public void buttonFour_OnClick(View view) {
        calculator.add_command(Digit.FOUR);
        this.outputText.setText(calculator.toString());
    }

    public void buttonThree_OnClick(View view) {
        calculator.add_command(Digit.THREE);
        this.outputText.setText(calculator.toString());
    }

    public void buttonTwo_OnClick(View view) {
        calculator.add_command(Digit.TWO);
        this.outputText.setText(calculator.toString());
    }

    public void buttonOne_OnClick(View view) {
        calculator.add_command(Digit.ONE);
        this.outputText.setText(calculator.toString());
    }

    public void buttonZero_OnClick(View view) {
        calculator.add_command(Digit.ZERO);
        this.outputText.setText(calculator.toString());
    }
}
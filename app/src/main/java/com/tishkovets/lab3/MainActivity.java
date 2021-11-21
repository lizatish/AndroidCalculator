package com.tishkovets.lab3;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tishkovets.lab3.commands.Operator;
import com.tishkovets.lab3.commands.SimpleOperand;

public class MainActivity extends AppCompatActivity {

    private final IntegerCalculator calculator;
    private TextView outputText;
    private TextView errorText;

    public MainActivity() {
        this.calculator = new IntegerCalculator();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.outputText = findViewById(R.id.resultText);
        this.errorText = findViewById(R.id.errorText);
    }

    public void buttonClear_OnClick(View view) {
        calculator.clearAll();
        this.outputText.setText(calculator.toString());
    }

    public void buttonDelete_OnClick(View view) {
        calculator.removeLastCommand();
        this.outputText.setText(calculator.toString());
    }

    public void buttonSum_OnClick(View view) {
        calculator.addCommand(Operator.ADDITION);
        this.outputText.setText(calculator.toString());
    }

    public void buttonChangeSign_OnClick(View view) {
        calculator.changeSign();
        this.outputText.setText(calculator.toString());
    }

    public void buttonDivide_OnClick(View view) {
        calculator.addCommand(Operator.DIVISION);
        this.outputText.setText(calculator.toString());
    }

    public void buttonMultiply_OnClick(View view) {
        calculator.addCommand(Operator.MULTIPLY);
        this.outputText.setText(calculator.toString());
    }

    public void buttonSubstract_OnClick(View view) {
        calculator.addCommand(Operator.SUBTRACT);
        this.outputText.setText(calculator.toString());
    }

    public void buttonEquals_OnClick(View view) {
        try {
            calculator.calculate();
            this.errorText.setHint("");
        } catch (IllegalArgumentException error) {
            this.errorText.setHint(error.getMessage());
        }
        this.outputText.setText(calculator.toString());
    }

    public void buttonNine_OnClick(View view) {
        calculator.addCommand(SimpleOperand.NINE);
        this.outputText.setText(calculator.toString());
    }

    public void buttonEight_OnClick(View view) {
        calculator.addCommand(SimpleOperand.EIGHT);
        this.outputText.setText(calculator.toString());
    }

    public void buttonSeven_OnClick(View view) {
        calculator.addCommand(SimpleOperand.SEVEN);
        this.outputText.setText(calculator.toString());
    }

    public void buttonSix_OnClick(View view) {
        calculator.addCommand(SimpleOperand.SIX);
        this.outputText.setText(calculator.toString());
    }

    public void buttonFive_OnClick(View view) {
        calculator.addCommand(SimpleOperand.FIVE);
        this.outputText.setText(calculator.toString());
    }

    public void buttonFour_OnClick(View view) {
        calculator.addCommand(SimpleOperand.FOUR);
        this.outputText.setText(calculator.toString());
    }

    public void buttonThree_OnClick(View view) {
        calculator.addCommand(SimpleOperand.THREE);
        this.outputText.setText(calculator.toString());
    }

    public void buttonTwo_OnClick(View view) {
        calculator.addCommand(SimpleOperand.TWO);
        this.outputText.setText(calculator.toString());
    }

    public void buttonOne_OnClick(View view) {
        calculator.addCommand(SimpleOperand.ONE);
        this.outputText.setText(calculator.toString());
    }

    public void buttonZero_OnClick(View view) {
        calculator.addCommand(SimpleOperand.ZERO);
        this.outputText.setText(calculator.toString());
    }
}
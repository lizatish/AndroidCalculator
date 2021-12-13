package com.tishkovets.lab3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tishkovets.lab3.commands.CommandType;
import com.tishkovets.lab3.commands.HandleOperator;
import com.tishkovets.lab3.commands.Operator;
import com.tishkovets.lab3.commands.SimpleOperand;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private final IntegerCalculator calculator;
    private TextView outputText;
    private TextView errorText;

    private final Map<String, CommandType> commandTypeMap;

    public MainActivity() {
        this.calculator = new IntegerCalculator();
        this.commandTypeMap = new HashMap<>();

        for (HandleOperator value : HandleOperator.values()) {
            commandTypeMap.put(value.toString(), value);
        }

        for (SimpleOperand value : SimpleOperand.values()) {
            commandTypeMap.put(value.toString(), value);
        }

        for (Operator value : Operator.values()) {
            commandTypeMap.put(value.toString(), value);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.outputText = findViewById(R.id.resultText);
        this.errorText = findViewById(R.id.errorText);
    }

    public void button_OnClick(View view) {
        try {
            String text = ((Button)view).getText().toString();
            CommandType commandType = this.commandTypeMap.get(text);
            calculator.addCommand(commandType);
            this.errorText.setHint("");
        } catch (IllegalArgumentException error) {
            this.errorText.setHint(error.getMessage());
        }
        this.outputText.setText(calculator.toString());
    }
}
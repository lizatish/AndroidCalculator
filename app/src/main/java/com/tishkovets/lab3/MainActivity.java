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

        commandTypeMap.put("buttonClear", HandleOperator.CLEAR);
        commandTypeMap.put("buttonDelete", HandleOperator.DELETE);
        commandTypeMap.put("buttonChangeSign", HandleOperator.CHANGE_SIGN);
        commandTypeMap.put("buttonEquals", HandleOperator.EQUALS);

        commandTypeMap.put("buttonNine", SimpleOperand.NINE);
        commandTypeMap.put("buttonEight", SimpleOperand.EIGHT);
        commandTypeMap.put("buttonSeven", SimpleOperand.SEVEN);
        commandTypeMap.put("buttonSix", SimpleOperand.SIX);
        commandTypeMap.put("buttonFive", SimpleOperand.FIVE);
        commandTypeMap.put("buttonFour", SimpleOperand.FOUR);
        commandTypeMap.put("buttonThree", SimpleOperand.THREE);
        commandTypeMap.put("buttonTwo", SimpleOperand.TWO);
        commandTypeMap.put("buttonOne", SimpleOperand.ONE);
        commandTypeMap.put("buttonZero", SimpleOperand.ZERO);

        commandTypeMap.put("buttonSum", Operator.ADDITION);
        commandTypeMap.put("buttonSubtract", Operator.SUBTRACT);
        commandTypeMap.put("buttonMultiply", Operator.MULTIPLY);
        commandTypeMap.put("buttonDivide", Operator.DIVISION);
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
            String id = view.getResources().getResourceEntryName(view.getId());
            String text = ((Button)view).getText().toString();

            CommandType commandType = this.commandTypeMap.get(id);
            calculator.addCommand(commandType);
            this.errorText.setHint("");
        } catch (IllegalArgumentException error) {
            this.errorText.setHint(error.getMessage());
        }
        this.outputText.setText(calculator.toString());
    }
}
package com.example.pikahcucalculator;

import androidx.appcompat.app.AppCompatActivity;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Declaring TextViews And Buttons
    TextView resultTv, solutionTv;
    MaterialButton btC, btBracketOpen, btBracketClose;
    MaterialButton btDivide, btMultiply, btSubstraction, btAddition, btEqual;
    MaterialButton bt0, bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9;
    MaterialButton btAc, btDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Code to hide Action Bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        // Handling TextViews
        solutionTv = findViewById(R.id.solution_tv);
        resultTv = findViewById(R.id.result_tv);


        // Finding View Id & Assigning ClickListener
        assignId(btC, R.id.clear);
        assignId(btBracketOpen, R.id.open_bracket);
        assignId(btBracketClose, R.id.close_backet);
        assignId(btDivide, R.id.divide);
        assignId(btMultiply, R.id.bt_multiply);
        assignId(btSubstraction, R.id.subtract);
        assignId(btAddition, R.id.addtion);
        assignId(btEqual, R.id.equal);
        assignId(bt0, R.id.button_0);
        assignId(bt1, R.id.button_1);
        assignId(bt2, R.id.button_2);
        assignId(bt3, R.id.button_3);
        assignId(bt4, R.id.button_4);
        assignId(bt5, R.id.button_5);
        assignId(bt6, R.id.button_6);
        assignId(bt7, R.id.button_7);
        assignId(bt8, R.id.button_8);
        assignId(bt9, R.id.button_9);
        assignId(btAc, R.id.AC);
        assignId(btDot, R.id.dot);

    }

    // Method to Find View by id & to Assign ClickListener
    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);

    }

    // OnClick Metods
    @Override
    public void onClick(View view) {
        // Making a button ClickAble
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if (buttonText.equals("AC")) {
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if (buttonText.equals("=")) {

            solutionTv.setText("");
            return;
        }
        if (buttonText.equals("C")) {
            if (solutionTv.length()==1 || solutionTv.length()==0)
            {
                solutionTv.setText("");
                resultTv.setText("0");
                return;
            }
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);

        } else {

            dataToCalculate = dataToCalculate + buttonText;
        }
        solutionTv.setText(dataToCalculate);

        // Calling method getResult
        String finalResult=getResult(dataToCalculate);
        if (!finalResult.equals("err"))
        {
            resultTv.setText(finalResult);
        }
    }

    // Method to get the final result
    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult=context.evaluateString(scriptable,data,"javascript",1,null).toString();
            if (finalResult.endsWith(".0"))
            {
                finalResult=finalResult.replace(".0","");
            }
            return finalResult;
        }
        catch (Exception e)
        {
            return "err";
        }
    }

}
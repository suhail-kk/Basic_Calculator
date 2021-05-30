package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display=findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);


        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getString(R.string.display).equals(display.getText().toString())){
                    display.setText("");
                }
            }
        });
    }

    private void UpdateText(String strToAdd) {
        String oldStr = display.getText().toString();
        int cursorPos=display.getSelectionStart();
        String leftStr=oldStr.substring(0,cursorPos);
        String rightStr=oldStr.substring(cursorPos);
        if(getString(R.string.display).equals(display.getText().toString())){
            display.setText(strToAdd);
            display.setSelection(cursorPos + 1);
        }
        else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            display.setSelection(cursorPos + 1);
        }

    }

    public void clearBtn(View view){
       display.setText("");

    }
    public void paraBtn(View view){
        int cursorPos=display.getSelectionStart();
        int openPara = 0;
        int closedPara = 0;
        int textLen = display.getText().length();

        for(int i=0 ; i < cursorPos ; i++){
            if(display.getText().toString().substring(i, i+1).equals("(")){
                openPara += 1;
            }
            if(display.getText().toString().substring(i, i+1).equals(")")){
                closedPara += 1;

            }
        }
        if(openPara == closedPara || display.getText().toString().substring(textLen -1,textLen).equals("(")){
            UpdateText("(");
            display.setSelection(cursorPos + 1);
        }
        else if(closedPara < openPara && !display.getText().toString().substring(textLen -1,textLen).equals("(")){
            UpdateText(")");
        }display.setSelection(cursorPos + 1);


    }
    public void riseBtn(View view){
        UpdateText("^");

    }
    public void divideBtn(View view){
        UpdateText("÷");

    }
    public void sevenBtn(View view){
        UpdateText("7");

    }
    public void eightBtn(View view){
        UpdateText("8");

    }
    public void nineBtn(View view){
        UpdateText("9");

    }
    public void multiplyBtn(View view){
        UpdateText("×");

    }
    public void fourBtn(View view){
        UpdateText("4");

    }
    public void fiveBtn(View view){
        UpdateText("5");

    }
    public void sixBtn(View view){
        UpdateText("6");

    }
    public void minusBtn(View view){
        UpdateText("-");

    }
    public void oneBtn(View view){
        UpdateText("1");

    }
    public void twoBtn(View view){
        UpdateText("2");

    }
    public void threeBtn(View view){
        UpdateText("3");

    }
    public void plusBtn(View view){
        UpdateText("+");

    }
    public void zeroBtn(View view){
        UpdateText("0");

    }
    public void pointBtn(View view){
        UpdateText(".");

    }
    public void equalsBtn(View view){
        String userExp=display.getText().toString();
        userExp = userExp.replaceAll("÷","/");
        userExp = userExp.replaceAll("×","*");

        Expression exp = new Expression(userExp);
        String result= String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());

    }
    public void backspaceBtn(View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if(cursorPos != 0 && textLen != 0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos - 1,cursorPos,"");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }

    }
}
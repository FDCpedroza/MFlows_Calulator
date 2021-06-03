package com.example.mandongcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.script.*;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {


    TextView inputText;
    TextView resultsText;

    String inputs = "";
    String formula = "";
    String tempFormula = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextViews();
    }

    private void initTextViews()
    {
        inputText = (TextView)findViewById(R.id.workingTextView);
        resultsText = (TextView)findViewById(R.id.resultTextView);
    }

    private void setInput(String newInput)
    {
        inputs = inputs + newInput;
        inputText.setText(inputs);
    }

    public void clearOnClick(View view)
    {
         inputText.setText("");
         resultsText.setText("");
         inputs = "";
         leftBracket = true;
    }

    public void equalsOnClick(View view)
    {
        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        checkForPowerOf(); 
        try {
            result = (double) engine.eval(String.valueOf(formula));

        } catch (ScriptException e)
        {
            Toast.makeText(this, "Invalid Input: "+e, Toast.LENGTH_SHORT).show();
        }
        if(result != null)
        {
            resultsText.setText(String.valueOf(result.doubleValue()));
        }

    }

    private void checkForPowerOf() {
        ArrayList<Integer> indexOfPowers= new ArrayList<>();

        for(int i = 0; i < inputs.length(); i++)
        {
            if(inputs.charAt(i) == '^')
            {
                indexOfPowers.add(i);
            }
        }
        
        formula = inputs;
        tempFormula = inputs;
        for(Integer index: indexOfPowers)
        {
            changeFormula(index);
        }
        formula = tempFormula;
    }

    private void changeFormula(Integer index)
    {
        String numberLeft = "";
        String numberRight = "";

        for(int i = index + 1; i < inputs.length(); i++ )
        {
            if(isNumeric(inputs.charAt(i)))
            {
                numberRight = numberRight + inputs.charAt(i);
            }
            else
            {
                break;
            }
        }
        for(int i = index - 1; i >= inputs.length(); i-- )
        {
            if(isNumeric(inputs.charAt(i)))
            {
                numberLeft = numberLeft + inputs.charAt(i);
            }
            else
            {
                break;
            }
        }

        String original = numberLeft + "^" + numberRight;
        String changed = "Math.pow("+ numberLeft+","+numberRight+")";
        tempFormula = tempFormula.replace(original,changed);

    }
    private boolean isNumeric(char c)
    {
        if((c <= '9') && (c >= '0') || (c == '.'))
        {
            return true;
        }
        return false;
    }

    boolean leftBracket = true;

    public void bracketsOnClick(View view)
    {
        if(leftBracket)
        {
            setInput("(");
            leftBracket = false;
        }else
        {
            setInput(")");
            leftBracket = false;
        }
    }

    /*NUMBERS*/
    public void oneOnClick(View view)
    {
        setInput("1");
    }

    public void twoOnClick(View view)
    {
        setInput("2");
    }

    public void threeOnClick(View view)
    {
        setInput("3");
    }

    public void fourOnClick(View view)
    {
        setInput("4");
    }

    public void fiveOnClick(View view)
    {
        setInput("5");
    }

    public void sixOnClick(View view)
    {
        setInput("6");
    }

    public void sevenOnClick(View view)
    {
        setInput("7");
    }

    public void eightOnClick(View view)
    {
        setInput("8");
    }

    public void nineOnClick(View view)
    {
        setInput("9");
    }

    public void zeroOnClick(View view)
    {
        setInput("0");
    }

    public void periodOnClick(View view)
    {
        setInput(".");
    }

    /*OPERATIONS*/
    public void divideOnClick(View view)
    {
        setInput("/");
    }

    public void multiplyOnClick(View view)
    {
        setInput("*");
    }

    public void subtractOnClick(View view)
    {
        setInput("-");
    }

    public void addOnClick(View view)
    {
        setInput("+");
    }

    public void powerOfOnclick(View view)
    {
        setInput("^");
    }

}
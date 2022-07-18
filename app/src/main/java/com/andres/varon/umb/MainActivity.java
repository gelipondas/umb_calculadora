package com.andres.varon.umb;


import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener
{
    private int inicioParentesis = 0;

    private boolean puntoUso = false;

    private boolean equalClicked = false;
    private String lastExpression = "";

    private final static int EXCEPTION = -1;
    private final static int IS_NUMBER = 0;
    private final static int IS_OPERAND = 1;
    private final static int IS_OPEN_PARENTHESIS = 2;
    private final static int IS_CLOSE_PARENTHESIS = 3;
    private final static int IS_DOT = 4;

    Button Number0;
    Button Number1;
    Button Number2;
    Button Number3;
    Button Number4;
    Button Number5;
    Button Number6;
    Button Number7;
    Button Number8;
    Button Number9;

    Button limpiar;
    Button parectesis;
    Button buttonPercent;
    Button buttonDivision;
    Button buttonMultiplicacion;
    Button buttonResta;
    Button buttonSuma;
    Button buttonIgual;
    Button buttonPunto;

    TextView textoEntradas;

    ScriptEngine scriptEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scriptEngine = new ScriptEngineManager().getEngineByName("rhino");

        Number0 = findViewById(R.id.button_zero);
        Number1 = findViewById(R.id.button_one);
        Number2 = findViewById(R.id.button_two);
        Number3 = findViewById(R.id.button_three);
        Number4 = findViewById(R.id.button_four);
        Number5 = findViewById(R.id.button_five);
        Number6 = findViewById(R.id.button_six);
        Number7 = findViewById(R.id.button_seven);
        Number8 = findViewById(R.id.button_eight);
        Number9 = findViewById(R.id.button_nine);

        limpiar                 = findViewById(R.id.button_limpiar);
        parectesis              = findViewById(R.id.button_parentesis);
        buttonPercent           = findViewById(R.id.button_percentaje);
        buttonDivision          = findViewById(R.id.button_division);
        buttonMultiplicacion    = findViewById(R.id.button_multiplicacion);
        buttonResta             = findViewById(R.id.button_resta);
        buttonSuma              = findViewById(R.id.button_suma);
        buttonIgual             = findViewById(R.id.button_igual);
        buttonPunto             = findViewById(R.id.button_punto);
        textoEntradas           = findViewById(R.id.entradas_texto);


        //ONCLICK ACTION BUTTOM INI
        Number0.setOnClickListener(this);
        Number1.setOnClickListener(this);
        Number2.setOnClickListener(this);
        Number3.setOnClickListener(this);
        Number4.setOnClickListener(this);
        Number5.setOnClickListener(this);
        Number6.setOnClickListener(this);
        Number7.setOnClickListener(this);
        Number8.setOnClickListener(this);
        Number9.setOnClickListener(this);

        limpiar.setOnClickListener(this);
        parectesis.setOnClickListener(this);
        buttonPercent.setOnClickListener(this);
        buttonDivision.setOnClickListener(this);
        buttonMultiplicacion.setOnClickListener(this);
        buttonResta.setOnClickListener(this);
        buttonSuma.setOnClickListener(this);
        buttonIgual.setOnClickListener(this);
        buttonPunto.setOnClickListener(this);


        //ONTOUCHLISTENER BACKGROUND CHANGUE BUTTON
        Number0.setOnTouchListener(this);
        Number1.setOnTouchListener(this);
        Number2.setOnTouchListener(this);
        Number3.setOnTouchListener(this);
        Number4.setOnTouchListener(this);
        Number5.setOnTouchListener(this);
        Number6.setOnTouchListener(this);
        Number7.setOnTouchListener(this);
        Number8.setOnTouchListener(this);
        Number9.setOnTouchListener(this);

        limpiar.setOnTouchListener(this);
        parectesis.setOnTouchListener(this);
        buttonPercent.setOnTouchListener(this);
        buttonDivision.setOnTouchListener(this);
        buttonMultiplicacion.setOnTouchListener(this);
        buttonResta.setOnTouchListener(this);
        buttonSuma.setOnTouchListener(this);
        buttonPunto.setOnTouchListener(this);    }


    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.button_zero:
                if (agregarNumero("0")) equalClicked = false;
                break;
            case R.id.button_one:
                if (agregarNumero("1")) equalClicked = false;
                break;
            case R.id.button_two:
                if (agregarNumero("2")) equalClicked = false;
                break;
            case R.id.button_three:
                if (agregarNumero("3")) equalClicked = false;
                break;
            case R.id.button_four:
                if (agregarNumero("4")) equalClicked = false;
                break;
            case R.id.button_five:
                if (agregarNumero("5")) equalClicked = false;
                break;
            case R.id.button_six:
                if (agregarNumero("6")) equalClicked = false;
                break;
            case R.id.button_seven:
                if (agregarNumero("7")) equalClicked = false;
                break;
            case R.id.button_eight:
                if (agregarNumero("8")) equalClicked = false;
                break;
            case R.id.button_nine:
                if (agregarNumero("9")) equalClicked = false;
                break;
            case R.id.button_suma:
                if (addOperand("+")) equalClicked = false;
                break;
            case R.id.button_resta:
                if (addOperand("-")) equalClicked = false;
                break;
            case R.id.button_multiplicacion:
                if (addOperand("x")) equalClicked = false;
                break;
            case R.id.button_division:
                if (addOperand("\u00F7")) equalClicked = false;
                break;
            case R.id.button_percentaje:
                if (addOperand("%")) equalClicked = false;
                break;
            case R.id.button_punto:
                if (addDot()) equalClicked = false;
                break;
            case R.id.button_parentesis:
                if (addParenthesis()) equalClicked = false;
                break;
            case R.id.button_limpiar:
                textoEntradas.setText("0 ");
                inicioParentesis = 0;
                puntoUso = false;
                equalClicked = false;
                break;
            case R.id.button_igual:
                textoEntradas.getText().toString();
                if (!textoEntradas.getText().toString().equals(""))
                    calcular(textoEntradas.getText().toString());
                break;
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent)
    {
        switch (motionEvent.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            {
                view.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                view.invalidate();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                view.getBackground().clearColorFilter();
                view.invalidate();
                break;
            }
        }
        return false;
    }

    private boolean addDot()
    {
        boolean done = false;

        if (textoEntradas.getText().length() == 0)
        {
            textoEntradas.setText("0.");
            puntoUso = true;
            done = true;
        } else if (puntoUso == true)
        {
        } else if (defineLastCharacter(textoEntradas.getText().charAt(textoEntradas.getText().length() - 1) + "") == IS_OPERAND)
        {
            textoEntradas.setText(textoEntradas.getText() + "0.");
            done = true;
            puntoUso = true;
        } else if (defineLastCharacter(textoEntradas.getText().charAt(textoEntradas.getText().length() - 1) + "") == IS_NUMBER)
        {
            textoEntradas.setText(textoEntradas.getText() + ".");
            done = true;
            puntoUso = true;
        }
        return done;
    }

    private boolean addParenthesis()
    {
        boolean done = false;
        int operationLength = textoEntradas.getText().length();

        if (operationLength == 0)
        {
            textoEntradas.setText(textoEntradas.getText() + "(");
            puntoUso = false;
            inicioParentesis++;
            done = true;
        } else if (inicioParentesis > 0 && operationLength > 0)
        {
            String lastInput = textoEntradas.getText().charAt(operationLength - 1) + "";
            switch (defineLastCharacter(lastInput))
            {
                case IS_NUMBER:
                case IS_CLOSE_PARENTHESIS:
                    textoEntradas.setText(textoEntradas.getText() + ")");
                    done = true;
                    inicioParentesis--;
                    puntoUso = false;
                    break;
                case IS_OPERAND:
                case IS_OPEN_PARENTHESIS:
                    textoEntradas.setText(textoEntradas.getText() + "(");
                    done = true;
                    inicioParentesis++;
                    puntoUso = false;
                    break;
            }
        } else if (inicioParentesis == 0 && operationLength > 0)
        {
            String lastInput = textoEntradas.getText().charAt(operationLength - 1) + "";
            if (defineLastCharacter(lastInput) == IS_OPERAND)
            {
                textoEntradas.setText(textoEntradas.getText() + "(");
                done = true;
                puntoUso = false;
                inicioParentesis++;
            } else
            {
                textoEntradas.setText(textoEntradas.getText() + "x(");
                done = true;
                puntoUso = false;
                inicioParentesis++;
            }
        }
        return done;
    }

    private boolean addOperand(String operand)
    {
        boolean done = false;
        int operationLength = textoEntradas.getText().length();
        if (operationLength > 0)
        {
            String lastInput = textoEntradas.getText().charAt(operationLength - 1) + "";

            if ((lastInput.equals("+") || lastInput.equals("-") || lastInput.equals("*") || lastInput.equals("\u00F7") || lastInput.equals("%")))
            {
                Toast.makeText(getApplicationContext(), "Wrong format", Toast.LENGTH_LONG).show();
            } else if (operand.equals("%") && defineLastCharacter(lastInput) == IS_NUMBER)
            {
                textoEntradas.setText(textoEntradas.getText() + operand);
                puntoUso = false;
                equalClicked = false;
                lastExpression = "";
                done = true;
            } else if (!operand.equals("%"))
            {
                textoEntradas.setText(textoEntradas.getText() + operand);
                puntoUso = false;
                equalClicked = false;
                lastExpression = "";
                done = true;
            }
        } else
        {
            Toast.makeText(getApplicationContext(), "Error en la entrada de datos", Toast.LENGTH_LONG).show();
        }
        return done;
    }

    private boolean agregarNumero(String number)
    {
        boolean done = false;
        int operationLength = textoEntradas.getText().length();
        if (operationLength > 0)
        {
            String lastCharacter = textoEntradas.getText().charAt(operationLength - 1) + "";
            int lastCharacterState = defineLastCharacter(lastCharacter);

            if (operationLength == 1 && lastCharacterState == IS_NUMBER && lastCharacter.equals("0"))
            {
                textoEntradas.setText(number);
                done = true;
            } else if (lastCharacterState == IS_OPEN_PARENTHESIS)
            {
                textoEntradas.setText(textoEntradas.getText() + number);
                done = true;
            } else if (lastCharacterState == IS_CLOSE_PARENTHESIS || lastCharacter.equals("%"))
            {
                textoEntradas.setText(textoEntradas.getText() + "x" + number);
                done = true;
            } else if (lastCharacterState == IS_NUMBER || lastCharacterState == IS_OPERAND || lastCharacterState == IS_DOT)
            {
                textoEntradas.setText(textoEntradas.getText() + number);
                done = true;
            }
        } else
        {
            textoEntradas.setText(textoEntradas.getText() + number);
            done = true;
        }
        return done;
    }


    private void calcular(String input)
    {
        String result = "";
        try
        {
            String temp = input;
            if (equalClicked)
            {
                temp = input + lastExpression;
            } else
            {
                saveLastExpression(input);
            }
            result = scriptEngine.eval(temp.replaceAll("%", "/100").replaceAll("x", "*").replaceAll("[^\\x00-\\x7F]", "/")).toString();
            BigDecimal decimal = new BigDecimal(result);
            result = decimal.setScale(8, BigDecimal.ROUND_HALF_UP).toPlainString();
            equalClicked = true;

        } catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Formato erroneo", Toast.LENGTH_SHORT).show();
            return;
        }

        if (result.equals("Infinity"))
        {
            Toast.makeText(getApplicationContext(), "No se puede dividir el numero 0", Toast.LENGTH_SHORT).show();
            textoEntradas.setText(input);

        } else if (result.contains("."))
        {
            result = result.replaceAll("\\.?0*$", "");
            textoEntradas.setText(result);
        }
    }

    private void saveLastExpression(String input)
    {
        String lastOfExpression = input.charAt(input.length() - 1) + "";
        if (input.length() > 1)
        {
            if (lastOfExpression.equals(")"))
            {
                lastExpression = ")";
                int numberOfCloseParenthesis = 1;

                for (int i = input.length() - 2; i >= 0; i--)
                {
                    if (numberOfCloseParenthesis > 0)
                    {
                        String last = input.charAt(i) + "";
                        if (last.equals(")"))
                        {
                            numberOfCloseParenthesis++;
                        } else if (last.equals("("))
                        {
                            numberOfCloseParenthesis--;
                        }
                        lastExpression = last + lastExpression;
                    } else if (defineLastCharacter(input.charAt(i) + "") == IS_OPERAND)
                    {
                        lastExpression = input.charAt(i) + lastExpression;
                        break;
                    } else
                    {
                        lastExpression = "";
                    }
                }
            } else if (defineLastCharacter(lastOfExpression + "") == IS_NUMBER)
            {
                lastExpression = lastOfExpression;
                for (int i = input.length() - 2; i >= 0; i--)
                {
                    String last = input.charAt(i) + "";
                    if (defineLastCharacter(last) == IS_NUMBER || defineLastCharacter(last) == IS_DOT)
                    {
                        lastExpression = last + lastExpression;
                    } else if (defineLastCharacter(last) == IS_OPERAND)
                    {
                        lastExpression = last + lastExpression;
                        break;
                    }
                    if (i == 0)
                    {
                        lastExpression = "";
                    }
                }
            }
        }
    }

    private int defineLastCharacter(String lastCharacter)
    {
        try
        {
            Integer.parseInt(lastCharacter);
            return IS_NUMBER;
        } catch (NumberFormatException e)
        {
        }

        if ((lastCharacter.equals("+") || lastCharacter.equals("-") || lastCharacter.equals("x") || lastCharacter.equals("\u00F7") || lastCharacter.equals("%")))
            return IS_OPERAND;

        if (lastCharacter.equals("("))
            return IS_OPEN_PARENTHESIS;

        if (lastCharacter.equals(")"))
            return IS_CLOSE_PARENTHESIS;

        if (lastCharacter.equals("."))
            return IS_DOT;

        return -1;
    }
}

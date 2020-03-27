package com.example.firstapplication;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private boolean equalsClicked = false;

    // Eval method to convert string into mathematical expression
    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('x')) x *= parseFactor(); // multiplication
                    else if (eat('÷')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Result TextView
        final TextView resultTextView = findViewById(R.id.resultTextView);

        // Numbers Buttons
        Button zeroButton = (Button) findViewById(R.id.zeroButton);
        Button oneButton = (Button) findViewById(R.id.oneButton);
        Button twoButton = (Button) findViewById(R.id.twoButton);
        Button threeButton = (Button) findViewById(R.id.threeButton);
        Button fourButton = (Button) findViewById(R.id.fourButton);
        Button fiveButton = (Button) findViewById(R.id.fiveButton);
        Button sixButton = (Button) findViewById(R.id.sixButton);
        Button sevenButton = (Button) findViewById(R.id.sevenButton);
        Button eightButton = (Button) findViewById(R.id.eightButton);
        Button nineButton = (Button) findViewById(R.id.nineButton);

        // Operations Buttons
        Button addButton = (Button) findViewById(R.id.addButton);
        Button substractButton = (Button) findViewById(R.id.substractButton);
        Button multiplyButton = (Button) findViewById(R.id.multiplyButton);
        Button divideButton = (Button) findViewById(R.id.divideButton);
        Button equalsButton = (Button) findViewById(R.id.equalsButton);
        Button clearButton = (Button) findViewById(R.id.clearButton);

        // OnClickListener for number buttons
        zeroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(10);
                int buttonValue = 0;

                if (equalsClicked) {
                    equalsClicked = false;
                    resultTextView.setText("");
                    String currentText = (String) resultTextView.getText();

                    resultTextView.setText(currentText + buttonValue);
                }
                else {
                    String currentText = (String) resultTextView.getText();
                    resultTextView.setText(currentText + buttonValue);
                }
            }
        });

        oneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(10);
                int buttonValue = 1;

                if (equalsClicked) {
                    equalsClicked = false;
                    resultTextView.setText("");
                    String currentText = (String) resultTextView.getText();

                    resultTextView.setText(currentText + buttonValue);
                }
                else {
                    String currentText = (String) resultTextView.getText();
                    resultTextView.setText(currentText + buttonValue);
                }
            }
        });

        twoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(10);
                int buttonValue = 2;

                if (equalsClicked) {
                    equalsClicked = false;
                    resultTextView.setText("");
                    String currentText = (String) resultTextView.getText();

                    resultTextView.setText(currentText + buttonValue);
                }
                else {
                    String currentText = (String) resultTextView.getText();
                    resultTextView.setText(currentText + buttonValue);
                }
            }
        });

        threeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(10);
                int buttonValue = 3;

                if (equalsClicked) {
                    equalsClicked = false;
                    resultTextView.setText("");
                    String currentText = (String) resultTextView.getText();

                    resultTextView.setText(currentText + buttonValue);
                }
                else {
                    String currentText = (String) resultTextView.getText();
                    resultTextView.setText(currentText + buttonValue);
                }
            }
        });

        fourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(10);
                int buttonValue = 4;

                if (equalsClicked) {
                    equalsClicked = false;
                    resultTextView.setText("");
                    String currentText = (String) resultTextView.getText();

                    resultTextView.setText(currentText + buttonValue);
                }
                else {
                    String currentText = (String) resultTextView.getText();
                    resultTextView.setText(currentText + buttonValue);
                }
            }
        });

        fiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(10);
                int buttonValue = 5;

                if (equalsClicked) {
                    equalsClicked = false;
                    resultTextView.setText("");
                    String currentText = (String) resultTextView.getText();

                    resultTextView.setText(currentText + buttonValue);
                }
                else {
                    String currentText = (String) resultTextView.getText();
                    resultTextView.setText(currentText + buttonValue);
                }
            }
        });

        sixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(10);
                int buttonValue = 6;

                if (equalsClicked) {
                    equalsClicked = false;
                    resultTextView.setText("");
                    String currentText = (String) resultTextView.getText();

                    resultTextView.setText(currentText + buttonValue);
                }
                else {
                    String currentText = (String) resultTextView.getText();
                    resultTextView.setText(currentText + buttonValue);
                }
            }
        });

        sevenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(10);
                int buttonValue = 7;

                if (equalsClicked) {
                    equalsClicked = false;
                    resultTextView.setText("");
                    String currentText = (String) resultTextView.getText();

                    resultTextView.setText(currentText + buttonValue);
                }
                else {
                    String currentText = (String) resultTextView.getText();
                    resultTextView.setText(currentText + buttonValue);
                }
            }
        });

        eightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(10);
                int buttonValue = 8;

                if (equalsClicked) {
                    equalsClicked = false;
                    resultTextView.setText("");
                    String currentText = (String) resultTextView.getText();

                    resultTextView.setText(currentText + buttonValue);
                }
                else {
                    String currentText = (String) resultTextView.getText();
                    resultTextView.setText(currentText + buttonValue);
                }
            }
        });

        nineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(10);
                int buttonValue = 9;

                if (equalsClicked) {
                    equalsClicked = false;
                    resultTextView.setText("");
                    String currentText = (String) resultTextView.getText();

                    resultTextView.setText(currentText + buttonValue);
                }
                else {
                    String currentText = (String) resultTextView.getText();
                    resultTextView.setText(currentText + buttonValue);
                }
            }
        });

        // OnClickListener for operations buttons
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(10);
                String currentText = (String) resultTextView.getText();
                char buttonValue = '+';

                equalsClicked = false;
                resultTextView.setText(currentText + buttonValue);
            }
        });

        substractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(10);
                String currentText = (String) resultTextView.getText();
                char buttonValue = '-';

                equalsClicked = false;
                resultTextView.setText(currentText + buttonValue);
            }
        });

        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(10);
                String currentText = (String) resultTextView.getText();
                char buttonValue = 'x';

                equalsClicked = false;
                resultTextView.setText(currentText + buttonValue);
            }
        });

        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(10);
                String currentText = (String) resultTextView.getText();
                char buttonValue = '÷';

                equalsClicked = false;
                resultTextView.setText(currentText + buttonValue);
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(25);
                resultTextView.setText("");
            }
        });

        equalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(25);
                String currentText = (String) resultTextView.getText();

                if (currentText == "") {
                    resultTextView.setText("0.0");
                    equalsClicked = true;
                } else {
                    double result = eval(currentText);

                    resultTextView.setText("" + result);
                    equalsClicked = true;
                    System.out.println(equalsClicked);
                }
            }
        });
    }
}

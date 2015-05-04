package com.example.marvin.calculator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Scientific extends ActionBarActivity {
    private Button sin, cos, tan, percent, ln, log, pi, exponent, degrees, factorial, regular;
    private Button square, exponent2, exponent3, equals, opening, closing, clear;
    private EditText display;
    private int num, numtemp;
    String str = "";
    boolean radianORdegree = false;
    char operation = 'x';


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scientific);

        sin = (Button) findViewById(R.id.buttonSin);
        cos = (Button) findViewById(R.id.buttonCos);
        tan = (Button) findViewById(R.id.buttonTan);
        percent = (Button) findViewById(R.id.buttonPercent);
        ln = (Button) findViewById(R.id.buttonLN);
        log = (Button) findViewById(R.id.buttonLog);
        pi = (Button) findViewById(R.id.buttonPI);
        exponent = (Button) findViewById(R.id.buttonExp);
        degrees = (Button) findViewById(R.id.buttonDeg);
        factorial = (Button) findViewById(R.id.buttonFactorial);
        square = (Button) findViewById(R.id.buttonRoot);
        exponent2 = (Button) findViewById(R.id.buttonPower);
        exponent3 = (Button) findViewById(R.id.buttonCube);
        opening = (Button) findViewById(R.id.buttonStart);
        closing = (Button) findViewById(R.id.buttonEnds);
        equals = (Button) findViewById(R.id.buttonEquals);
        regular = (Button) findViewById(R.id.buttonRegular);
        display = (EditText) findViewById(R.id.results);
        clear = (Button) findViewById(R.id.buttonClear);

        Intent intent = getIntent();
        String p1 = intent.getStringExtra("score1");
        display.setText(p1);

        regular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String stateToSave1 = display.getText().toString();

                if (stateToSave1.isEmpty()) {

                    Intent intent = new Intent(Scientific.this, Simple.class);
                    intent.putExtra("score2", "0");
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(Scientific.this, Simple.class);
                    intent.putExtra("score2", stateToSave1);
                    startActivity(intent);
                }

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = "";
                operation = 'x';
                num = 0;
                numtemp = 0;
                display.setText("");
            }
        });


        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //the calculator is degree mode
                String digits = display.getText().toString();
                double sinNum = Double.valueOf(digits).doubleValue();
                double sinResults = (Math.sin(sinNum));
                BigDecimal number = new BigDecimal(sinResults);
                number = number.setScale(5, RoundingMode.DOWN);
                String backToDigits = String.valueOf(number);
                display.setText(backToDigits);

            }
        });

        cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String digits = display.getText().toString();
                double cosNum = Double.valueOf(digits).doubleValue();
                double cosResults = Math.cos(cosNum);
                //Reduce the decimal to 5
                BigDecimal number = new BigDecimal(cosResults);
                number = number.setScale(5, RoundingMode.DOWN);
                //Get the results back to the display
                String backToDigits = String.valueOf(number);
                display.setText(backToDigits);


            }
        });

        tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String digits = display.getText().toString();
                double tanNum = Double.valueOf(digits).doubleValue();
                double tanResults = Math.tan(tanNum);
                BigDecimal number = new BigDecimal(tanResults);
                number = number.setScale(5, RoundingMode.DOWN);
                String backToDigits = String.valueOf(number);
                display.setText(backToDigits);
            }
        });

        percent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String digits = display.getText().toString();
                double tanNum = Double.valueOf(digits).doubleValue();
                double per = tanNum/100;
                String backToDigits = String.valueOf(per);
                display.setText(backToDigits);



            }
        });
        degrees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        //Perform ln(x), display error for ln(0)
        ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String digits = display.getText().toString();
                double numbers = Double.valueOf(digits).doubleValue();

                if (numbers == 0) {
                    String backToDigits = String.valueOf("Error");
                    display.setText(backToDigits);
                } else {
                    double logNum = Math.log(numbers);
                    BigDecimal number = new BigDecimal(logNum);
                    number = number.setScale(5, RoundingMode.DOWN);
                    String backToDigits = String.valueOf(number);
                    display.setText(backToDigits);
                }


            }
        });

        //It will perform log(x) assuming base of 10
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String digits = display.getText().toString();
                double numbers = Double.valueOf(digits).doubleValue();
                double logNum = Math.log(numbers) / Math.log(10.0);
                BigDecimal number = new BigDecimal(logNum);
                number = number.setScale(5, RoundingMode.DOWN);
                String backToDigits = String.valueOf(number);
                display.setText(backToDigits);
            }
        });

        //This button will display Pi.
        pi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double applePI = Math.PI;
                BigDecimal number = new BigDecimal(applePI);
                number = number.setScale(5, RoundingMode.DOWN);
                String backToDigits = String.valueOf(number);
                display.setText(backToDigits);
            }
        });

        //This function will perform factorial on a particular number.
        factorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String digits = display.getText().toString();
                double num = Double.valueOf(digits).doubleValue();
                performFactorial(num);

            }
        });


        //Calculate the square root of a number.
        exponent2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String digits = display.getText().toString();
                double numbers = Double.valueOf(digits).doubleValue();
                double x = numbers * numbers;
                String backToDigits = String.valueOf(x);
                display.setText(backToDigits);

            }
        });

        //Calculate the cube root a number.
        exponent3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String digits = display.getText().toString();
                double numbers = Double.valueOf(digits).doubleValue();
                double x = numbers * numbers * numbers;
                String backToDigits = String.valueOf(x);
                display.setText(backToDigits);

            }
        });

        //Get the square root of a number.
        square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String digits = display.getText().toString();
                double numbers = Double.valueOf(digits).doubleValue();
                double x = Math.sqrt(numbers);
                String backToDigits = String.valueOf(x);
                display.setText(backToDigits);
            }
        });

        exponent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String digits = display.getText().toString();
                double numbers = 2.718281;
                String backToDigits = String.valueOf(numbers);
                display.setText(backToDigits);


            }
        });

        opening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String digits = display.getText().toString();
                double numbers = Double.valueOf(digits).doubleValue();
                if (numbers == 0) {
                    display.setText("Error");
                } else {
                    double oneDivision = 1 / numbers;
                    String backToDigits = String.valueOf(oneDivision);
                    display.setText(backToDigits);
                }

            }
        });

        closing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String digits = display.getText().toString();
                double numbers = Double.valueOf(digits).doubleValue();
                double oneDivision = (-1) * numbers;
                String backToDigits = String.valueOf(oneDivision);
                display.setText(backToDigits);

            }
        });

    }

    public void performFactorial(double x) {
        double theNumber = x;
        double factorial = 1;
        for (double i = theNumber; i >= 0; i--) {
            if (i < 1) {
                factorial *= 1;
            } else {
                factorial *= i;
            }
        }

        String backToDigits = String.valueOf(factorial);
        display.setText(backToDigits);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scientific, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.simple) {
            String stateToSave1 = display.getText().toString();
            Intent intent = new Intent(Scientific.this, Simple.class);
            intent.putExtra("score2", stateToSave1);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }
}

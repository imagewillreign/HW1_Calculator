package com.example.marvin.calculator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Simple extends ActionBarActivity {
    //All Numbers
    private Button number0, number1, number2, number3, number4;
    private Button number5, number6, number7, number8, number9;
    //All operations
    private Button scientific, clear, plus, minus, multiply, divide, equals, decimal;
    private EditText display;
    private double num, numtemp, mydouble;
    String displayString = "";
    char operation = 'x';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        //All Buttons
        number0 = (Button) findViewById(R.id.button0);
        number1 = (Button) findViewById(R.id.button1);
        number2 = (Button) findViewById(R.id.button2);
        number3 = (Button) findViewById(R.id.button3);
        number4 = (Button) findViewById(R.id.button4);
        number5 = (Button) findViewById(R.id.button5);
        number6 = (Button) findViewById(R.id.button6);
        number7 = (Button) findViewById(R.id.button7);
        number8 = (Button) findViewById(R.id.button8);
        number9 = (Button) findViewById(R.id.button9);
        scientific = (Button) findViewById(R.id.buttonScientific);
        clear = (Button) findViewById(R.id.buttonClear);
        plus = (Button) findViewById(R.id.buttonPlus);
        minus = (Button) findViewById(R.id.buttonMinus);
        multiply = (Button) findViewById(R.id.buttonTimes);
        divide = (Button) findViewById(R.id.buttonDivide);
        equals = (Button) findViewById(R.id.buttonEquals);

        decimal = (Button) findViewById(R.id.buttonDecimal);
        display = (EditText) findViewById(R.id.results);

        Intent intent = getIntent();
        String p1 = intent.getStringExtra("score2");
        display.setText(p1);

        if (p1 == null) {
            send("0");
        } else {
            mydouble = Double.parseDouble(p1);
            callDoubleMaker(mydouble);
        }

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableSoftInputFromAppearing(display);
            }
        });


        number0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send("0");

            }
        });

        number1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                send("1");

            }
        });

        number2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send("2");

            }
        });

        number3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send("3");

            }
        });

        number4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send("4");

            }
        });

        number5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send("5");

            }
        });

        number6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send("6");

            }
        });

        number7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send("7");

            }
        });

        number8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send("8");

            }
        });

        number9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send("9");

            }
        });

        scientific.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stateToSave1 = display.getText().toString();
                Intent intent = new Intent(Simple.this, Scientific.class);
                intent.putExtra("score1", stateToSave1);
                startActivity(intent);

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Reset Everything to original defaults
                displayString = "";
                operation = 'x';
                num = 0;
                numtemp = 0;
                display.setText("");
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Display toast of the operation use.
                Context context = getApplicationContext();
                CharSequence text = " + ";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.LEFT, 0, -230);
                toast.show();
                perform();
                operation = '+';

            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Display toast of the operation use.
                Context context = getApplicationContext();
                CharSequence text = " - ";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.LEFT, 0, -230);
                toast.show();
                perform();
                operation = '-';

            }
        });

        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Display toast of the operation use.
                Context context = getApplicationContext();
                CharSequence text = " x ";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.LEFT, 0, -230);
                toast.show();
                perform();
                operation = '*';

            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Display toast of the operation use.
                Context context = getApplicationContext();
                CharSequence text = " ÷ ";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.LEFT, 0, -230);
                toast.show();
                perform();
                operation = '/';

            }
        });


        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();

            }
        });

        decimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send(".");
            }
        });

        disableSoftInputFromAppearing(display);

    }

    public static void disableSoftInputFromAppearing(EditText editText) {
        if (Build.VERSION.SDK_INT >= 11) {
            editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
            editText.setTextIsSelectable(true);
        } else {
            editText.setRawInputType(InputType.TYPE_NULL);
            editText.setFocusable(true);
        }
    }

    private void callDoubleMaker(double x) {
        num = x;
    }

    private void send(String x) {
        //Build the string from here.
        displayString = displayString + x;
        if (displayString.endsWith(".")) {
            display.setText(displayString);
        }
        num = Double.valueOf(displayString).doubleValue();
        display.setText(displayString);

    }

    private void perform() {
        displayString = "";
        numtemp = num;
    }

    private void calculate() {
        if (operation == '+') {
            num = numtemp + num;
        } else if (operation == '-')
            num = numtemp - num;
        else if (operation == '/')
            num = numtemp / num;
        else if (operation == '*')
            num = numtemp * num;
        display.setText("" + num);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_simple, menu);
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
        } else if (id == R.id.science) {
            String stateToSave1 = display.getText().toString();
            Intent intent = new Intent(Simple.this, Scientific.class);
            intent.putExtra("score1", stateToSave1);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }
}


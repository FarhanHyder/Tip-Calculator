/*
        Farhan Hyder
        CSc 221 - Project Phase II
        Submission date - 12/06/17
        Submitted to - Professor Mohammed Saadi
 */


package com.example.gung.myapplication;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button resetBtn;
    Button calculateBtn;

    TextView resultValueTv;

    Button percentage10Btn;
    Button percentage15Btn;
    Button percentage20Btn;
    Button percentage25Btn;

    Button activatedPercentageBtn;

    EditText billAmount;
    EditText percentage;
    EditText numPeople;

    Double billAmountValue = 0.0;
    Double percentageValue = 0.0;
    Double numPeopleValue = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resetBtn = (Button) findViewById(R.id.reset_btn);
        resetBtn.setOnClickListener(this);

        calculateBtn = (Button) findViewById(R.id.calculate_btn);
        calculateBtn.setOnClickListener(this);

        resultValueTv = (TextView) findViewById(R.id.pay_amount_result_value_tv);

        percentage10Btn = (Button) findViewById(R.id.percentage_10_value_btn);
        percentage10Btn.setOnClickListener(this);

        percentage15Btn = (Button) findViewById(R.id.percentage_15_value_btn);
        percentage15Btn.setOnClickListener(this);

        percentage20Btn = (Button) findViewById(R.id.percentage_20_value_btn);
        percentage20Btn.setOnClickListener(this);

        percentage25Btn = (Button) findViewById(R.id.percentage_25_value_btn);
        percentage25Btn.setOnClickListener(this);

        billAmount = (EditText) findViewById(R.id.bill_amount_et);
        billAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (billAmount.getText().toString().length() == 0) {
                    billAmountValue = 0.0;
                } else {
                    billAmountValue = Double.parseDouble(billAmount.getText().toString());
                }
            }
        });

        percentage = (EditText) findViewById(R.id.tip_percentage_et);
        percentage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (percentage.getText().toString().length() == 0) {
                    if (activatedPercentageBtn == null)
                        percentageValue = 0.0;
                } else {
                    percentageValue = Double.parseDouble(percentage.getText().toString());
                    if (activatedPercentageBtn != null) {
                        activatedPercentageBtn.setBackgroundColor(Color.parseColor("#999999"));
                        activatedPercentageBtn = null;
                    }
                }
            }
        });

        numPeople = (EditText) findViewById(R.id.num_people_et);
        numPeople.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (numPeople.getText().toString().length() == 0) {
                    numPeopleValue = 0.0;
                } else {
                    numPeopleValue = Double.parseDouble(numPeople.getText().toString());
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reset_btn:
                reset();
                break;
            case R.id.calculate_btn:
                calculate();
                break;
            case R.id.percentage_10_value_btn:
                percentageAct(10.0, percentage10Btn);
                break;
            case R.id.percentage_15_value_btn:
                percentageAct(15.0, percentage15Btn);
                break;
            case R.id.percentage_20_value_btn:
                percentageAct(20.0, percentage20Btn);
                break;
            case R.id.percentage_25_value_btn:
                percentageAct(25.0, percentage25Btn);
                break;
                default:
                    break;
        }
    }

    public void calculate() {

        if (!checkValues()) {
            // alert
            resultValueTv.setText("Please input all above values!!!");
        } else {
            Double result = (billAmountValue * (1 + percentageValue/100)) / numPeopleValue;
            resultValueTv.setText(String.format("%.2f", result));
        }
    }

    public boolean checkValues() {
        if (billAmountValue == 0.0 || percentageValue == 0.0 || numPeopleValue == 0.0) {
            return false;
        }
        return true;
    }

    public void percentageAct(Double value, Button button) {
        if (activatedPercentageBtn != null)
            activatedPercentageBtn.setBackgroundColor(Color.parseColor("#999999"));
        activatedPercentageBtn = button;
        activatedPercentageBtn.setBackgroundColor(Color.parseColor("#395723"));
        percentageValue = value;
        percentage.setText("");
    }

    public void reset() {
        billAmountValue = 0.0;
        percentageValue = 0.0;
        numPeopleValue = 0.0;
        resultValueTv.setText("");
        percentage.setText("");
        billAmount.setText("");
        numPeople.setText("");
        if (activatedPercentageBtn != null)
            activatedPercentageBtn.setBackgroundColor(Color.parseColor("#999999"));
    }
}

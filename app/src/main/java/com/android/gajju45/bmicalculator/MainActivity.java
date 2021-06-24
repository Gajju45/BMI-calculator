package com.android.gajju45.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText weightET, heightET, ageET;
    TextView txtRes, txtInter;
    Button btnRes, btnReset;
    RadioGroup rg;
    int gender = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rg = findViewById(R.id.main_radio_group);

        weightET = (EditText) findViewById(R.id.main_weight_tv);
        heightET = (EditText) findViewById(R.id.main_height_tv);
        ageET = (EditText) findViewById(R.id.main_age_tv);

        txtInter = (TextView) findViewById(R.id.txtinter);
        txtRes = (TextView) findViewById(R.id.txtresult);


        btnReset = (Button) findViewById(R.id.main_try_again_bt);
        btnRes = (Button) findViewById(R.id.main_result_bt);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ageET.getText().clear();
                heightET.getText().clear();
                weightET.getText().clear();
                txtInter.setText("");
                txtRes.setText("");
                txtInter.setBackgroundResource(R.color.white);

                Toast.makeText(MainActivity.this, "Try Again BMI !!", Toast.LENGTH_SHORT).show();
            }
        });

        btnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strweight = weightET.getText().toString();
                String strheight = heightET.getText().toString();
                String strage = ageET.getText().toString();



                if (strage.equals("")) {
                    ageET.setError("Please Enter Your age");
                    ageET.requestFocus();
                    return;
                }
                if (strweight.equals("")) {
                    weightET.setError("Please Enter Your weight");
                    weightET.requestFocus();
                    return;
                }
                if (strheight.equals("")) {
                    heightET.setError("Please Enter Your height");
                    heightET.requestFocus();
                    return;
                }



                float weight = Float.parseFloat(strweight);
                float height = Float.parseFloat(strheight) / 100;
                float bmiValue = BMICalculator(weight, height);


                txtInter.setText(interpreteBMI(bmiValue));
                txtRes.setText("BMI= " + bmiValue);

                Bundle extras = new Bundle();
                extras.putFloat("RESULT",bmiValue);
                extras.putString("TEXTBMI",interpreteBMI(bmiValue));
                Intent intent=new Intent(MainActivity.this,ResultActivity.class);
                intent.putExtras(extras);
                startActivity(intent);

            }

        });


    }

    public void check(View view) {
        int checkedId = rg.getCheckedRadioButtonId();
        if (R.id.main_men_rb == checkedId) {
            Toast.makeText(this, "Men", Toast.LENGTH_SHORT).show();
            gender = 0;
        } else if (R.id.main_women_rb == checkedId) {
            Toast.makeText(this, "Women", Toast.LENGTH_SHORT).show();
            gender = 1;
        }
            else if (R.id.main_other_rb == checkedId) {
            Toast.makeText(this, "Others", Toast.LENGTH_SHORT).show();
            gender = 2;
        }
    }


    public float BMICalculator(float weight, float height) {
        return weight / (height * height);
    }

    public String interpreteBMI(float bmiValue) {
        if (bmiValue < 18.5) {
            txtInter.setBackgroundResource(R.color.under_weight);
            return "Under weight";
        } else if (bmiValue < 25) {
            txtInter.setBackgroundResource(R.color.normal_weight);
            return "Normal weight";
        } else if (bmiValue < 30) {
            txtInter.setBackgroundResource(R.color.over_weight);
            return "Over Weight weight";
        } else
            txtInter.setBackgroundResource(R.color.obese);
        return "Obese";
    }



}
package com.android.gajju45.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edWeg, edHei, age;
    TextView txtRes, txtInter;
    Button btnRes, btnReset;
    RadioGroup rg;
    int gender = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rg = findViewById(R.id.rg);

        edWeg = (EditText) findViewById(R.id.edweg);
        edHei = (EditText) findViewById(R.id.edhei);
        age = (EditText) findViewById(R.id.age);

        txtInter = (TextView) findViewById(R.id.txtinter);
        txtRes = (TextView) findViewById(R.id.txtresult);


        btnReset = (Button) findViewById(R.id.btnres);
        btnRes = (Button) findViewById(R.id.btnresult);

        btnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strweight = edWeg.getText().toString();
                String strheight = edHei.getText().toString();
                String strage = age.getText().toString();



                if (strage.equals("")) {
                    age.setError("Please Enter Your age");
                    age.requestFocus();
                    return;
                }
                if (strweight.equals("")) {
                    edWeg.setError("Please Enter Your weight");
                    edWeg.requestFocus();
                    return;
                }
                if (strheight.equals("")) {
                    edHei.setError("Please Enter Your height");
                    edHei.requestFocus();
                    return;
                }



                float weight = Float.parseFloat(strweight);
                float height = Float.parseFloat(strheight) / 100;
                float bmiValue = BMICalculator(weight, height);


                txtInter.setText(interpreteBMI(bmiValue));
                txtRes.setText("BMI= " + bmiValue);

                Intent intent=new Intent(MainActivity.this,ResultActivity.class);
                intent.putExtra("Result",bmiValue);
                intent.putExtra("textBmi",interpreteBMI(bmiValue));
                startActivity(intent);

            }

        });


    }

    public void check(View view) {
        int checkedId = rg.getCheckedRadioButtonId();
        if (R.id.rb1 == checkedId) {
            Toast.makeText(this, "Men", Toast.LENGTH_SHORT).show();
            gender = 0;
        } else if (R.id.rb2 == checkedId) {
            Toast.makeText(this, "Women", Toast.LENGTH_SHORT).show();
            gender = 1;
        }
            else if (R.id.rb3 == checkedId) {
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
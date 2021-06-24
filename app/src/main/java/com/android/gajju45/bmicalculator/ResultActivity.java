package com.android.gajju45.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView bmiDataTV, bmiStatusTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initViews();

        //Result Obtain from Main Activity
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Float bmiObtain = extras.getFloat("RESULT");
        String bmiStatusObtain = extras.getString("TEXTBMI");
        String bmiObtainString = Float.toString(bmiObtain);
        bmiDataTV.setText(bmiObtainString);
        bmiStatusTV.setText(bmiStatusObtain);


    }

    //Intialization Views
    private void initViews() {
        bmiDataTV = (TextView) findViewById(R.id.reult_data_tv);
        bmiStatusTV = (TextView) findViewById(R.id.result_bmi_status_tv);
    }
}
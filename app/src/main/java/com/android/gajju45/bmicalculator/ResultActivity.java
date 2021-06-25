package com.android.gajju45.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView bmiDataTV, bmiStatusTV;
    Button tryAgainBT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initViews();
        clickListener(this);

        //Result Obtain from Main Activity
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Float bmiObtain = extras.getFloat("RESULT");


        Float bmiStatusObtain = extras.getFloat("TEXTBMI");
        String bmiObtainString = Float.toString(bmiObtain);
        bmiStatusTV.setText(interpreteBMI(bmiObtain));
        bmiDataTV.setText(bmiObtainString);


    }


    //Intialization Views
    private void initViews() {
        bmiDataTV = (TextView) findViewById(R.id.reult_data_tv);
        bmiStatusTV = (TextView) findViewById(R.id.result_bmi_status_tv);
        tryAgainBT = (Button)findViewById(R.id.result_try_again_bt);

    }

    //Click Listeners
    private void clickListener(ResultActivity resultActivity) {
        tryAgainBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

    }

    public String interpreteBMI(float bmiValue) {
        if (bmiValue < 18.5) {
            bmiStatusTV.setBackgroundResource(R.color.under_weight);
            return "Under weight";
        } else if (bmiValue < 25) {
            bmiStatusTV.setBackgroundResource(R.color.normal_weight);
            return "Normal weight";
        } else if (bmiValue < 30) {
            bmiStatusTV.setBackgroundResource(R.color.over_weight);
            return "Over Weight weight";
        } else
            bmiStatusTV.setBackgroundResource(R.color.obese);
        return "Obese";
    }

}
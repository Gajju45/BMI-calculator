package com.android.gajju45.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView t1reslt, t2string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        t1reslt = (TextView) findViewById(R.id.t1reslt);
        t2string = (TextView) findViewById(R.id.t2string);

        String Result = getIntent().getStringExtra("Result");
        String strtextBmi = getIntent().getStringExtra("textBmi");

        t1reslt.setText(Result);
        t2string.setText("Yor BMI Resul is "+strtextBmi);
        // txtInter.setText("text= "+key);


    }
}
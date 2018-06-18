package com.createchs.middleearth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RadioGroup question1Radios = (RadioGroup) findViewById(R.id.question1_options);
        String[] question1Options = getResources().getStringArray(R.array.question1);
        for (int i = 0; i < question1Radios.getChildCount(); i++) {
            ((RadioButton) question1Radios.getChildAt(i)).setText(question1Options[i]);
        }
    }
}

package com.createchs.middleearth;

import android.content.res.XmlResourceParser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void getScores(View view) {
        int score = 0;
        int[] answers = {
                R.id.question1_option1,
                R.id.question2_option5,
                3,
                5,
                R.id.question5_option2,
                R.id.question5_option4,
                R.id.question6_option2,
                R.id.question6_option3,
                0
        };
        String[] viewTypes = {
                "RadioButton",
                "RadioButton",
                "EditText",
                "EditText",
                "CheckBox",
                "CheckBox",
                "CheckBox",
                "CheckBox",
                "EditText"
        };
        int[] editViews = {
                R.id.question3_ans,
                R.id.question4_ans,
                R.id.question7_ans
        };
        int editsIndex = 0;
        for (int i = 0; i < answers.length; i++) {
            switch (viewTypes[i]) {
                case "RadioButton":
                    if (((RadioButton) findViewById(answers[i])).isChecked()) {
                        score += 1;
                    }
                    break;
                case "EditText":
                    if (((EditText) findViewById(editViews[editsIndex])).getText().toString().equals(
                            String.valueOf(answers[i])
                    )) {
                        score += 1;
                        editsIndex += 1;
                    }
                    break;
                case "CheckBox":
                    if (((CheckBox) findViewById(answers[i])).isChecked()) {
                        score += 1;
                    }
                    break;
                default: break;
            }

        }
        Log.i("MainActivity", "" + score);
        giveFeedback(score);
    }

    private void giveFeedback(int score) {
        Toast.makeText(this, "You scored " + score, Toast.LENGTH_SHORT).show();
    }
}

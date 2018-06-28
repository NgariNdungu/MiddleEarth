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

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getScores(View view) {
        int[] q5answers = {R.id.question5_option2, R.id.question5_option4};
        int[] q6answers = {R.id.question6_option3, R.id.question6_option3};
        HashMap answers = new HashMap();
        answers.put("q1", R.id.question1_option1);
        answers.put("q2", R.id.question2_option5);
        answers.put("q3", 3);
        answers.put("q4", 5);
        answers.put("q5", q5answers);
        answers.put("q6", q6answers);
        answers.put("q7", 0);


        int score = 0;


        boolean questionOneIsCorrect = ((RadioButton) findViewById((int) answers.get("q1"))).isChecked();
        boolean questionTwoIsCorrect = ((RadioButton) findViewById((int) answers.get("q2"))).isChecked();

        EditText questionThreeAns = (EditText) findViewById(R.id.question3_ans);
        boolean questionThreeIsCorrect = questionThreeAns.getText().toString().equals(
                answers.get("q3").toString());
        EditText questionFourAns = (EditText) findViewById(R.id.question4_ans);
        boolean questionFourIsCorrect = questionFourAns.getText().toString().equals(
                answers.get("q4").toString());
        boolean[] questionFiveIsCorrect = {
                ((CheckBox) findViewById(q5answers[0])).isChecked(),
                ((CheckBox) findViewById(q5answers[1])).isChecked()
        };
        boolean[] questionSixIsCorrect = {
                ((CheckBox) findViewById(q6answers[0])).isChecked(),
                ((CheckBox) findViewById(q6answers[1])).isChecked()
        };
        EditText questionSevenAns = (EditText) findViewById(R.id.question7_ans);
        boolean questionSevenIsCorrect = questionSevenAns.getText().toString().equals(
                answers.get("q7").toString()
        );

        Log.i("MainActivity", "" + questionOneIsCorrect +
        " " + questionTwoIsCorrect +
        " " + questionThreeIsCorrect +
        " " + questionFourIsCorrect +
        "\n" + questionFiveIsCorrect +
        "\n" + questionSixIsCorrect +
        "\n" + questionSevenIsCorrect);

    }

}

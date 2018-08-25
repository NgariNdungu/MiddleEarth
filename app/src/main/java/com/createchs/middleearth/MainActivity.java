package com.createchs.middleearth;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    // list to keep track of whether questions are correctly answered
    private boolean[] correctlyAnswered = new boolean[7];
    private static final String ANSWERS = "answers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // attach click listener to submit button
        Button submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getScores();
            }
        });
    }

    /*
     * Method for calculating the score, called on submit
     */
    public void getScores() {
        int score = 0;
        /*
         * Multi-choice multi answer questions
         * Scored by checking the choices with their expected checked state
         */
        int[] questionFiveChoices = {
                R.id.question5_option1,
                R.id.question5_option2,
                R.id.question5_option3,
                R.id.question5_option4
        };
        boolean[] qFiveExpectedState = {false, true, false, true};

        int[] questionSixChoices = {
                R.id.question6_option1,
                R.id.question6_option2,
                R.id.question6_option3,
                R.id.question6_option4
        };
        boolean[] qSixExpectedState = {false, true, true, false};

        // Answers to all questions
        Object[][] answers = {
                {R.id.question1_option1},
                {R.id.question2_option5},
                {3},
                {5},
                {questionFiveChoices, qFiveExpectedState},
                {questionSixChoices, qSixExpectedState},
                {0}
        };

        String[] viewTypes = {
                "RadioButton",
                "RadioButton",
                "EditText",
                "EditText",
                "CheckBox",
                "CheckBox",
                "EditText"
        };

        // text inputs
        int[] editViews = {
                R.id.question3_ans,
                R.id.question4_ans,
                R.id.question7_ans
        };
        int editViewsIndex = 0;
        for (int i = 0; i < answers.length; i++) {
            switch (viewTypes[i]) {
                case "RadioButton":
                    if (((RadioButton) findViewById(((int) answers[i][0]))).isChecked()) {
                        score += 1;
                        correctlyAnswered[i] = true;
                    }
                    break;
                case "EditText":
                    if (((EditText) findViewById(editViews[editViewsIndex])).getText().toString().
                            equals(String.valueOf(answers[i][0])
                    )) {
                        score += 1;
                        correctlyAnswered[i] = true;
                        editViewsIndex += 1;
                    }
                    break;
                case "CheckBox":
                    boolean isCorrect = checkMultiChoiceQuestion(((int[]) answers[i][0]),
                            ((boolean[]) answers[i][1]));
                    if (isCorrect) {
                        score += 2;
                        correctlyAnswered[i] = isCorrect;
                    }
                    break;
            }

        }
        giveFeedback(score, null);
    }

    /*
     * This method checks that only the correct answers are checked for multiple choice questions
     */
    private boolean checkMultiChoiceQuestion(int[] questionChoices, boolean[] qExpectedState) {
        if (questionChoices.length == qExpectedState.length) {
            for (int i = 0; i < questionChoices.length; i++) {
                if (!((CheckBox) findViewById(questionChoices[i])).isChecked() == qExpectedState[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
     * Method provides feedback to the user, by displaying the score and setting the background
     * on question cards depending on whether the question is correctly answered
     */
    private void giveFeedback(int score, @Nullable Bundle savedInstanceState) {
        int[] questionCardViews = {
                R.id.question1_card,
                R.id.question2_card,
                R.id.question3_card,
                R.id.question4_card,
                R.id.question5_card,
                R.id.question6_card,
                R.id.question7_card
        };
        for (int i = 0; i < questionCardViews.length; i++) {
            if (!correctlyAnswered[i]) {
                findViewById(questionCardViews[i]).setBackgroundColor(
                        getResources().getColor(R.color.incorrect_color, null));
            } else {
                findViewById(questionCardViews[i]).setBackgroundColor(
                        getResources().getColor(R.color.colorSecondaryDark, null)
                );
            }
        }

        if (savedInstanceState == null) {
            String message;
            if (score < 5) {
                message = getResources().getString(R.string.poor);
            } else if (score < 7) {
                message = getResources().getString(R.string.average);
            } else {
                message = getResources().getString(R.string.good);
            }

            String toastMessage = String.format("You scored %s/9. %s", score, message);

            Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBooleanArray(ANSWERS, correctlyAnswered);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        correctlyAnswered = savedInstanceState.getBooleanArray(ANSWERS);
        giveFeedback(0,savedInstanceState);
    }
}

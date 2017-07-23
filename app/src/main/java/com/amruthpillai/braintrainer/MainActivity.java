package com.amruthpillai.braintrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startGameButton;
    TextView resultTextView;
    ArrayList<Integer> answers = new ArrayList<>();
    int locationCorrectAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startGameButton = (Button) findViewById(R.id.startButton);
        TextView sumTextView = (TextView) findViewById(R.id.sumTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);

        Button button0 = (Button) findViewById(R.id.button0);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);

        Random random = new Random();

        int a = random.nextInt(21);
        int b = random.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationCorrectAnswer = random.nextInt(4);

        int incorrectAnswer;

        for (int i = 0; i < 4; i++) {
            if (i == locationCorrectAnswer) {
                answers.add(a + b);
            } else {
                incorrectAnswer = random.nextInt(41);
                while (incorrectAnswer == (a + b)) {
                    incorrectAnswer = random.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view) {
        if (view.getTag().toString().equals(Integer.toString(locationCorrectAnswer))) {
            resultTextView.setText("Correct!");
        } else {
            resultTextView.setText("Wrong!");
        }
    }

    public void startGame(View view) {
        startGameButton.setVisibility(View.INVISIBLE);
    }
}

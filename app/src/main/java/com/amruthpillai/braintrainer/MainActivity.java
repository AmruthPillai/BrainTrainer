package com.amruthpillai.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout gameConstraintLayout;

    private Button startGameButton;
    private Button playAgainButton;
    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;

    private TextView timerTextView;
    private TextView pointsTextView;
    private TextView sumTextView;
    private TextView resultTextView;
    private final ArrayList<Integer> answers = new ArrayList<>();

    private int locationCorrectAnswer;
    private int score = 0;
    private int numOfQuestions = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameConstraintLayout = (ConstraintLayout) findViewById(R.id.gameConstraintLayout);

        startGameButton = (Button) findViewById(R.id.startButton);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);

        timerTextView = (TextView) findViewById(R.id.timerTextView);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
    }

    private void generateQuestion() {
        answers.clear();

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
            score++;
        } else {
            resultTextView.setText("Wrong!");
        }

        numOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + " / " + Integer.toString(numOfQuestions));

        generateQuestion();
    }

    public void startGame(View view) {
        startGameButton.setVisibility(View.INVISIBLE);
        gameConstraintLayout.setVisibility(View.VISIBLE);

        playAgain(playAgainButton);
    }

    public void playAgain(View view) {
        score = 0;
        numOfQuestions = 0;

        timerTextView.setText("30s");
        pointsTextView.setText("0 / 0");
        resultTextView.setText("");

        playAgainButton.setVisibility(View.INVISIBLE);

        generateQuestion();

        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long l) {

                button0.setEnabled(true);
                button1.setEnabled(true);
                button2.setEnabled(true);
                button3.setEnabled(true);

                timerTextView.setText(String.valueOf(l / 1000) + "s");

            }

            @Override
            public void onFinish() {

                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);


                playAgainButton.setVisibility(View.VISIBLE);
                resultTextView.setText("Your Score: " + Integer.toString(score) + " / " + Integer.toString(numOfQuestions));

            }

        }.start();
    }
}

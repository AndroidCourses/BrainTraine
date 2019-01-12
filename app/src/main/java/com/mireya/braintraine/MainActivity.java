package com.mireya.braintraine;

import android.annotation.SuppressLint;
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
    private Button btnGo, btnPlayAgain, btnAnswer0, btnAnswer1, btnAnswer2, btnAnswer3;
    private TextView txtSum, txtScore, txtTimer;
    private int locationOfCorrectAnswer;
    private TextView txtResult;
    private int score =0;
    private int numberOfQuestions = 0;
    private ConstraintLayout constraintBrain;
    private ArrayList<Integer> answer = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGo = findViewById(R.id.btnGo);
        txtSum = findViewById(R.id.txtSum);
        txtResult = findViewById(R.id.txtResult);
        txtScore = findViewById(R.id.txtScore);
        txtTimer = findViewById(R.id.txtTimer);
        btnAnswer0 = findViewById(R.id.btnChoseAnswer);
        btnAnswer1 = findViewById(R.id.btnChoseAnswer1);
        btnAnswer2 = findViewById(R.id.btnChoseAnswer2);
        btnAnswer3 = findViewById(R.id.btnChoseAnswer3);
        btnPlayAgain = findViewById(R.id.btnPlayAgain);
        constraintBrain = findViewById(R.id.ConstraintlBrain);
        btnGo.setVisibility(View.VISIBLE);
        constraintBrain.setVisibility(View.INVISIBLE);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });

        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
            }
        });

    }


    private void start(){
        btnGo.setVisibility(View.INVISIBLE);
        constraintBrain.setVisibility(View.VISIBLE);
        playAgain();
    }

    @SuppressLint("SetTextI18n")
    public void chooseAnswer(View view){
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
            txtResult.setText(R.string.correct);
            score++;
        }else {
            txtResult.setText(R.string.wrong);
        }
        numberOfQuestions++;
        txtScore.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestion();
    }

    @SuppressLint("SetTextI18n")
    private void newQuestion(){
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        txtSum.setText(Integer.toString(a) + " + " + Integer.toString(b));
        locationOfCorrectAnswer = random.nextInt(4);

        answer.clear();
        for (int i=0; i<4; i++){
            if (i == locationOfCorrectAnswer){
                answer.add(a+b);
            }else {
                int wrongAsnwet = random.nextInt(41);
                while (wrongAsnwet == a+b){
                    wrongAsnwet = random.nextInt(41);
                }
                answer.add(wrongAsnwet);
            }
        }

        btnAnswer0.setText(Integer.toString(answer.get(0)));
        btnAnswer1.setText(Integer.toString(answer.get(1)));
        btnAnswer2.setText(Integer.toString(answer.get(2)));
        btnAnswer3.setText(Integer.toString(answer.get(3)));
    }

    @SuppressLint("SetTextI18n")
    private void playAgain(){
        score = 0;
        numberOfQuestions = 0;
        txtTimer.setText(R.string.timer);
        txtScore.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestion();
        btnPlayAgain.setVisibility(View.INVISIBLE);
        txtResult.setText("");
        new CountDownTimer(30100, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                txtTimer.setText(String.valueOf(millisUntilFinished/1000 + "s"));

            }

            @Override
            public void onFinish() {
                txtResult.setText(R.string.done);
                btnPlayAgain.setVisibility(View.VISIBLE);
            }
        }.start();
    }

}

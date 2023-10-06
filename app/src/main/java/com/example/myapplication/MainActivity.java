package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    private Button trueBtn, falseBtn, nextBtn;
    private TextView questionTextView;
    private TextView scoreTextView;
    private int currentIndex = 0;
    private int score = 0;
    private Question[] questions = new Question[] {
            new Question(R.string.q_activity, true),
            new Question(R.string.q_version, false),
            new Question(R.string.q_listener, true),
            new Question(R.string.q_resources, true),
            new Question(R.string.q_find_resources, false)
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questionTextView = findViewById(R.id.question_text_view);
        trueBtn = findViewById(R.id.true_button);
        falseBtn = findViewById(R.id.false_button);
        nextBtn = findViewById(R.id.next_button);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Quiz");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        scoreTextView = findViewById(R.id.score_text_view);

        trueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswerCorrectness(true);
            }
        });
        falseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswerCorrectness(false);
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = (currentIndex + 1) % questions.length;
                setNextQuestion();
            }
        });
        setNextQuestion();
    }

    private void checkAnswerCorrectness(boolean userAnswer) {
        boolean correctAnswer = questions[currentIndex].isTrueAnswer();
        int messageId;
        if (userAnswer == correctAnswer) {
            messageId = R.string.correct_answer;
            score++;
        }
        else {
            messageId = R.string.incorrect_answer;
        }
        Toast.makeText(MainActivity.this, messageId, Toast.LENGTH_SHORT).show();
    }

    private void setNextQuestion() {
        int questionId = questions[currentIndex].getQuestionId();
        questionTextView.setText(questionId);
        scoreTextView.setText("Score: " + score);
    }

}
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;


public class MainActivity extends AppCompatActivity {
    private Button trueBtn, falseBtn, nextBtn, promptBtn;
    private TextView questionTextView;
    private TextView scoreTextView;
    private int currentIndex = 0;
    private int score = 0;

    public static final String KEY_CURRENT_INDEX = "currentIndex";
    public static final String KEY_EXTRA_ANSWER = "com.exmaple.myapplication.correctAnswer";
    private static final int REQUEST_CODE_PROMPT = 0;
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
        Log.d("QUIZ_TAG", "Wywołanie onCreate");
        setContentView(R.layout.activity_main);
        questionTextView = findViewById(R.id.question_text_view);
        trueBtn = findViewById(R.id.true_button);
        falseBtn = findViewById(R.id.false_button);
        nextBtn = findViewById(R.id.next_button);
        promptBtn = findViewById(R.id.prompt_button);
        scoreTextView = findViewById(R.id.score_text_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Quiz");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

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

        promptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intet = new Intent(MainActivity.this, PromptActivity.class);
                boolean correctAnswer = questions[currentIndex].isTrueAnswer();
                intet.putExtra(KEY_EXTRA_ANSWER, correctAnswer);
                startActivityForResult(intet, REQUEST_CODE_PROMPT);
            }
        });

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

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("QUIZ_TAG", "Wywołanie onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("QUIZ_TAG", "Wywołanie onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("QUIZ_TAG", "Wywołanie onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("QUIZ_TAG", "Wywołanie onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("QUIZ_TAG", "Wywołanie onResume");
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("QUIZ_TAG", "Wywołanie onSaveInstanceState");
        outState.putInt(KEY_CURRENT_INDEX, currentIndex);
    }
}
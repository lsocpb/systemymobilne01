package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PromptActivity extends AppCompatActivity {

    private boolean correctAnswer;

    private TextView answerTextView;

    private Button showAnswerCorrectButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt);
        answerTextView = findViewById(R.id.answer_text_view);
        showAnswerCorrectButton = findViewById(R.id.show_answer_button);
        correctAnswer = getIntent().getBooleanExtra(MainActivity.KEY_EXTRA_ANSWER, false);

        showAnswerCorrectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int answer = correctAnswer ? R.string.btn_true : R.string.btn_false;
                answerTextView.setText(answer);
            }
        });



    }
}
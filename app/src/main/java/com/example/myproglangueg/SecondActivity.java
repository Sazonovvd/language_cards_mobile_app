package com.example.myproglangueg;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class SecondActivity extends Activity {
int count;
    String needCheck;
    String wordText;
    String[] splitText;

    Button stopSecButton;
    Button checkButton;
    EditText secondText;
    TextView firstText;
    TextView trueFalseText;
    ArrayList<String> words = new ArrayList<>(); // List of items to learn

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        count = 0;

        stopSecButton = findViewById(R.id.stopSecButton);
        checkButton = findViewById(R.id.checkButton);
        firstText = findViewById(R.id.firstText);
        secondText = findViewById(R.id.secondText);
        trueFalseText = findViewById(R.id.trueFalseText);

        Intent intent = new Intent();
        words = intent.getStringArrayListExtra("words");
        splitText = words.get(count+1).split(" == ");

        wordText = (splitText[0]);
        needCheck = (splitText[1]);

        firstText.setText(wordText);
        count++;
    }

    public void btnStopSecButtonClick() {
        stopSecButton.setOnClickListener(v -> {
            Intent intent = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    public void btnCheckClick() {
        checkButton.setOnClickListener(v -> {
            String secCheck = String.valueOf(secondText.getText());
            if (secCheck.equals(needCheck)){
                trueFalseText.setText("Correct");
            } else {
                trueFalseText.setText("Incorrect");
            }
        });
    }
}

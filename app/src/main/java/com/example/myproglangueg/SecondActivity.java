package com.example.myproglangueg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class SecondActivity extends Activity {
    int count;
    String needCheck;

    Button stopSecButton;
    Button checkButton;
    Button startButton;
    EditText secondText;
    TextView firstText;
    TextView trueFalseText;
    ArrayList<String> words; // List of items to learn
//    ArrayList<String> words = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.d("onCreate second activity", "");

        count = 0; //?  Attempt to invoke virtual method 'java.lang.Object java.util.ArrayList.get(int)' on a null object reference

        startButton = findViewById(R.id.startButton);
        stopSecButton = findViewById(R.id.stopSecButton);
        checkButton = findViewById(R.id.checkButton);
        firstText = findViewById(R.id.firstText);
        secondText = findViewById(R.id.secondText);
        trueFalseText = findViewById(R.id.trueFalseText);

        trueFalseText.setVisibility(View.INVISIBLE);

        //Intent intent = new Intent();
        Bundle arguments = getIntent().getExtras();
        words = (ArrayList<String>) arguments.get("words");
//        words = arguments.getStringArrayListExtra("words");
        Log.d("TEST_START-CLICK", String.valueOf(words.size()));
    }

    public void btnStopSecButtonClick() {
        stopSecButton.setOnClickListener(v -> {
            Intent intent = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    public void btnCheckClick(View view) {
        checkButton.setOnClickListener(v -> {
            String secCheck = String.valueOf(secondText.getText());
            trueFalseText.setVisibility(View.VISIBLE);
            if (secCheck.equals(needCheck)) {
                trueFalseText.setText("Correct");
                count++;
            } else {
                trueFalseText.setText("Incorrect");
            }
        });
    }

    public void btnStartClick(View view) {
        startButton.setOnClickListener(v -> {
            trueFalseText.setVisibility(View.INVISIBLE);
            if (count >= words.size()) {
                count = 0;
                Log.d("COUNT_CHECK", "again 0");
            }
            Log.d("TEST_START-CLICK", words.get(count));
            String[] splitWordsTranslation = words.get(count).split(" == ");
            firstText.setText(splitWordsTranslation[0]);
            needCheck = splitWordsTranslation[1];
            startButton.setText("Next");
        });
    }
}

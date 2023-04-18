package com.example.myproglangueg;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class SecondActivity extends Activity {
//    ListView my_list_second;
//    Adapter adapter;
//    FileInputOutputStream fileStream = new FileInputOutputStream(my_list_second);
    int count;
    String needCheck;

    Button stopSecButton;
    Button checkButton;
    EditText secondText;
    TextView firstText;
    ArrayList<String> words = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        count = 0;

        stopSecButton = findViewById(R.id.stopSecButton);
        checkButton = findViewById(R.id.checkButton);
        firstText = findViewById(R.id.firstText);
        secondText = findViewById(R.id.secondText);

        words.add("Hello");
        words.add("Hi");
        words.add("Здравствуйте");
        words.add("Привет");
//        my_list_second = findViewById(R.id.my_list_second);
//        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, words_list);
//        my_list_second.setAdapter((ListAdapter) adapter);
//
//        try {
//            fileStream.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        firstText.setText(words.indexOf(count));
        count++;
    }

    public void btnStopSecButtonClick() {
        stopSecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void btnCheckClick() {
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String secCheck = String.valueOf(secondText.getText());
                needCheck = String.valueOf(firstText.getText());
                if (secCheck.equals(needCheck)){
                    firstText.setText("Correct");
                }
            }
        });
    }
}

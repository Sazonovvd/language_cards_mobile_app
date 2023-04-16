package com.example.myproglangueg;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class SecondActivity extends Activity {
    Button stopSecButton;
    ListView my_list_second;
    Adapter adapter;

    FileInputOutputStream fileStream = new FileInputOutputStream(my_list_second);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ArrayList<String> words_list = new ArrayList<String>();

        my_list_second = findViewById(R.id.my_list_second);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, words_list);
        my_list_second.setAdapter((ListAdapter) adapter);

        try {
            fileStream.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnStopSecButtonClick() {
        stopSecButton = (Button) findViewById(R.id.stopSecButton);
        stopSecButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

package com.example.myproglangueg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button addButton;
    Button buttonStart;
    Button buttonCancel;
    Button deleteButton;
    EditText addWord;
    EditText addTranslation;
    ListView my_list;

    String wordText;
    String translationText;

    int itemPosition;
    ArrayAdapter<String> adapter;

    ArrayList<String> words = new ArrayList<>(); // List of words to learn

    FileInputOutputStream fileStream = new FileInputOutputStream(my_list);

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = findViewById(R.id.buttonStart);
        buttonCancel = findViewById(R.id.buttonCansel);
        deleteButton = findViewById(R.id.delete);
        addButton = findViewById(R.id.addButton);
        addWord = findViewById(R.id.addWord);
        addTranslation = findViewById(R.id.addTranslation);
        my_list = findViewById(R.id.my_list);

        Bundle arguments = getIntent().getExtras();
        try {
            if (arguments.get("words") != null) {
                words = (ArrayList<String>) arguments.get("words");
            }
        } catch (Exception ignored){}
        Log.d("TEST_START-CLICK", String.valueOf(words.size()));

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, words);
        my_list.setAdapter(adapter);

        buttonCancel.setVisibility(View.INVISIBLE);
        deleteButton.setVisibility(View.INVISIBLE);

        itemPosition = -1;

        my_list.setOnItemClickListener((parent, view, position, id) -> {
            String[] splitText = words.get(position).split(" == ");

            addWord.setText(splitText[0]);
            addTranslation.setText(splitText[1]);

            wordText = splitText[0];
            translationText = splitText[1];

            itemPosition = position;
            addButton.setText("Rename");
            buttonCancel.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.VISIBLE);
        });
        buttonStart.setOnClickListener(v -> {
            if (v.getId() == R.id.buttonStart) {
                Intent intent = new Intent(this, SecondActivity.class);
                intent.putStringArrayListExtra("words", words);
                startActivity(intent);
            }
        });

        buttonCancel.setOnClickListener(v -> {
            words.add(wordText + " == " + translationText);

            words.remove(itemPosition);

            addButton.setText("add");
            addWord.setText("");
            addTranslation.setText("");

            adapter.notifyDataSetChanged();
            buttonCancel.setVisibility(View.INVISIBLE);
            deleteButton.setVisibility(View.INVISIBLE);
        });

        deleteButton.setOnClickListener(v -> {
            words.remove(itemPosition);
            addButton.setText("add");
            buttonStart.setText("start learning");
            addWord.setText("");
            addTranslation.setText("");
            adapter.notifyDataSetChanged();
            buttonCancel.setVisibility(View.INVISIBLE);
            deleteButton.setVisibility(View.INVISIBLE);
        });
    }

    @SuppressLint("SetTextI18n")
    public void add(View view) {
        String word = addWord.getText().toString();
        String translation = addTranslation.getText().toString();
        String name = word + " == " + translation;

        if (itemPosition > -1) {
            words.remove(itemPosition);
            words.add(name);

            addButton.setText("add");
            buttonStart.setText("start learning");

            itemPosition = -1;
            wordText = "";
            translationText = "";
        } else {
            words.add(name);
        }

        try {
            fileStream.write(name);
        } catch (IOException e) {
            e.printStackTrace();
        }

        addWord.setText("");
        addTranslation.setText("");
        adapter.notifyDataSetChanged();
        deleteButton.setVisibility(View.INVISIBLE);
        buttonCancel.setVisibility(View.INVISIBLE);
    }
}

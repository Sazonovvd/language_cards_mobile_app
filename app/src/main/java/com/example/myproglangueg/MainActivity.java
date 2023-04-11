package com.example.myproglangueg;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button addButton;
    Button buttonStart;
    Button deleteButton;
    EditText addWord;
    EditText addTranslation;
    ListView my_list;

    String wordR;
    String translationR;

    int itemPosition;
    ArrayAdapter adapter;

    ArrayList<String> words = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        deleteButton = findViewById(R.id.delete);
        buttonStart = findViewById(R.id.buttonStart);
        addButton = findViewById(R.id.addButton);
        addWord = findViewById(R.id.addWord);
        addTranslation = findViewById(R.id.addTranslation);
        my_list = findViewById(R.id.my_list);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, words);
        my_list.setAdapter(adapter);
        deleteButton.setVisibility(View.INVISIBLE);

        itemPosition = -1;

        my_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] splitText = words.get(position).split(" == ");

//                words.get(position).(" -- Refactoring -- ");

                addWord.setText(splitText[0].toString());
                addTranslation.setText(splitText[1].toString());

                wordR = splitText[0];
                translationR = splitText[1];

                itemPosition = position;
                addButton.setText("Rename");
                buttonStart.setText("Cancel");
                deleteButton.setVisibility(View.VISIBLE);
            }
        });
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonStart.getText().toString().equals("Cancel")) {
                    words.add(wordR + " == " + translationR);

                    words.remove(itemPosition);

                    addButton.setText("add");
                    buttonStart.setText("start learning");
                    addWord.setText("");
                    addTranslation.setText("");
                    adapter.notifyDataSetChanged();
                    deleteButton.setVisibility(View.INVISIBLE);
                }
                if (buttonStart.getText().toString().equals("start learning")) {
                    switch (v.getId()) {
                        case R.id.buttonStart:
                            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                            startActivity(intent);
                            break;
                        default:
                            break;
                    }
                }
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                words.remove(itemPosition);
                deleteButton.setVisibility(View.INVISIBLE);
                addButton.setText("add");
                buttonStart.setText("start learning");
                addWord.setText("");
                addTranslation.setText("");
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void add(View view) {
        String word = addWord.getText().toString();
        String translation = addTranslation.getText().toString(); // сделать delete cancel

        if (itemPosition > -1) {
            words.remove(itemPosition);
            words.add(word + " == " + translation);

            addButton.setText("add");
            buttonStart.setText("start learning");

            itemPosition = -1;
            wordR = "";
            translationR = "";
        } else {
            words.add(word + " == " + translation);
        }
        addWord.setText("");
        addTranslation.setText("");
        adapter.notifyDataSetChanged();
        deleteButton.setVisibility(View.INVISIBLE);
    }
}

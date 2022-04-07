package com.techrz.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageButton createNotes, exit;
    ArrayList<allNotes> arrayList;
    customProblemAdapter customProblemAdapter;
    private ListView showNotes;
    MyDatabaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        exit = findViewById(R.id.exit);
        createNotes = findViewById(R.id.createNotes);
        exit.setOnClickListener(v-> finish());
        createNotes.setOnClickListener(v-> createNotes());
        showNotes = findViewById(R.id.showNotes);
        DB= new MyDatabaseHelper(this);
        loadDatainList();
    }
    void createNotes(){
        Intent intent = new Intent(this, createNotes.class);
        startActivity(intent);
    }
    public void loadDatainList(){
        arrayList = DB.getProblems();
        customProblemAdapter = new customProblemAdapter(this,arrayList);
        showNotes.setAdapter(customProblemAdapter);
        customProblemAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        loadDatainList();
    }
}
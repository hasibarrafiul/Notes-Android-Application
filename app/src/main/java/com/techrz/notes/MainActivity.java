package com.techrz.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageButton createNotes, exit;
    ArrayList<allNotes> arrayList;
    customNoteAdapter customNoteAdapter;
    private ListView showNotes;
    MyDatabaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        exit = findViewById(R.id.exit);
        createNotes = findViewById(R.id.createNotes);
        exit.setOnClickListener(v-> finishAffinity());
        createNotes.setOnClickListener(v-> createNotes());
        showNotes = findViewById(R.id.showNotes);
        DB= new MyDatabaseHelper(this);
    }
    void createNotes(){
        Intent intent = new Intent(this, createNotes.class);
        startActivity(intent);
    }
    public void loadDatainList(){
        arrayList = DB.getProblems();
        customNoteAdapter = new customNoteAdapter(this,arrayList);
        showNotes.setAdapter(customNoteAdapter);
        customNoteAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadDatainList();
    }
}
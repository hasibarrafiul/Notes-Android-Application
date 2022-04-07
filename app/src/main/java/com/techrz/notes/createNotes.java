package com.techrz.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class createNotes extends AppCompatActivity {
    ImageButton back, addNotes;
    EditText courseID, topic, date, note;
    MyDatabaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notes);
        DB= new MyDatabaseHelper(this);
        back = findViewById(R.id.goBack);
        addNotes = findViewById(R.id.addNotes);
        back.setOnClickListener(v-> onBackPressed());
        addNotes.setOnClickListener(v->saveNotes());

        courseID = findViewById(R.id.courseId);
        topic = findViewById(R.id.topic);
        date = findViewById(R.id.date);
        note = findViewById(R.id.note);
    }
    void saveNotes(){
        String prvcourseID = courseID.getText().toString().trim();
        String prvtopic = topic.getText().toString().trim();
        String prvdate = date.getText().toString().trim();
        String prvnote = note.getText().toString().trim();
        Boolean noError = DB.inserNotes(prvcourseID, prvtopic, prvdate, prvnote);
        if(noError==true){
            Toast.makeText(this,"Note Added",Toast.LENGTH_LONG).show();
            mainPage();
        }
        else System.out.println("Got some error");
    }
    void mainPage(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
package com.techrz.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class editNote extends AppCompatActivity {
    ImageButton back, save, delete;
    private int noteID;
    MyDatabaseHelper DB;
    EditText courseID, topic, date, note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        DB= new MyDatabaseHelper(this);
        back = findViewById(R.id.editgoBack);
        save = findViewById(R.id.editsave);
        delete = findViewById(R.id.delete);
        courseID = findViewById(R.id.editcourseId);
        topic = findViewById(R.id.edittopic);
        date = findViewById(R.id.editdate);
        note = findViewById(R.id.editnote);
        back.setOnClickListener(v->finish());
        save.setOnClickListener(v->saveEditedNotes());
        delete.setOnClickListener(v->deleteNote());
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            noteID = extras.getInt("noteID");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        String notes[];
        notes = DB.getNote(noteID);
        courseID.setText(notes[0]);
        topic.setText(notes[1]);
        date.setText(notes[2]);
        note.setText(notes[3]);
    }
    void saveEditedNotes(){
        String prvcourseID = courseID.getText().toString().trim();
        String prvtopic = topic.getText().toString().trim();
        String prvdate = date.getText().toString().trim();
        String prvnote = note.getText().toString().trim();
        Boolean noError = DB.updateNote(noteID, prvcourseID, prvtopic, prvdate, prvnote);
        if(noError==true){
            mainPage();
        }
        else System.out.println("Got some error");
    }
    void mainPage(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    void deleteNote(){
        Boolean noError = DB.deleteNote(noteID);
        if(noError==true){
            mainPage();
        }
        else System.out.println("Got some error");
    }
}
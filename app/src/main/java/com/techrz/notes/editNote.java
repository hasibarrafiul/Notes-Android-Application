package com.techrz.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

public class editNote extends AppCompatActivity {
    ImageButton back, save;
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
        courseID = findViewById(R.id.editcourseId);
        topic = findViewById(R.id.edittopic);
        date = findViewById(R.id.editdate);
        note = findViewById(R.id.editnote);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            noteID = extras.getInt("noteID");
        }
        back.setOnClickListener(v->finish());
        String notes[] = new String[4];
        notes = DB.getNote(noteID);
        courseID.setText(notes[0]);
        topic.setText(notes[1]);
        date.setText(notes[2]);
        note.setText(notes[3]);
    }
}
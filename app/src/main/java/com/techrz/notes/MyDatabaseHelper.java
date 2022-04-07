package com.techrz.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String Database_name= "Notes";
    private static final int Version= 1;


    private Context context;

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, Database_name, null, Version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL("Create TABLE notes (noteid INTEGER PRIMARY KEY AUTOINCREMENT,courseID varchar(50),topic varchar(50) UNIQUE,date varchar(20),note varchar(5000));");
            System.out.println("Table Created");
        }
        catch (Exception e){
            Toast.makeText(context,"Error: "+e,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE notes;");
        onCreate(db);
    }
    public Boolean inserNotes(String courseID, String topic, String date, String note){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("courseID", courseID);
        contentValues.put("topic", topic);
        contentValues.put("date", date);
        contentValues.put("note", note);
        long result = DB.insert("notes",null,contentValues);
        if(result==-1) return false;
        else return true;
    }

    public ArrayList<allNotes> getProblems(){
        ArrayList<allNotes> arrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor getProblem = sqLiteDatabase.rawQuery("SELECT noteid, courseID, topic, date, note FROM notes;",null);
        while(getProblem.moveToNext()){
            int noteid = getProblem.getInt(0);
            String courseID = getProblem.getString(1);
            String topic = getProblem.getString(2);
            String date = getProblem.getString(3);
            String note = getProblem.getString(4);

            allNotes allNotes = new allNotes(noteid, courseID, topic, date, note);
            arrayList.add(allNotes);
        }
        return arrayList;
    }
    public String[] getNote(int noteID){
        String noteArray[] = new String[4];
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor getNote = sqLiteDatabase.rawQuery("SELECT courseID, topic, date, note FROM notes WHERE noteid="+noteID+";",null);
        while(getNote.moveToNext()) {
            noteArray[0] = getNote.getString(0);
            noteArray[1] = getNote.getString(1);
            noteArray[2] = getNote.getString(2);
            noteArray[3] = getNote.getString(3);
        }
        return noteArray;
    }
    public Boolean updateNote(int noteID, String courseID, String topic, String date, String note){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("courseID", courseID);
        contentValues.put("topic", topic);
        contentValues.put("date", date);
        contentValues.put("note", note);

        long result = DB.update("notes",contentValues, "noteid = ?", new String[]{String.valueOf(noteID)});
        if(result==-1) return false;
        else return true;
    }

    public Boolean deleteNote(int noteID){
        SQLiteDatabase DB = this.getWritableDatabase();
        long result = DB.delete("notes", "noteid = ?", new String[]{String.valueOf(noteID)});
        if(result==-1) return false;
        else return true;
    }

}
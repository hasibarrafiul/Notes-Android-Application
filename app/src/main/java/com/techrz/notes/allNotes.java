package com.techrz.notes;

public class allNotes {
    int noteid;
    String courseID, topic, date, note;

    public allNotes(int id, String title , String name, String helptype, String postdetail) {
        this.noteid = id;
        this.courseID = title;
        this.topic = name;
        this.date = helptype;
        this.note = postdetail;
    }
    public allNotes(){}

    public int getID() {
        return noteid;
    }

    public void setID(int noteid) {
        this.noteid = noteid;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

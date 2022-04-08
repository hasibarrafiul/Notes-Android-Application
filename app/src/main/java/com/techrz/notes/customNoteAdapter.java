package com.techrz.notes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class customNoteAdapter extends BaseAdapter {

    Context context;
    ArrayList<allNotes> arrayList;
    public customNoteAdapter(Context context, ArrayList<allNotes> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }
    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.notescustomview, parent, false);

        TextView title = rowView.findViewById(R.id.courseIDShow);
        TextView post_type = rowView.findViewById(R.id.topicShow);
        TextView date_show = rowView.findViewById(R.id.dateShow);
        ImageButton edit = rowView.findViewById(R.id.editOrDelete);

        allNotes allNotes = arrayList.get(position);

        title.setText(allNotes.getCourseID());
        post_type.setText(allNotes.getTopic());
        date_show.setText(allNotes.getDate());
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = allNotes.getID();
                Intent intent = new Intent(context, editNote.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("noteID", id);
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}

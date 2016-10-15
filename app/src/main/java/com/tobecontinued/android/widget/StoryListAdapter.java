package com.tobecontinued.android.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.tobecontinued.android.R;
import com.tobecontinued.android.Session;
import com.tobecontinued.android.model.Story;

import java.util.ArrayList;
import java.util.List;

public class StoryListAdapter extends ArrayAdapter<Story> {
    private Session session;

    public StoryListAdapter(Context context) {
        this(context, new ArrayList<Story>());
    }

    public StoryListAdapter(Context context, List<Story> stories) {
        super(context, R.layout.listitem_story, stories);
        session = Session.getInstance(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            // Inflate the layout for the list item if necessary
            convertView = inflater.inflate(R.layout.listitem_story, parent, false);
        }

        // TODO: Configure list item view

        return convertView;
    }
}

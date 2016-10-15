package com.tobecontinued.android.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.tobecontinued.android.R;
import com.tobecontinued.android.Session;
import com.tobecontinued.android.model.Snippet;
import com.tobecontinued.android.model.Story;
import com.tobecontinued.android.model.User;

public class StoryListAdapter extends ArrayAdapter<Story> {
    private Session session;

    public StoryListAdapter(Context context, int resource) {
        super(context, resource);
        session = Session.getInstance(context);
    }

    @NonNull
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

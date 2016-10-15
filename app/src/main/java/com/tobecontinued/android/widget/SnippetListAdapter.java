package com.tobecontinued.android.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tobecontinued.android.R;
import com.tobecontinued.android.Session;
import com.tobecontinued.android.model.Snippet;


import java.util.ArrayList;
import java.util.List;

public class SnippetListAdapter extends ArrayAdapter<Snippet> {
    private Session session;

    public SnippetListAdapter(Context context) {
        this(context, new ArrayList<Snippet>());
    }

    public SnippetListAdapter(Context context, List<Snippet> stories) {
        super(context, R.layout.listitem_story, stories);
        session = Session.getInstance(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            // Inflate the layout for the list item if necessary
            convertView = inflater.inflate(R.layout.listitem_snippet, parent, false);
        }

        Snippet snippet = getItem(position);

        TextView snippettext = (TextView) convertView.findViewById(R.id.story3);
        snippettext.setText(snippet.getText());

        return convertView;
    }
}

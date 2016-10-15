package com.tobecontinued.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tobecontinued.android.model.Story;
import com.tobecontinued.android.widget.StoryListAdapter;

import java.util.List;

import java8.util.function.Consumer;
import java8.util.function.Function;

public class DashboardActivity extends Activity {
    private Session session;

    private ListView storyListView;
    private StoryListAdapter storyListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        session = Session.getInstance(getApplicationContext());

        setContentView(R.layout.activity_dashboard);

        storyListView = (ListView) findViewById(R.id.list_stories);
        storyListAdapter = new StoryListAdapter(this);
        storyListView.setAdapter(storyListAdapter);
        storyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), StoryViewActivity.class);
                intent.putExtra(StoryViewActivity.ARG_STORY_ID,
                        storyListAdapter.getItem(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        onRefresh();
    }

    private void onRefresh() {
        session.getTbcDAO().getAllStories()
                .thenAccept(new Consumer<List<Story>>() {
                    @Override
                    public void accept(List<Story> stories) {
                        storyListAdapter.clear();
                        storyListAdapter.addAll(stories);
                        storyListAdapter.notifyDataSetChanged();
                    }
                })
                .exceptionally(new Function<Throwable, Void>() {
                    @Override
                    public Void apply(Throwable throwable) {
                        throwable.printStackTrace();
                        return null;
                    }
                });
    }
}

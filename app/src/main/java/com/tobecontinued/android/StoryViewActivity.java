package com.tobecontinued.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.tobecontinued.android.model.Story;

import java8.util.function.Consumer;
import java8.util.function.Function;

public class StoryViewActivity extends Activity {
    public static final String ARG_STORY_ID = "ARG_STORY_ID";

    private Session session;
    private String storyId;
    private Story story;

    private ListView snippetListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        session = Session.getInstance(getApplicationContext());

        storyId = getIntent().getStringExtra(ARG_STORY_ID);

        setContentView(R.layout.activity_story_view);

        snippetListView = (ListView) findViewById(R.id.list_snippets);
    }

    @Override
    protected void onResume() {
        super.onResume();
        onRefresh();
    }

    private void onRefresh() {
        session.getTbcDAO().getStory(storyId)
                .thenAccept(new Consumer<Story>() {
                    @Override
                    public void accept(Story story) {
                        setTitle(story.getTitle());
                        // TODO: Set snippet list adapter
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

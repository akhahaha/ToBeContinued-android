package com.tobecontinued.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.tobecontinued.android.model.Snippet;
import com.tobecontinued.android.model.Story;
import com.tobecontinued.android.widget.SnippetListAdapter;

import java8.util.concurrent.CompletionStage;
import java8.util.function.Consumer;
import java8.util.function.Function;

public class StoryViewActivity extends Activity {
    public static final String ARG_STORY_ID = "ARG_STORY_ID";
    public static final int REQUEST_CREATE_SNIPPET = 0;

    private Session session;
    private String storyId;
    private Story story;
    private Snippet tailSnippet;

    private ListView snippetListView;
    private SnippetListAdapter snippetListAdapter;
    private Button addSnippetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        session = Session.getInstance(getApplicationContext());

        storyId = getIntent().getStringExtra(ARG_STORY_ID);

        setContentView(R.layout.activity_story_view);

        snippetListView = (ListView) findViewById(R.id.list_snippets);
        snippetListAdapter = new SnippetListAdapter(getApplicationContext());
        snippetListView.setAdapter(snippetListAdapter);

        addSnippetButton = (Button) findViewById(R.id.button_add_snippet);
    }

    @Override
    protected void onResume() {
        super.onResume();
        onRefresh();
    }

    private void onRefresh() {
        snippetListAdapter.clear();
        session.getTbcDAO().getStory(storyId)
                .thenCompose(new Function<Story, CompletionStage<Snippet>>() {
                    @Override
                    public CompletionStage<Snippet> apply(Story story) {
                        setTitle(story.getTitle());
                        return session.getTbcDAO().getSnippet(story.getRootSnippet().getId());
                    }
                })
                .thenAccept(new Consumer<Snippet>() {
                    @Override
                    public void accept(Snippet rootSnippet) {
                        loadSnippets(rootSnippet);
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

    private void loadSnippets(Snippet snippet) {
        snippetListAdapter.add(snippet);
        snippetListAdapter.notifyDataSetChanged();
        if (snippet.getChild() != null) {
            session.getTbcDAO().getSnippet(snippet.getChild().getId())
                    .thenAccept(new Consumer<Snippet>() {
                        @Override
                        public void accept(Snippet childSnippet) {
                            loadSnippets(childSnippet);
                        }
                    })
                    .exceptionally(new Function<Throwable, Void>() {
                        @Override
                        public Void apply(Throwable throwable) {
                            throwable.printStackTrace();
                            return null;
                        }
                    });
        } else {
            tailSnippet = snippet;
            addSnippetButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), SnippetFormActivity.class);
                    intent.putExtra(SnippetFormActivity.ARG_PARENT_SNIPPET_ID, tailSnippet.getId());
                    startActivityForResult(intent, REQUEST_CREATE_SNIPPET);
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CREATE_SNIPPET:
                break;
        }
    }
}

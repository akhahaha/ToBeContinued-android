package com.tobecontinued.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tobecontinued.android.model.Snippet;
import com.tobecontinued.android.model.Story;
import com.tobecontinued.android.model.User;

import java8.util.concurrent.CompletionStage;
import java8.util.function.Consumer;
import java8.util.function.Function;

public class StoryFormActivity extends AppCompatActivity {
    private Session session;

    private EditText titleField;
    private EditText snippetField;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        session = Session.getInstance(getApplicationContext());

        setContentView(R.layout.activity_story_form);

        titleField = (EditText) findViewById(R.id.title);
        snippetField = (EditText) findViewById(R.id.editText11);
        submitButton = (Button) findViewById(R.id.button2);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User currUser = session.getCurrentUser();
                Story story = Story.newInstance(currUser, titleField.getText().toString());
                final Snippet snippet = Snippet.newRootInstance(currUser, story,
                        snippetField.getText().toString());
                session.getTbcDAO().pushStory(story)
                        .thenCompose(new Function<Story, CompletionStage<Snippet>>() {
                            @Override
                            public CompletionStage<Snippet> apply(Story story) {
                                return session.getTbcDAO().pushSnippet(snippet);
                            }
                        })
                        .thenAccept(new Consumer<Snippet>() {
                            @Override
                            public void accept(Snippet snippet) {
                                finish();
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
        });
    }
}

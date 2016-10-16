package com.tobecontinued.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.tobecontinued.android.model.Snippet;
import com.tobecontinued.android.model.Story;
import com.tobecontinued.android.model.User;

import java8.util.concurrent.CompletionStage;
import java8.util.function.Consumer;
import java8.util.function.Function;


public class StoryFormActivity extends AppCompatActivity {
    public static final int REQUEST_ASR_TITLE = 0;
    public static final int REQUEST_ASR_SNIPPET = 1;

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
                final Story story = Story.newInstance(currUser, titleField.getText().toString());
                final Snippet snippet = Snippet.newRootInstance(currUser, story,
                        snippetField.getText().toString());
                session.getTbcDAO().pushStory(story)
                        .thenCompose(new Function<Story, CompletionStage<Snippet>>() {
                            @Override
                            public CompletionStage<Snippet> apply(Story story) {
                                return session.getTbcDAO().pushSnippet(snippet);
                            }
                        })
                        .thenCompose(new Function<Snippet, CompletionStage<Story>>() {
                            @Override
                            public CompletionStage<Story> apply(Snippet snippet) {
                                story.setRootSnippet(snippet);
                                return session.getTbcDAO().updateStory(story);
                            }
                        })
                        .thenAccept(new Consumer<Story>() {
                            @Override
                            public void accept(Story story) {
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

        ImageButton btn1 = (ImageButton) findViewById(R.id.micBtn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getApplicationContext(), ASRActivity.class),
                        REQUEST_ASR_TITLE);
            }
        });

        ImageButton btn2 = (ImageButton) findViewById(R.id.micBtn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getApplicationContext(), ASRActivity.class),
                        REQUEST_ASR_SNIPPET);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case REQUEST_ASR_TITLE:
                titleField.setText(data.getStringExtra("RESULT"));
                break;
            case REQUEST_ASR_SNIPPET:
                snippetField.setText(data.getStringExtra("RESULT"));
                break;
        }
    }
}

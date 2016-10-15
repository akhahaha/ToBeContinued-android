package com.tobecontinued.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tobecontinued.android.model.Snippet;

import java8.util.concurrent.CompletionStage;
import java8.util.function.Consumer;
import java8.util.function.Function;

public class SnippetFormActivity extends AppCompatActivity {
    public static final String ARG_PARENT_SNIPPET_ID = "ARG_PARENT_SNIPPET_ID";

    private Session session;

    private String parentSnippetId;

    private EditText snippetField;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        session = Session.getInstance(getApplicationContext());

        parentSnippetId = getIntent().getStringExtra(ARG_PARENT_SNIPPET_ID);

        setContentView(R.layout.activity_snippet_form);

        snippetField = (EditText) findViewById(R.id.field_snippet);
        submitButton = (Button) findViewById(R.id.button_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.getTbcDAO().getSnippet(parentSnippetId)
                        .thenCompose(new Function<Snippet, CompletionStage<Snippet>>() {
                            @Override
                            public CompletionStage<Snippet> apply(Snippet parentSnippet) {
                                Snippet childSnippet = Snippet.newChildInstance(
                                        session.getCurrentUser(),
                                        snippetField.getText().toString(),
                                        parentSnippet);
                                return session.getTbcDAO().pushSnippet(childSnippet);
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

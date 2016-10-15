package com.tobecontinued.android.model;

import firebomb.annotation.Entity;
import firebomb.annotation.Id;
import firebomb.annotation.ManyToOne;
import firebomb.annotation.OneToOne;

@Entity
public class Snippet {
    private String id;
    private User author;
    private Story rootStory;
    private String text;
    private Snippet parent;
    private Snippet child;

    public static Snippet newRootInstance(User author, Story story, String text) {
        return new Snippet(author, story, text, null);
    }

    public static Snippet newChildInstance(User author, String text, Snippet parent) {
        return new Snippet(author, null, text, parent);
    }

    public Snippet() {
        // Required default constructor
    }

    private Snippet(User author, Story rootStory, String text, Snippet parent) {
        this.author = author;
        this.rootStory = rootStory;
        this.text = text;
        this.parent = parent;
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne(foreignIndexName = "snippets")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @OneToOne(foreignFieldName = "rootSnippet")
    public Story getRootStory() {
        return rootStory;
    }

    public void setRootStory(Story rootStory) {
        this.rootStory = rootStory;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @OneToOne(foreignFieldName = "child")
    public Snippet getParent() {
        return parent;
    }

    public void setParent(Snippet parent) {
        this.parent = parent;
    }

    @OneToOne(foreignFieldName = "parent")
    public Snippet getChild() {
        return child;
    }

    public void setChild(Snippet child) {
        this.child = child;
    }
}

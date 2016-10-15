package com.tobecontinued.android.model;

import java.util.List;

import firebomb.annotation.Entity;
import firebomb.annotation.Id;
import firebomb.annotation.ManyToOne;
import firebomb.annotation.OneToMany;

@Entity
public class Snippet {
    private String id;
    private User author;
    private Story rootStory;
    private Snippet parent;
    private String text;
    private Story story;
    private List<Snippet> children;

    public Snippet() {
        // Required default constructor
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

    @OneToMany(foreignFieldName = "rootSnippet")
    public Story getRootStory() {
        return rootStory;
    }

    public void setRootStory(Story rootStory) {
        this.rootStory = rootStory;
    }

    @ManyToOne(foreignIndexName = "children")
    public Snippet getParent() {
        return parent;
    }

    public void setParent(Snippet parent) {
        this.parent = parent;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @ManyToOne(foreignIndexName = "snippets")
    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    @OneToMany(foreignFieldName = "parent")
    public List<Snippet> getChildren() {
        return children;
    }

    public void setChildren(List<Snippet> children) {
        this.children = children;
    }
}

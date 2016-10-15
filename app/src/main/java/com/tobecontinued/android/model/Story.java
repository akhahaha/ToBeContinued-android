package com.tobecontinued.android.model;

import firebomb.annotation.Entity;
import firebomb.annotation.Id;
import firebomb.annotation.ManyToOne;
import firebomb.annotation.OneToOne;

@Entity
public class Story {
    private String id;
    private User owner;
    private String title;
    private Snippet rootSnippet;

    public Story newInstance(User owner, String title) {
        return new Story(owner, title);
    }

    public Story() {
        // Required default constructor
    }

    private Story(User owner, String title) {
        this.owner = owner;
        this.title = title;
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne(foreignIndexName = "stories")
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @OneToOne(foreignFieldName = "rootStory")
    public Snippet getRootSnippet() {
        return rootSnippet;
    }

    public void setRootSnippet(Snippet rootSnippet) {
        this.rootSnippet = rootSnippet;
    }
}

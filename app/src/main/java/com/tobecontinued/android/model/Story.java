package com.tobecontinued.android.model;

import java.util.List;

import firebomb.annotation.Entity;
import firebomb.annotation.Id;
import firebomb.annotation.ManyToOne;
import firebomb.annotation.OneToMany;

@Entity
public class Story {
    private String id;
    private User owner;
    private String title;
    private Snippet rootSnippet;
    private List<Snippet> snippets;

    public Story() {
        // Required default constructor
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

    @OneToMany(foreignFieldName = "rootStory")
    public Snippet getRootSnippet() {
        return rootSnippet;
    }

    public void setRootSnippet(Snippet rootSnippet) {
        this.rootSnippet = rootSnippet;
    }

    @OneToMany(foreignFieldName = "story")
    public List<Snippet> getSnippets() {
        return snippets;
    }

    public void setSnippets(List<Snippet> snippets) {
        this.snippets = snippets;
    }
}

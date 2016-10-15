package com.tobecontinued.android.model;

import java.util.ArrayList;
import java.util.List;

import firebomb.annotation.Entity;
import firebomb.annotation.Id;
import firebomb.annotation.OneToMany;

@Entity
public class User {
    private String id;
    private List<Story> stories = new ArrayList<>();
    private List<Snippet> snippets = new ArrayList<>();

    public static User newInstance(String id) {
        return new User(id);
    }

    public User() {
        // Required default empty constructor
    }

    private User(String id) {
        this.id = id;
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @OneToMany(foreignFieldName = "owner")
    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    @OneToMany(foreignFieldName = "owner")
    public List<Snippet> getSnippets() {
        return snippets;
    }

    public void setSnippets(List<Snippet> snippets) {
        this.snippets = snippets;
    }
}

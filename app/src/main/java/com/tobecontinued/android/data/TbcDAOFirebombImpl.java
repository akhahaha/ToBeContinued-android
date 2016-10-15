package com.tobecontinued.android.data;

import com.tobecontinued.android.model.Snippet;
import com.tobecontinued.android.model.Story;
import com.tobecontinued.android.model.User;

import java.util.List;

import firebomb.Firebomb;
import firebomb.criteria.Criteria;
import java8.util.concurrent.CompletableFuture;

public class TbcDAOFirebombImpl implements TbcDAO {
    private Firebomb firebomb;

    public TbcDAOFirebombImpl(Firebomb firebomb) {
        this.firebomb = firebomb;
    }

    @Override
    public CompletableFuture<User> createUser(User user) {
        return firebomb.persist(user);
    }

    @Override
    public CompletableFuture<User> updateUser(User user) {
        // TODO: Verify user exists
        return firebomb.persist(user);
    }

    @Override
    public CompletableFuture<User> getUser(String userId) {
        return firebomb.find(User.class, userId);
    }

    @Override
    public CompletableFuture<Void> deleteUser(String userId) {
        return firebomb.remove(User.class, userId);
    }

    @Override
    public CompletableFuture<Story> pushStory(Story story) {
        story.setId(null);
        return firebomb.persist(story);
    }

    @Override
    public CompletableFuture<Story> updateStory(Story story) {
        // TODO: Verify story exists
        return firebomb.persist(story);
    }

    @Override
    public CompletableFuture<Story> getStory(String storyId) {
        return firebomb.find(Story.class, storyId);
    }

    @Override
    public CompletableFuture<List<Story>> getAllStories() {
        return firebomb.query(new Criteria<>(Story.class));
    }

    @Override
    public CompletableFuture<Void> deleteStory(String storyId) {
        return firebomb.remove(Story.class, storyId);
    }

    @Override
    public CompletableFuture<Snippet> pushSnippet(Snippet snippet) {
        snippet.setId(null);
        return firebomb.persist(snippet);
    }

    @Override
    public CompletableFuture<Snippet> updateSnippet(Snippet snippet) {
        // TODO: Verify snippet exists
        return firebomb.persist(snippet);
    }

    @Override
    public CompletableFuture<Snippet> getSnippet(String snippetId) {
        return firebomb.find(Snippet.class, snippetId);
    }

    @Override
    public CompletableFuture<Void> deleteSnippet(String snippetId) {
        return firebomb.remove(Snippet.class, snippetId);
    }
}

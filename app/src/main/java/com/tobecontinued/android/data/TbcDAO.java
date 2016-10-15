package com.tobecontinued.android.data;

import com.tobecontinued.android.model.Snippet;
import com.tobecontinued.android.model.Story;
import com.tobecontinued.android.model.User;

import java.util.List;

import java8.util.concurrent.CompletableFuture;

public interface TbcDAO {
    CompletableFuture<User> createUser(User user);

    CompletableFuture<User> updateUser(User user);

    CompletableFuture<User> getUser(String userId);

    CompletableFuture<Void> deleteUser(String userId);

    CompletableFuture<Story> pushStory(Story story);

    CompletableFuture<Story> updateStory(Story story);

    CompletableFuture<Story> getStory(String storyId);

    CompletableFuture<List<Story>> getAllStories();

    CompletableFuture<Void> deleteStory(String storyId);

    CompletableFuture<Snippet> pushSnippet(Snippet snippet);

    CompletableFuture<Snippet> updateSnippet(Snippet snippet);

    CompletableFuture<Snippet> getSnippet(String snippetId);

    CompletableFuture<Void> deleteSnippet(String snippetId);
}

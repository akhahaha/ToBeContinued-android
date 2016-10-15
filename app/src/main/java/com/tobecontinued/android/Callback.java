package com.tobecontinued.android;

public interface Callback<T> {
    void onResponse(T result);

    void onFailed(Throwable t);
}

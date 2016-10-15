package com.tobecontinued.android;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.database.FirebaseDatabase;
import com.tobecontinued.android.data.TbcDAO;
import com.tobecontinued.android.data.TbcDAOFirebombImpl;
import com.tobecontinued.android.model.User;

import firebomb.Firebomb;
import firebomb.database.FirebaseManager;
import java8.util.function.Consumer;
import java8.util.function.Function;

public class Session {
    private static Session ourInstance;

    public static Session getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new Session(context);
        }

        return ourInstance;
    }

    private SharedPreferences preferences;
    private User currentUser;
    private TbcDAO tbcDAO;

    private Session(Context context) {
        Firebomb.initialize(new FirebaseManager(FirebaseDatabase.getInstance()), "v1");
        tbcDAO = new TbcDAOFirebombImpl(Firebomb.getInstance());
        restoreSession(context);
    }

    public void saveSession(Context context) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        if (currentUser != null) {
            editor.putString("PREF_KEY_CURR_UID", currentUser.getId()).apply();
        } else {
            editor.remove("PREF_KEY_CURR_UID");
        }
    }

    public void restoreSession(Context context, final Callback callback) {
        String currentUserID = getPreferences(context).getString(("PREF_KEY_CURR_UID"), null);
        if (currentUserID == null) {
            if (callback != null) callback.onResponse(false);
            return;
        }

        tbcDAO.getUser(currentUserID).thenAccept(new Consumer<User>() {
            @Override
            public void accept(User user) {
                if (user != null) {
                    currentUser = user;
                    if (callback != null) callback.onResponse(true);
                } else if (callback != null) {
                    callback.onResponse(false);
                }
            }
        }).exceptionally(new Function<Throwable, Void>() {
            @Override
            public Void apply(Throwable throwable) {
                if (callback != null) callback.onFailed(throwable);
                return  null;
            }
        });
    }

    public void restoreSession(Context context) {
        restoreSession(context, null);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Context context, User currentUser) {
        this.currentUser = currentUser;
        saveSession(context);
    }

    public Boolean isActive() {
        return currentUser != null;
    }

    public TbcDAO getTbcDAO() {
        return tbcDAO;
    }

    public void setTbcDAO(TbcDAO tbcDAO) {
        this.tbcDAO = tbcDAO;
    }

    private SharedPreferences getPreferences(Context context) {
        if (preferences == null) {
            preferences = context.getSharedPreferences(("PREF_FILE_NAME"), Context.MODE_PRIVATE);
        }

        return preferences;
    }
}

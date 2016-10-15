package com.tobecontinued.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.FirebaseDatabase;
import com.tobecontinued.android.data.DAOManager;
import com.tobecontinued.android.data.TbcDAOFirebombImpl;

import firebomb.Firebomb;
import firebomb.database.FirebaseManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Initialize app here
        Firebomb.initialize(new FirebaseManager(FirebaseDatabase.getInstance()));
        DAOManager.setTbcDAOInstance(new TbcDAOFirebombImpl(Firebomb.getInstance()));
    }
}

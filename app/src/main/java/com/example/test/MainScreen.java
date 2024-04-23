package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }
    public void onClickProfile(View view) {
        startActivity(new Intent(this, Profile.class));
    }
    public void onClickFavor(View view) {
        startActivity(new Intent(this, Favourites.class));
    }
}
package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Favourites extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
    }
    public void onClickMain(View view) {
        startActivity(new Intent(this, MainScreen.class));
    }
    public void onClickiProfile(View view) { startActivity(new Intent(this, Profile.class)); }
}
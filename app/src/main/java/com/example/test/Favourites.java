package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Favourites extends AppCompatActivity {
    private String userName = "";
    private String userFamilia = "";
    private String userOtchestvo = "";
    private String userMail = "";
    private String userUrl = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        if (savedInstanceState != null) {
            userName = savedInstanceState.getString("userName");
            userFamilia = savedInstanceState.getString("userFamilia");
            userOtchestvo = savedInstanceState.getString("userOtchestvo");
            userMail = savedInstanceState.getString("userMail");
            userUrl = savedInstanceState.getString("userUrl");
        } else {
            // Получение данных из Intent (если активность создается впервые)
            Intent intent = getIntent();
            userName = intent.getStringExtra("userName");
            userFamilia = intent.getStringExtra("userFamilia");
            userOtchestvo = intent.getStringExtra("userOtchestvo");
            userMail = intent.getStringExtra("userMail");
            userUrl = intent.getStringExtra("userUrl");
        }
    }
    public void onClickMain(View view) {
        Intent intent = new Intent(this, MainScreen.class);
        intent.putExtra("userName", userName);
        intent.putExtra("userFamilia", userFamilia);
        intent.putExtra("userOtchestvo", userOtchestvo);
        intent.putExtra("userMail", userMail);
        intent.putExtra("userUrl", userUrl);
        startActivity(intent);
    }
    public void onClickiProfile(View view) {
        Intent intent = new Intent(this, Profile.class);
        intent.putExtra("userName", userName);
        intent.putExtra("userFamilia", userFamilia);
        intent.putExtra("userOtchestvo", userOtchestvo);
        intent.putExtra("userMail", userMail);
        intent.putExtra("userUrl", userUrl);
        startActivity(intent);
    }
}
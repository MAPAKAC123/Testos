package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class PasswordRecovery extends AppCompatActivity {
    private EditText emailEditText;
    private ImageButton sendSmsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);

        init();
    }
    public void init()
    {
        emailEditText = findViewById(R.id.editTextText);
    }
    public void onClickBack(View view) { startActivity(new Intent(this, LoginAccount.class));}
    public void onClickVost(View view)
    {
        String email = emailEditText.getText().toString();
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
        smsIntent.setData(Uri.parse("smsto:" + email));
        smsIntent.putExtra("sms_body", "Привет! Отправлено через SMS");

        if (ContextCompat.checkSelfPermission(PasswordRecovery.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            startActivity(smsIntent);
        } else {
            ActivityCompat.requestPermissions(PasswordRecovery.this, new String[]{Manifest.permission.SEND_SMS}, 1);
        }
    }
}
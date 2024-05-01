package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {
    private EditText edName, edFamilia, edOtchestvo, edMail, edLogin, edPassword, edPPassword;
    private DatabaseReference mDataBase;
    private String USER_KEY = "User";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        init();
    }

    private void init() {
        edName = findViewById(R.id.edName);
        edFamilia = findViewById(R.id.edFamilia);
        edOtchestvo = findViewById(R.id.edOtchestvo);
        edMail = findViewById(R.id.edMail);
        edLogin = findViewById(R.id.edLogin);
        edPassword = findViewById(R.id.edPassword);
        edPPassword = findViewById(R.id.edPPassword);
        mDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);
    }

    public void onClickBack(View view) {
        startActivity(new Intent(this, HomeScreen.class));
    }

    public void onClickAcc(View view) {
        startActivity(new Intent(this, LoginAccount.class));
    }

    public void onClickSave(View view) {
        String id = mDataBase.getKey();
        String name = edName.getText().toString();
        String familia = edFamilia.getText().toString();
        String otchestvo = edOtchestvo.getText().toString();
        String mail = edMail.getText().toString();
        String login = edLogin.getText().toString().toLowerCase();
        String password = edPassword.getText().toString().toLowerCase();
        String ppassword = edPPassword.getText().toString().toLowerCase();
        if (name.isEmpty() || familia.isEmpty() || otchestvo.isEmpty() || mail.isEmpty() || login.isEmpty() || password.isEmpty() || ppassword.isEmpty()) {
            // Одно из полей пустое
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
        } else {
            if (mail.contains("@gmail.com") || mail.contains("@mail.com") || mail.contains("@mail.ru")) {
                if (password.length() >= 8 && password.equals(ppassword)) {
                    User newUser = new User(id, name, familia, otchestvo, mail, login, password);
                    mDataBase.push().setValue(newUser);
                    startActivity(new Intent(this, MainScreen.class));
                    Toast.makeText(this, "Вы успешно прошли регистрацию", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Пароль должен содержать 8 символов или больше. Также пароль нужно подтвердить", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Неверно введена электронная почта", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
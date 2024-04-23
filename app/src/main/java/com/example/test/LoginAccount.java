package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginAccount extends AppCompatActivity {
    private EditText editTextText, editTextTextPassword3;
    private DatabaseReference mDataBase;
    private String USER_KEY = "User";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_account);
        init();
    }

    private void init() {
        editTextText = findViewById(R.id.editTextText);
        editTextTextPassword3 = findViewById(R.id.editTextTextPassword3);
    }

    public void onClickRegist(View view) {
        startActivity(new Intent(this, Registration.class));
    }

    public void onClickBack(View view) {
        startActivity(new Intent(this, HomeScreen.class));
    }
    public void onClickRecovery(View view) {
        startActivity(new Intent(this, PasswordRecovery.class));
    }

    public void onClickLogin(View view)
    {
        String login = editTextText.getText().toString();
        String password = editTextTextPassword3.getText().toString();
        mDataBase = FirebaseDatabase.getInstance().getReference("User");
        Query query = mDataBase.orderByChild("login").equalTo(login);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        User user = userSnapshot.getValue(User.class);
                        if (user.getPassword().equals(password)) {
                            // Логин и пароль совпадают, переходи на экран HomeScreen
                            startActivity(new Intent(LoginAccount.this, MainScreen.class));
                        } else {
                            // Неправильный пароль
                            Toast.makeText(LoginAccount.this, "Неправильный пароль", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    // Пользователь с таким логином не найден
                    Toast.makeText(LoginAccount.this, "Пользователь с таким логином не найден" + login, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Firebase", "Ошибка: ", databaseError.toException());
                Toast.makeText(LoginAccount.this, "Произошла ошибка, попробуйте еще раз", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;

public class Profile extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST_CODE = 1;
    private Uri selectedImageUri;
    private ImageView avatarImageView;
    private String userName, userFamilia, userOtchestvo, userMail, userUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        avatarImageView = findViewById(R.id.imageButton4);

        // Восстановление значений из сохраненного состояния
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

        // Обновление пользовательского интерфейса с восстановленными/полученными значениями
        TextView nameText = findViewById(R.id.nameText);
        TextView familiaText = findViewById(R.id.familiaText);
        TextView otchestvoText = findViewById(R.id.otchestvoText);
        TextView mailUser = findViewById(R.id.mailText);

        nameText.setText("Имя: " + userName);
        familiaText.setText("Фамилия: " + userFamilia);
        otchestvoText.setText("Отчество: " + userOtchestvo);
        mailUser.setText("Почта: " + userMail);

        Glide.with(getApplicationContext()).load(userUrl).into(avatarImageView);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("userName", userName);
        outState.putString("userFamilia", userFamilia);
        outState.putString("userOtchestvo", userOtchestvo);
        outState.putString("userMail", userMail);
        outState.putString("userUrl", userUrl);
    }
    public void onClickiMain(View view)
    {
        Intent intent = new Intent(this, MainScreen.class);
        intent.putExtra("userName", userName);
        intent.putExtra("userFamilia", userFamilia);
        intent.putExtra("userOtchestvo", userOtchestvo);
        intent.putExtra("userMail", userMail);
        intent.putExtra("userUrl", userUrl);
        startActivity(intent);
    }
    public void onClickFavor(View view) {
        Intent intent = new Intent(this, Favourites.class);
        intent.putExtra("userName", userName);
        intent.putExtra("userFamilia", userFamilia);
        intent.putExtra("userOtchestvo", userOtchestvo);
        intent.putExtra("userMail", userMail);
        intent.putExtra("userUrl", userUrl);
        startActivity(intent);
    }
    public void onClickNewCar(View view) {
        Intent intent = new Intent(this, NewCar.class);
        intent.putExtra("userName", userName);
        intent.putExtra("userFamilia", userFamilia);
        intent.putExtra("userOtchestvo", userOtchestvo);
        intent.putExtra("userMail", userMail);
        intent.putExtra("userUrl", userUrl);
        startActivity(intent);
    }
    public void onClickPhoto(View view) {
        Intent pickImageIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImageIntent, PICK_IMAGE_REQUEST_CODE);
    }

}

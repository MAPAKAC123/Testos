package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewCar extends AppCompatActivity {
    private EditText edMarka, edModel, edPokolenie, edTipKyzova, edPrivod, edGod, edKorobka, edPrice;
    private DatabaseReference mDataBase;
    private String USER_KEY = "Car";
    private String userName = "";
    private String userFamilia = "";
    private String userOtchestvo = "";
    private String userMail = "";
    private String userUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_car);

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

        init();
    }
    private void init() {
        edMarka = findViewById(R.id.edTextMarka);
        edModel = findViewById(R.id.edTextModel);
        edPokolenie = findViewById(R.id.edTextPokolenie);
        edTipKyzova = findViewById(R.id.edTextTipKyzova);
        edPrivod = findViewById(R.id.edTextPrivod);
        edGod = findViewById(R.id.edTextGod);
        edKorobka = findViewById(R.id.edTextKorobka);
        edPrice = findViewById(R.id.edTextPrice);
        mDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);
    }
    public void onClickBack(View view) {
        Intent intent = new Intent(this, Profile.class);
        intent.putExtra("userName", userName);
        intent.putExtra("userFamilia", userFamilia);
        intent.putExtra("userOtchestvo", userOtchestvo);
        intent.putExtra("userMail", userMail);
        intent.putExtra("userUrl", userUrl);
        startActivity(intent);
    }
    public void onClickSave(View view) {
        String id = mDataBase.getKey();
        String marka = edMarka.getText().toString();
        String model = edModel.getText().toString();
        String pokolenie = edPokolenie.getText().toString() + " Поколение";
        String tip_kyzova = edTipKyzova.getText().toString();
        String privod = edPrivod.getText().toString();
        String god = edGod.getText().toString() + " г.";
        String korobka = edKorobka.getText().toString();
        String price = edPrice.getText().toString() + " руб.";
        String url = null;
        if (marka.isEmpty() || model.isEmpty() || pokolenie.isEmpty() || tip_kyzova.isEmpty() || privod.isEmpty() || god.isEmpty() || korobka.isEmpty() || price.isEmpty()) {
            // Одно из полей пустое
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Car newCar = new Car(id, url, tip_kyzova, privod, korobka, god, price, marka, model, pokolenie);
            mDataBase.push().setValue(newCar);
            Toast.makeText(this, "Объявление успешно добавлено", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, Profile.class);
            intent.putExtra("userName", userName);
            intent.putExtra("userFamilia", userFamilia);
            intent.putExtra("userOtchestvo", userOtchestvo);
            intent.putExtra("userMail", userMail);
            intent.putExtra("userUrl", userUrl);
            startActivity(intent);
        }
    }
}
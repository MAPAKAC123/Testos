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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_car);
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
        startActivity(new Intent(this, Profile.class));
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
            startActivity(new Intent(this, Profile.class));
            Toast.makeText(this, "Объявление успешно добавлено", Toast.LENGTH_SHORT).show();
        }
    }
}
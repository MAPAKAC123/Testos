package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class CarDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);

        // Получаем данные из Intent
        Intent intent = getIntent();
        String carName = intent.getStringExtra("carName");
        String carPhoto = intent.getStringExtra("carPhoto");
        String carPrice = intent.getStringExtra("carPrice");
        String carDrive = intent.getStringExtra("carDrive");
        String carStamp = intent.getStringExtra("carStamp");
        String carYear = intent.getStringExtra("carYear");
        String carBody = intent.getStringExtra("carBody");
        String carTransmisson = intent.getStringExtra("carTransmisson");

        // Находим TextView по id
        TextView carNameTextView = findViewById(R.id.carNameTextView);
        TextView carPriceTextView = findViewById(R.id.carPriceTextView);
        TextView carStampTextView = findViewById(R.id.carStampTextView);
        TextView carDriveTextView = findViewById(R.id.carDriveTextView);
        TextView carYearTextView = findViewById(R.id.carYearTextView);
        TextView carBodyTextView = findViewById(R.id.carBodyTextView);
        TextView carTransmissonTextView = findViewById(R.id.carTransmissionTextView);
        ImageView Photo = findViewById(R.id.imageView9);
        Glide.with(getApplicationContext()).load(carPhoto).into(Photo);

        // Устанавливаем полученные данные в TextView
        carNameTextView.setText("Марка: " + carName);
        carPriceTextView.setText("Цена: " + carPrice);
        carStampTextView.setText("Модель: " + carStamp);
        carDriveTextView.setText("Привод: " + carDrive);
        carYearTextView.setText("Год выпуска: " + carYear);
        carBodyTextView.setText("Тип кузова: " + carBody);
        carTransmissonTextView.setText("Коробка передач: " + carTransmisson);

    }
    public void onClickBack(View view) {
        startActivity(new Intent(this, MainScreen.class));
    }
}
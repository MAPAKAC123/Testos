package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainScreen extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private GridView gridView;
    private CarAdapter carAdapter;
    private String userName = "";
    private String userFamilia = "";
    private String userOtchestvo = "";
    private String userMail = "";
    private String userUrl = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

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

        FirebaseApp.initializeApp(this);
        gridView = findViewById(R.id.Grid);

        // Создаём начальный список автомобилей, который будет отображаться до загрузки данных из Firebase
        List<Car> carsList = new ArrayList<>();

        // Создаём адаптер и устанавливаем его для GridView
        carAdapter = new CarAdapter(this, carsList);
        gridView.setAdapter(carAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Car");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Car> cars = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Car car = snapshot.getValue(Car.class);
                    cars.add(car);
                }
                // Обновляем адаптер новыми данными
                carAdapter.updateCars(cars);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Обработка ошибок
            }
        });

        // Установим обработчик нажатий на элементы GridView
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Car car = (Car) parent.getItemAtPosition(position);
                // Переходим на новую активность, передавая информацию о выбранном автомобиле
                Intent intent = new Intent(MainScreen.this, CarDetailsActivity.class);
                intent.putExtra("carName", car.getModel());
                intent.putExtra("carPhoto", car.getPhoto());
                intent.putExtra("carPrice", car.getPrice());
                intent.putExtra("carStamp", car.getStamp());
                intent.putExtra("carDrive", car.getDrive());
                intent.putExtra("carYear", car.getYear());
                intent.putExtra("carBody", car.getBody());
                intent.putExtra("carTransmisson", car.getTransmission());
                startActivity(intent);
            }
        });
    }


    public void onClickProfile(View view) {
        Intent intent = new Intent(this, Profile.class);
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
}

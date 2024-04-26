package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class CarAdapter extends BaseAdapter {
    private Context context;
    private List<Car> cars;

    public CarAdapter(Context context, List<Car> cars) {
        this.context = context;
        this.cars = cars;
    }

    @Override
    public int getCount() {
        return cars.size();
    }

    @Override
    public Object getItem(int position) {
        return cars.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item_car, null); // Предполагается, что у вас есть макет grid_item_car.xml для элемента GridView
        }

        // Получите ссылки на элементы макета
        TextView carNameTextView = view.findViewById(R.id.carNameTextView);
        TextView carModelTextView = view.findViewById(R.id.carModelTextView);
        TextView carPriceTextView = view.findViewById(R.id.carPriceTextView);

        // Получите информацию о машине для текущей позиции
        Car car = cars.get(position);

        // Установите информацию о машине в элементы макета
        carNameTextView.setText(car.getStamp());
        carModelTextView.setText(car.getModel());
        carPriceTextView.setText(car.getPrice());

        return view;
    }
    public void updateCars(List<Car> cars) {
        this.cars = cars;
        notifyDataSetChanged();
    }
}
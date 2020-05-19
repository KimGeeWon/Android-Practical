package com.example.foodrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.foodrecorder.data.FoodRecord;
import com.example.foodrecorder.data.FoodRecordDatabase;
import com.example.foodrecorder.data.FoodRecordOpenHelper;
import com.example.foodrecorder.databinding.ActivityMainBinding;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private SharedPreferences preferences;
    DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.KOREA);
    //private FoodRecordOpenHelper helper;
    private FoodRecordDatabase db;

    private void save(FoodRecord record) {
        new Thread(() -> db.foodRecordDAO().addRecord(record)).start();
    }

    private void getList() {
        new Thread(() -> {
            List<FoodRecord> result = db.foodRecordDAO().getRecords();
            for(FoodRecord e:result)
                Log.i("Main", e.getTime() + e.getFood());
        }).start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        helper = new FoodRecordOpenHelper(this, "db", null, 1);
        db = FoodRecordDatabase.getInstance(getApplicationContext());
        getList();
//        ArrayList<FoodRecord> list = helper.getRecords();
//        for(FoodRecord r: list) {
//            Log.i("Main", r.getFood() + r.getTime());
//        }

        preferences = getSharedPreferences("food", Context.MODE_PRIVATE);
        String lastFood = preferences.getString("food", null);
        String lastTime = preferences.getString("time", null);
        displayRecord(lastFood, lastTime);
        binding.buttonRecord.setOnClickListener(onSave);

        binding.buttonShowAll.setOnClickListener(v->{
            startActivity(new Intent(this, RecordActivity.class));
        });
    }

    private View.OnClickListener onSave = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            SharedPreferences.Editor editor = preferences.edit();
            String food = binding.editText.getText().toString();

            if(!food.isEmpty()) {
                editor.putString("food", food);
                String time = LocalDateTime.now().toString();
                editor.putString("time", time);
                editor.apply();
                //helper.addRecord(new FoodRecord(food, time.toString()));
                save(new FoodRecord(food, time.toString()));
            }
        }
    };

    private void displayRecord(String food, String time) {

        if(food == null) {
            binding.textViewRecord.setText("저장된 기록이 없습니다");
            binding.textViewElapsed.setText("경과 시간이 없습니다");
        }
        else {
            LocalDateTime startTime = LocalDateTime.parse(time);
            LocalDateTime endTime = LocalDateTime.now();
            String timeStr = startTime.format(formatter);
            String foodMessage = String.format("%s - %s", timeStr, food);
            binding.textViewRecord.setText(foodMessage);

            long hour = ChronoUnit.HOURS.between(startTime, endTime);
            long minute = ChronoUnit.MINUTES.between(startTime, endTime);
            minute -= hour * 60;

            Log.i("asdf", String.valueOf(hour));

            binding.textViewElapsed.setText(
                    String.format(Locale.KOREA, "%d시간 %02d분 경과", hour, minute));
        }
    }
}

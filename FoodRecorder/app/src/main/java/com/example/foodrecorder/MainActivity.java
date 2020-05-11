package com.example.foodrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.foodrecorder.databinding.ActivityMainBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private SharedPreferences preferences;
    DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.KOREA);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferences = getSharedPreferences("food", Context.MODE_PRIVATE);
        String lastFood = preferences.getString("food", null);
        String lastTime = preferences.getString("time", null);
        displayRecord(lastFood, lastTime);
        binding.buttonRecord.setOnClickListener(onSave);
    }

    private View.OnClickListener onSave = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            SharedPreferences.Editor editor = preferences.edit();
            String food = binding.editText.getText().toString();

            if(!food.isEmpty()) {
                LocalDateTime nowTime = LocalDateTime.now();
                String time = nowTime.format(formatter);

                binding.textViewRecord.setText(time + " - " + food);
                binding.textViewElapsed.setText("0시간 0분 경과");

                editor.putString("food", food);
                editor.putString("time", time);

                editor.apply();
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

            long hour = ChronoUnit.HOURS.between(startTime, endTime);
            long minute = ChronoUnit.MINUTES.between(startTime, endTime);
            minute -= hour * 60;

            binding.textViewRecord.setText(timeStr + " - " + food);
            binding.textViewElapsed.setText(
                    String.format(Locale.KOREA, "%d시간 %02d분 경과", hour, minute));
        }
    }
}

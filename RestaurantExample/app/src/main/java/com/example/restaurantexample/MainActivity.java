package com.example.restaurantexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.restaurantexample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initWidgets();
    }

    private void initWidgets() {
        binding.buttonMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                binding.scrollView.setVisibility(View.VISIBLE);
                binding.locateView.setVisibility(View.INVISIBLE);
                binding.eventView.setVisibility(View.INVISIBLE);
            }
        });

        binding.buttonLocate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                binding.scrollView.setVisibility(View.INVISIBLE);
                binding.locateView.setVisibility(View.VISIBLE);
                binding.eventView.setVisibility(View.INVISIBLE);
            }
        });

        binding.buttonEvent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                binding.scrollView.setVisibility(View.INVISIBLE);;
                binding.locateView.setVisibility(View.INVISIBLE);
                binding.eventView.setVisibility(View.VISIBLE);
            }
        });
    }
}

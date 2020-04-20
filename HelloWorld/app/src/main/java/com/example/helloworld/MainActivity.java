package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;
    private EditText editTextNum1, editTextNum2;
    private Button buttonAdd, buttonSub, buttonMul, buttonDiv;

    private View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            float num1=Float.parseFloat(editTextNum1.getText().toString());
            float num2=Float.parseFloat(editTextNum2.getText().toString());
            textView.setText(String.valueOf(num1*num2));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        textView.setText("Simple Calculator!!");

        editTextNum1 = findViewById(R.id.editTextNum1);
        editTextNum2 = findViewById(R.id.editTextNum2);

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(this);

        buttonSub = findViewById(R.id.buttonSub);
        buttonSub.setOnClickListener((v)-> {
            float num1 = Float.parseFloat(editTextNum1.getText().toString());
            float num2 = Float.parseFloat(editTextNum2.getText().toString());
            textView.setText(String.valueOf(num1-num2));
        });

        buttonMul = findViewById(R.id.buttonMul);
        buttonMul.setOnClickListener(listener);
    }

    @Override
    public void onClick(View v) {

        float num1 = 0, num2 = 0;

        try {
            num1 = Float.parseFloat(editTextNum1.getText().toString());
            num1 = Float.parseFloat(editTextNum1.getText().toString());
        }
        catch(NumberFormatException e) {
            textView.setText("Invalid Number");
            return;
        }
        textView.setText(String.valueOf(num1+num2));
    }

    public void onDivision(View v) {

        float num1=Float.parseFloat(editTextNum1.getText().toString());
        float num2=Float.parseFloat(editTextNum2.getText().toString());
        if(num2==0) {
            textView.setText("Divided by zero");
        }
        else {
            textView.setText(String.valueOf(num1/num2));
        }
    }
}

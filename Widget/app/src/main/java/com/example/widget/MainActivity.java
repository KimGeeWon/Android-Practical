package com.example.widget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity implements TextWatcher,
        View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private EditText name, phone;
    private RadioButton adult, student;
    private CheckBox terms;
    private ProgressBar progressBar;
    private Button apply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
    }

    private void initWidgets() {
        name = findViewById(R.id.editTextName);
        name.addTextChangedListener(this);
        phone = findViewById(R.id.editTextPhone);
        phone.addTextChangedListener(this);
        adult = findViewById(R.id.radioButtonAdult);
        adult.setOnClickListener(this);
        student = findViewById(R.id.radioButtonStudent);
        student.setOnClickListener(this);
        terms = findViewById(R.id.checkBoxTerms);
        terms.setOnCheckedChangeListener(this);
        progressBar = findViewById(R.id.progressBar);
        apply = findViewById(R.id.buttonApply);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        Log.i("Main", "before: "+s+", start: "+start+", count: "+count+", after: "+after);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Log.i("Main", "on="+s+", start: "+start+", before: "+before+", count: "+count);
    }

    @Override
    public void afterTextChanged(Editable s) {
        Log.i("Main", "after: "+s.toString());
        updateProgress();
    }

    @Override
    public void onClick(View view) {
        updateProgress();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        updateProgress();
    }

    private void updateProgress() {
        int progress=0;

        if(name.getText().length() > 0) {
            progress++;
        }
        if(phone.getText().length() > 0) {
            progress++;
        }
        if(adult.isChecked() || student.isChecked()) {
            progress++;
        }
        if(terms.isChecked()) {
            progress++;
        }

        progressBar.setProgress(progress);

        if(progress == 4) {
            apply.setVisibility(View.VISIBLE);
        }
        else {
            apply.setVisibility(View.INVISIBLE);
        }
    }
}
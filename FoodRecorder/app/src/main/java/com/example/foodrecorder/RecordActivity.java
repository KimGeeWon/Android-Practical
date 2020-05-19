package com.example.foodrecorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.example.foodrecorder.data.FoodRecord;
import com.example.foodrecorder.data.FoodRecordDatabase;
import com.example.foodrecorder.data.FoodRecordOpenHelper;
import com.example.foodrecorder.data.RecordAdapter;
import com.example.foodrecorder.databinding.ActivityRecordBinding;

import java.util.ArrayList;
import java.util.List;

public class RecordActivity extends AppCompatActivity implements RecordAdapter.OnItemClickListener {
    private ActivityRecordBinding binding;
    //private FoodRecordOpenHelper helper;
    private FoodRecordDatabase db;
    private RecordAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityRecordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //helper = new FoodRecordOpenHelper(this, "db", null, 1);
        db = FoodRecordDatabase.getInstance(getApplicationContext());
        LinearLayoutManager manager = new LinearLayoutManager(this);
        adapter = new RecordAdapter(null, this);
        binding.recyclearView.setAdapter(adapter);
        binding.recyclearView.setLayoutManager(manager);
        new LoadTask().execute();
    }

    @Override
    public void onItemClick(View v, int position, FoodRecord record) {
         new DeleteTask().execute(record);
//        helper.delete(record.getId());
//        adapter.setData(helper.getRecords());
//        adapter.notifyDataSetChanged();
    }

    class LoadTask extends AsyncTask<Void, Void, List<FoodRecord>> {
        @Override
        protected List<FoodRecord> doInBackground(Void... voids) {
            return db.foodRecordDAO().getRecords();
        }
        @Override
        protected void onPostExecute(List<FoodRecord> foodRecords) {
            super.onPostExecute(foodRecords);
            adapter.setData((ArrayList<FoodRecord>) foodRecords);
            adapter.notifyDataSetChanged();
        }
    }

    class DeleteTask extends AsyncTask<FoodRecord, Void, List<FoodRecord>> {
        @Override
        protected List<FoodRecord> doInBackground(FoodRecord... args) {
            int result = db.foodRecordDAO().delete(args[0]);
            if(result == 1) {
                return db.foodRecordDAO().getRecords();
            }
            else {
                return null;
            }
        }
        @Override
        protected void onPostExecute(List<FoodRecord> foodRecords) {
            super.onPostExecute(foodRecords);
            if(foodRecords != null) {
                adapter.setData((ArrayList<FoodRecord>) foodRecords);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
package com.example.foodrecorder.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverter;

import java.util.List;

@Dao
public interface FoodRecordDAO {
    @Insert
    long addRecord(FoodRecord record);

    @Query("select * from foods")
    @TypeConverter
    List<FoodRecord> getRecords();

    @Query("select * from foods where id=:id")
    FoodRecord get(int id);

    @Delete
    int delete(FoodRecord record);
}

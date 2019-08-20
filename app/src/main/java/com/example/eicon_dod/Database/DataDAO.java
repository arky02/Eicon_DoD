package com.example.eicon_dod.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DataDAO {
    @Query("SELECT * FROM data")
    List<Data> getDataList();

    @Insert
    void insertData(Data data);

    @Update
    void updateData(Data data);

    @Delete
    void deleteData(Data data);
}

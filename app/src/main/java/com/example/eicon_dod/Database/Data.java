package com.example.eicon_dod.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;

@Entity(tableName = "data")
public class Data {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "time")
    public Timestamp timestamp;

    @ColumnInfo(name = "word")
    public String word;
}

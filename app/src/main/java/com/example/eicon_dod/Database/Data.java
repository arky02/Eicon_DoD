package com.example.eicon_dod.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;
import java.util.UUID;

@Entity(tableName = "data")
public class Data {
    @PrimaryKey
    public String uuid;

    @ColumnInfo(name = "time")
    public Timestamp timestamp;

    @ColumnInfo(name = "word")
    public String word;

    public Data(Timestamp timestamp, String word) {
        this.uuid = UUID.randomUUID().toString();
        this.timestamp = timestamp;
        this.word = word;
    }
}

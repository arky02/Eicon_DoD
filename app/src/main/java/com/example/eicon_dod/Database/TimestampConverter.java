package com.example.eicon_dod.Database;

import androidx.room.TypeConverter;

import java.sql.Timestamp;

public class TimestampConverter {
    @TypeConverter
    public long timestampToLong(Timestamp value) {
        return value.getTime();
    }

    @TypeConverter
    public Timestamp longToTimestamp(long date) {
        return new Timestamp(date);
    }
}

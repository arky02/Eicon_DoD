package com.example.eicon_dod;

import com.example.eicon_dod.Database.Data;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Helper {
    public static float countOccurence(List<Data> data, String date) {
        float count = 0;
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("E", Locale.US);
        for (Data temp : data) {
            if (simpleDateformat.format(temp.timestamp).equals(date)) {
                count++;
            }
        }

        return count;
    }

    public static List<Data> filterDate(List<Data> data, Timestamp time) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.SUNDAY);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());

        List<Data> DataList = new ArrayList<>();
        for (Data temp : data) {
            if(temp.timestamp.after(c.getTime())) {
                DataList.add(temp);
            }
        }

        return DataList;
    }
}

package com.example.eicon_dod;

import android.util.Log;

import com.example.eicon_dod.Database.Data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Helper {
    public static float countOccurrence(List<Data> data, Boolean filter, String date) {
        float count = 0;
        if (filter) {
            SimpleDateFormat simpleDateformat = new SimpleDateFormat("E", Locale.US);
            for (Data temp : data) {
                if (simpleDateformat.format(temp.timestamp).equals(date)) {
                    count++;
                }
            }
        } else {
            return data.size();
        }

        return count;
    }

    public static List<Data> filterDate(List<Data> data) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.SUNDAY);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());

        List<Data> DataList = new ArrayList<>();
        for (Data temp : data) {
            if (temp.timestamp.after(c.getTime())) {
                DataList.add(temp);
            }
        }

        return DataList;
    }

    public static HashMap<Integer, Map.Entry<String, Integer>> topThree(List<Data> data) {
        HashMap<String, Integer> countMap = new HashMap<>();
        for (Data temp : data) {
            Integer count = countMap.get(temp.word);
            if (count == null) count = 0;
            countMap.put(temp.word, count + 1);
        }

        List<Map.Entry<String, Integer>> list = new LinkedList<>(countMap.entrySet());

        Collections.sort(list, (o1, o2) -> (o2.getValue().compareTo(o1.getValue())));
        Log.e("BUG", list.toString());
        HashMap<String, Integer> result = new HashMap<>();
        int listSize = list.size();
        for (int i = 0; i < listSize; i++) {
            Map.Entry<String, Integer> temp = list.get(i);
            result.put(temp.getKey(), temp.getValue());
        }

        Log.e("TEST", result.toString());
        return getSomeEntries(3, result);
    }


    public static HashMap<Integer, Map.Entry<String, Integer>> getSomeEntries(int max, HashMap<String, Integer> source) {
        int count = 0;
        HashMap<Integer, Map.Entry<String, Integer>> target = new HashMap<>();

        for (Map.Entry<String, Integer> entry : source.entrySet()) {
            if (count >= max) break;
            Log.e("TEST", entry.toString());
            target.put(count, entry);
            count++;
        }

        return target;
    }
}

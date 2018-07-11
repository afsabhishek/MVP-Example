package com.sample.myapplication.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utility {

    public static String returnDay(String dateStr){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        String finalDay = null;
        try {
            date = dateFormat.parse(dateStr);
            DateFormat format2=new SimpleDateFormat("EEEE");
            finalDay=format2.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return finalDay;
    }
}

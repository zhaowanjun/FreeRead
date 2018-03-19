package com.joy.freeread.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static boolean isSameDate(Date date1, Date date2) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        Calendar selectedDate = Calendar.getInstance();
        selectedDate.setTime(date2);

        return cal.get(Calendar.DAY_OF_YEAR) == selectedDate.get(Calendar.DAY_OF_YEAR);
    }

}

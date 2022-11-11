package com.example.demo;

import org.apache.commons.lang3.StringUtils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by @author Harish Patil on 02-11-2022
 */
 class Testing {
    public static void main(String args[]){

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        Date reconEndDateTime = Testing.getDateWithTime(new Date(), -1, 23, 59, 59, 999);
        System.out.println(Testing.formatDateToString(reconEndDateTime, "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"));
    }
    public static Date getDateWithTime(Date dateNow, int day,  int hour, int min, int sec, int milli) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateNow);
        calendar.add(Calendar.DATE, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.SECOND, sec);
        calendar.set(Calendar.MILLISECOND, milli);
        Date dateTime = calendar.getTime();
        return dateTime;
    }
    public static String formatDateToString(Date date, String dateFormat) {

        String strDate = null;
        if(date != null && StringUtils.isNotBlank(dateFormat)){
            Format f = new SimpleDateFormat(dateFormat);
            strDate = f.format(date);
        }
        return strDate;
    }

}

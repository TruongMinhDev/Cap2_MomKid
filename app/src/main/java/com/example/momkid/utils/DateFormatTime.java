package com.example.momkid.utils;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatTime {

    public static String formatTime(String date1) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        try {
            Date date = df.parse(date1);
            SimpleDateFormat newDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String newDateString = newDateFormat.format(date);
            return newDateString;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static int calculateAge(String birthdate) {
        Date currentDate = new Date();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date birthDate;
        try {
            birthDate = df.parse(birthdate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        long d1 = birthDate.getTime();
        long d2 = currentDate.getTime();
        long age = Math.abs(d2 - d1);
        long result = age / (24 * 60 * 60 * 1000);
        return (int) result;
    }

}

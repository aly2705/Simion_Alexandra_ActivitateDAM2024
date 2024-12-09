package com.example.myapplication;

import androidx.room.TypeConverter;

import java.util.Date;

public class ConvertDateToLong {
    @TypeConverter
    public static long dateToLong(Date date){
        return date.getTime();
    }

    @TypeConverter
    public static Date longToDate(long num){
        return new Date(num);
    }

}
